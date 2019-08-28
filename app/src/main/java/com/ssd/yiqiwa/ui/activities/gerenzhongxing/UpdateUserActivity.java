package com.ssd.yiqiwa.ui.activities.gerenzhongxing;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.api.Api;
import com.ssd.yiqiwa.model.entity.BaseBean;
import com.ssd.yiqiwa.model.entity.JsonEntity;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.utils.Constants;
import com.ssd.yiqiwa.widget.GlideLoadEngine;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UpdateUserActivity extends BaseActivity {


    @BindView(R.id.img_user_head)
    ImageView imgUserHead;

    @BindView(R.id.txt_my_name)
    EditText txt_my_name;
    @BindView(R.id.txt_my_phone)
    EditText txt_my_phone;
    @BindView(R.id.txt_datetime)
    TextView txt_datetime;

    private Activity mActivity;

    private String portrait;
    private String nickName;
    private String contactPhone;
    private String birthday;

    @Override
    public Object offerLayout() {
        return R.layout.z_activity_updateuser;
    }

    @Override
    public void onBindView() {
        mActivity = UpdateUserActivity.this;

        portrait = SPStaticUtils.getString(Constants.SP_USER_PORTRAIT);
        nickName = SPStaticUtils.getString(Constants.SP_USER_NICKNAME);
        contactPhone = SPStaticUtils.getString(Constants.SP_USER_LOGINPHONE);
        birthday = SPStaticUtils.getString(Constants.SP_USER_BIRTHDAY);

        txt_my_name.setText(nickName);
        txt_my_name.setSelection(txt_my_name.getText().length());
        txt_my_phone.setText(contactPhone);
        txt_datetime.setText(birthday);
//            txt_shouchang.setText(SPStaticUtils.getString(Constants.SP_USER_NICKNAME));
        portrait = SPStaticUtils.getString(Constants.SP_USER_PORTRAIT);

        Glide.with(mActivity).load(Constants.ALIYUN_IMAGE_SSO+portrait)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imgUserHead);

    }

    @Override
    public void destory() {


    }


    @OnClick({R.id.img_back,R.id.img_user_head,R.id.txt_datetime,R.id.txt_update_user})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.img_user_head:
                showMatisse();
                break;
            case R.id.txt_datetime:
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateUserActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txt_datetime.setText(year + "年" + (monthOfYear+1) + "月" + dayOfMonth+"日");
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            case R.id.txt_update_user:
                getUerChangeInfo();

                break;
        }
    }





//



    public void showMatisse(){
        Matisse.from(this).choose(MimeType.ofImage(), false)
                .countable(true)
                .maxSelectable(21)
                .gridExpectedSize((int) getResources().getDimension(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.87f)
                .imageEngine(new GlideLoadEngine())
                .forResult(REQUEST_CODE_CHOOSE);
    }


    private final int REQUEST_CODE_CHOOSE = 10001;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> matisse = Matisse.obtainResult(data);
            Uri uriFile = matisse.get(0);
            imgUserHead.setImageURI(uriFile);
            upLoadingMethod(uriFile);

        }
        Log.e("Matisse", "mSelected: " + data);
    }



    /**
      *  响应上传点击事件的方法
      */
    private void upLoadingMethod(Uri uri)  {
        //创建文件(你需要上传到服务器的文件)
        //file1Location文件的路径 ,我是在手机存储根目录下创建了一个文件夹,里面放着了一张图片;
        File file = new File(getRealFilePath(UpdateUserActivity.this,uri));
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("fileData", file.getName(), requestFile);

        Call<JsonEntity> call = getRetrofit().create(Api.class).uploadFile(body);
        call.enqueue(new Callback<JsonEntity>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<JsonEntity> call, Response<JsonEntity> response) {
                hideDialog();
                ToastUtils.showLong( response.body().getMsg());
                if(response.body().getCode()== Constants.HTTP_RESPONSE_OK) {
                    portrait = response.body().getData();
                }

            }

            //请求失败时回调
            @Override
            public void onFailure(Call<JsonEntity> call, Throwable throwable) {
                Log.e("yqw","filePath:"+throwable.getMessage());
            }
        });

    }


    /**
     * Try to return the absolute file path from the given Uri
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePath(final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }



    /**
     * 修改用户信息
     */
    public void getUerChangeInfo(){
        nickName = txt_my_name.getText().toString();
        contactPhone = txt_my_phone.getText().toString();
        if(!RegexUtils.isMobileSimple(contactPhone)){
            ToastUtils.showLong("手机号格式错误");
            return;
        }

        showDialog();
        Api request = getRetrofit().create(Api.class);
        Call<JsonEntity> call = request.userChangeInfo(SPStaticUtils.getInt(Constants.SP_USER_ID),portrait,nickName,
                contactPhone,birthday);
        call.enqueue(new Callback<JsonEntity>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<JsonEntity> call, Response<JsonEntity> response) {
                hideDialog();
                ToastUtils.showLong( response.body().getMsg());
                if(response.body().getCode()==Constants.HTTP_RESPONSE_OK) {
                    finish();
                }

            }

            //请求失败时回调
            @Override
            public void onFailure(Call<JsonEntity> call, Throwable throwable) {
                LogUtils.e("请求失败");
                LogUtils.e(throwable.getMessage());
            }
        });
    }

}
