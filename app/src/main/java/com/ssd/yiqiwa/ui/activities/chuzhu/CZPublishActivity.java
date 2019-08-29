package com.ssd.yiqiwa.ui.activities.chuzhu;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.api.Api;
import com.ssd.yiqiwa.model.LoadModel;
import com.ssd.yiqiwa.model.entity.BaseBean;
import com.ssd.yiqiwa.model.entity.BaseBeanList;
import com.ssd.yiqiwa.model.entity.HomeBase;
import com.ssd.yiqiwa.model.entity.JsonEntity;
import com.ssd.yiqiwa.model.entity.MachineBrandBean;
import com.ssd.yiqiwa.model.entity.MachineModelBean;
import com.ssd.yiqiwa.model.entity.MachineTypeBean;
import com.ssd.yiqiwa.model.entity.ProductBean;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.adapter.ImageUploadAdapter;
import com.ssd.yiqiwa.utils.Constants;
import com.ssd.yiqiwa.utils.DateFormatUtil;
import com.ssd.yiqiwa.widget.GlideImageLoader;
import com.ssd.yiqiwa.widget.GlideImageThisLoader;
import com.ssd.yiqiwa.widget.GlideLoadEngine;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 出租发布
 */
public class CZPublishActivity extends BaseActivity {

    @BindView(R.id.grv_image_upload)
    GridView gridView;

    ImageUploadAdapter imageUploadAdapter;
    private Activity activity;
    private Context context;

    Validator validator;

    @NotEmpty(message = "标题不能为空")
    @BindView(R.id.edt_publish_01)
    EditText edt_publish_01;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_03)
    EditText edt_publish_03;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_04)
    EditText edt_publish_04;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_05)
    EditText edt_publish_05;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_07)
    EditText edt_publish_07;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_11)
    EditText edt_publish_11;
    @BindView(R.id.edt_publish_12)
    EditText edt_publish_12;
    @BindView(R.id.edt_publish_14)
    EditText edt_publish_14;
    @BindView(R.id.edt_publish_15)
    EditText edt_publish_15;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_16)
    EditText edt_publish_16;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_19)
    EditText edt_publish_19;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_20)
    EditText edt_publish_20;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_22)
    EditText edt_publish_22;

    @BindView(R.id.txt_publish_10)
    TextView txt_publish_10;


    @BindView(R.id.spr_publish_06)
    Spinner spr_publish_06;
    @BindView(R.id.spr_publish_08)
    Spinner spr_publish_08;
    @BindView(R.id.spr_publish_10)
    Spinner spr_publish_10;
    @BindView(R.id.spr_publish_17)
    Spinner spr_publish_17;
    @BindView(R.id.spr_publish_18)
    Spinner spr_publish_18;

    @BindView(R.id.rbn_geren)
    RadioButton rbn_geren;
    @BindView(R.id.rbn_gongshi)
    RadioButton rbn_gongshi;
    // 品牌列表
    List<MachineBrandBean> machineBrandBeans = new ArrayList<>();
    //设备类型
    List<MachineTypeBean> machineTypeBeans = new ArrayList<>();
    //设备型号
    List<MachineModelBean> machineModelTypes = new ArrayList<>();
    //出厂年份
    List<String> yearList = new ArrayList<>();

    private String machineBrand;
    private String machineType;
    private String machineModelType;
    private String standard;
    private String factoryDate;

    @Override
    public Object offerLayout() {
        return R.layout.cz_activity_publish;
    }

    @Override
    public void onBindView() {
        activity = this;
        context = getApplicationContext();
        validator = new Validator(this);
        validator.setValidationListener(new Validator.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                verifyPublishDialog();
            }

            @Override
            public void onValidationFailed(List<ValidationError> errors) {
                for (ValidationError error : errors) {
                    View view = error.getView();
                    String message = error.getCollatedErrorMessage(activity);
                    if (view instanceof EditText) {
                        ((EditText) view).setError(message);
                    }
                }
            }
        });



        selectList = new ArrayList<>();
        imageUploadAdapter = new ImageUploadAdapter(selectList, context, new ImageUploadAdapter.OnClickImageDelete() {
            @Override
            public void onClickImageDelete(int postion) {
                selectList.remove(postion);
                imageUploadAdapter.notifyDataSetChanged();
            }

            @Override
            public void onShowPhoto() {
                showPicture();
            }
        });

        gridView.setAdapter(imageUploadAdapter);

        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(selectList.get(position)!=null){
                    photoShowDialog(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spr_publish_06.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(machineTypeBeans.size()!=0) {
                    machineType = machineTypeBeans.get(position).getMbId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spr_publish_08.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(machineBrandBeans.size()!=0){
                    machineBrand = machineBrandBeans.get(position).getMbId();
                    getMachineModelType(Integer.parseInt(machineBrand), 0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spr_publish_10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(machineModelTypes.size()!=0) {
                    machineModelType = machineModelTypes.get(position).getMbId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spr_publish_17.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(yearList.size()!=0) {
                    factoryDate = yearList.get(position).trim();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spr_publish_18.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                standard = getResources().getStringArray(R.array.paifangbiaozhun)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        getMachineBrandAll();
        getMachineTypeAll();

        int currentYear = Integer.parseInt(DateFormatUtil.getDateCurrentFormat(DateFormatUtil.FORMAT_yyyy));
         yearList = new ArrayList<>();
        for(int i = 0; i < 15; i++){
            int cyear = currentYear-i;
            yearList.add(cyear+"");
        }
        spr_publish_17.setAdapter(new ArrayAdapter<String>(CZPublishActivity.this, android.R.layout.simple_spinner_item, yearList) );

    }

    @Override
    public void destory() {

    }

    @OnClick({R.id.img_back,R.id.txt_verify_publish,R.id.rab_dunwei,R.id.rab_xinghao})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.txt_verify_publish:
                validator.validate();
//                verifyPublishDialog();
                break;
            case R.id.rab_dunwei:
                txt_publish_10.setText("设备吨位");
                getMachineModelType(Integer.parseInt(machineBrand),0);
                break;
            case R.id.rab_xinghao:
                txt_publish_10.setText("设备型号");
                getMachineModelType(Integer.parseInt(machineBrand),1);
                break;

        }
    }

    /**
     * 打开相册
     */
    public void showPicture(){
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(CZPublishActivity.this)
                .openGallery(PictureMimeType.ofAll())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_QQ_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(10)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE )// 多选 or 单选  PictureConfig.MULTIPLE : PictureConfig.SINGLE
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .selectionMedia(selectList)// 是否传入已选图片
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

    }

    private List<LocalMedia> selectList = new ArrayList<>();


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    selectList.clear();
                    // 图片选择结果回调
                    selectList.addAll(PictureSelector.obtainMultipleResult(data));
                    imageUploadAdapter.notifyDataSetChanged();
                    break;
            }
        }

        Log.e("Matisse", "mSelected: " + data);
    }


    /**
     * 验证信息
     */
    private void verifyPublishDialog(){
        Dialog dia = new Dialog(activity, R.style.dialog);
        dia.setContentView(R.layout.item_cz_verify_publish);

        ImageView textView = dia.findViewById(R.id.img_close);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dia.dismiss();
            }
        });
        dia.findViewById(R.id.txt_putongfabu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRentOutAdd(0);
                ToastUtils.showLong("普通发布成功");
                dia.dismiss();
            }
        });
        dia.findViewById(R.id.txt_jingpingfabu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRentOutAdd(1);
                ToastUtils.showLong("精品发布成功");
                dia.dismiss();
            }
        });




        //选择true的话点击其他地方可以使dialog消失，为false的话不会消失
        dia.setCanceledOnTouchOutside(false); // Sets whether this dialog is

//        Window window = dia.getWindow();
//        window.getDecorView().setPadding(0, 0, 0, 0);
//        WindowManager.LayoutParams layoutParams = window.getAttributes();
//        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//        layoutParams.horizontalMargin = 0;
//        window.setAttributes(layoutParams);
//        window.getDecorView().setMinimumWidth(getResources().getDisplayMetrics().widthPixels);
//        window.getDecorView().setBackgroundColor(Color.TRANSPARENT);

        dia.show();
    }



    /**
     * 查看图片
     */
    private void photoShowDialog(int position){
        Dialog dia = new Dialog(activity, R.style.imageDialog);
        dia.setContentView(R.layout.item_image_banner);
        Banner banner =  dia.findViewById(R.id.banner);

//        List<Uri> uriList = mSelected;
//        uriList.remove(uriList.size()-1);
        List<String> uriList = new ArrayList<>();
        for(LocalMedia item:selectList){
            uriList.add(item.getPath());
        }

            banner.setImageLoader(new GlideImageThisLoader())
                .setImages(uriList)
                .isAutoPlay(false)
                .setBannerStyle(BannerConfig.NUM_INDICATOR)
                .start();
            banner.setIndicatorGravity(position);

        ImageView imageView = dia.findViewById(R.id.img_count_down);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dia.dismiss();
            }
        });
        //选择true的话点击其他地方可以使dialog消失，为false的话不会消失
        dia.setCanceledOnTouchOutside(false); // Sets whether this dialog is

        Window window = dia.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.horizontalMargin = 0;
        window.setAttributes(layoutParams);
        window.getDecorView().setMinimumWidth(getResources().getDisplayMetrics().widthPixels);
        window.getDecorView().setBackgroundColor(Color.TRANSPARENT);

        dia.show();
    }


    /**
     * 获取产品信息
     */
    public void getMachineBrandAll(){
        Api request = getRetrofit().create(Api.class);
        Call<BaseBeanList<MachineBrandBean>> call = request.machineBrandAll();
        call.enqueue(new Callback<BaseBeanList<MachineBrandBean>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BaseBeanList<MachineBrandBean>> call, Response<BaseBeanList<MachineBrandBean>> response) {
                hideDialog();
                BaseBeanList<MachineBrandBean> baseBeanList = response.body();

                if(baseBeanList.getCode()== Constants.HTTP_RESPONSE_OK){
                    machineBrandBeans =  baseBeanList.getData();
                    List<String> machineList = new ArrayList<>();
                    for (MachineBrandBean item:baseBeanList.getData()){
                        machineList.add(item.getName());
                    }
                    spr_publish_08.setAdapter(new ArrayAdapter<>(CZPublishActivity.this, android.R.layout.simple_spinner_item, machineList) );
                }else{
                    ToastUtils.showLong(baseBeanList.getMsg());
                }
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<BaseBeanList<MachineBrandBean>> call, Throwable throwable) {
                LogUtils.e("请求失败");
                LogUtils.e(throwable.getMessage());
            }
        });
    }

    /**
     * 获取设备类型
     */
    public void getMachineTypeAll(){
        Api request = getRetrofit().create(Api.class);
        Call<BaseBeanList<MachineTypeBean>> call = request.machineTypeAll();
        call.enqueue(new Callback<BaseBeanList<MachineTypeBean>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BaseBeanList<MachineTypeBean>> call, Response<BaseBeanList<MachineTypeBean>> response) {
                hideDialog();
                BaseBeanList<MachineTypeBean> baseBeanList = response.body();

                if(baseBeanList.getCode()== Constants.HTTP_RESPONSE_OK){
                    machineTypeBeans =  baseBeanList.getData();
                    List<String> machineList = new ArrayList<>();
                    for (MachineTypeBean item:baseBeanList.getData()){
                        machineList.add(item.getName());
                    }
                    spr_publish_06.setAdapter(new ArrayAdapter<>(CZPublishActivity.this, android.R.layout.simple_spinner_item, machineList) );
                }else{
                    ToastUtils.showLong(baseBeanList.getMsg());
                }
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<BaseBeanList<MachineTypeBean>> call, Throwable throwable) {
                LogUtils.e("请求失败");
                LogUtils.e(throwable.getMessage());
            }
        });
    }

    /**
     * 获取设备型号
     */
    public void getMachineModelType(int mbId,int type){
        Api request = getRetrofit().create(Api.class);
        Call<BaseBeanList<MachineModelBean>> call = request.machineModelType(mbId,type);
        call.enqueue(new Callback<BaseBeanList<MachineModelBean>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BaseBeanList<MachineModelBean>> call, Response<BaseBeanList<MachineModelBean>> response) {
                hideDialog();
                BaseBeanList<MachineModelBean> baseBeanList = response.body();

                if(baseBeanList.getCode()== Constants.HTTP_RESPONSE_OK){
                    machineModelTypes =  baseBeanList.getData();
                    List<String> machineList = new ArrayList<>();
                    for (MachineModelBean item:baseBeanList.getData()){
                        if(type==0){
                            machineList.add(item.getTonnage());
                        }else {
                            machineList.add(item.getName());
                        }
                    }
                    spr_publish_10.setAdapter(new ArrayAdapter<>(CZPublishActivity.this, android.R.layout.simple_spinner_item, machineList) );
                }else{
                    ToastUtils.showLong(baseBeanList.getMsg());
                }
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<BaseBeanList<MachineModelBean>> call, Throwable throwable) {
                LogUtils.e("请求失败");
                LogUtils.e(throwable.getMessage());
            }
        });
    }



    /**
     * 获取产品信息
     */
    public void getRentOutAdd(int boutique){

        Map<String,Object> rentOutMap = new HashMap<>();
        rentOutMap.put("roId","");
        rentOutMap.put("title",edt_publish_01.getText().toString());
        rentOutMap.put("coverImage","封面的图片xxxxxxxxxxxxxxxxx");
        if(rbn_geren.isChecked()) {
            rentOutMap.put("rentFrom", "个人");
            rentOutMap.put("contactPerson", "张三");
            rentOutMap.put("companyName", "");
        }else{
            rentOutMap.put("rentFrom", "公司");
            rentOutMap.put("companyName", "东拉西扯公司");
            rentOutMap.put("contactPerson", "");
        }


        rentOutMap.put("contactPhone",edt_publish_04.getText().toString());


        rentOutMap.put("mtId",machineType);

        rentOutMap.put("productDesc",edt_publish_07.getText().toString());

        rentOutMap.put("mbId",machineBrand);

        rentOutMap.put("mbmId",machineModelType);

        rentOutMap.put("capacity",edt_publish_11.getText().toString());
        rentOutMap.put("priceHour",edt_publish_12.getText().toString());
        rentOutMap.put("priceDay",edt_publish_14.getText().toString());
        rentOutMap.put("priceMonth",edt_publish_15.getText().toString());
        rentOutMap.put("workTime",edt_publish_16.getText().toString());
        rentOutMap.put("factoryDate",factoryDate);
        rentOutMap.put("standard",standard);
        rentOutMap.put("province","四川省");
        rentOutMap.put("city","成都市");
        rentOutMap.put("county","金牛区");
        rentOutMap.put("address",edt_publish_20.getText().toString());
        rentOutMap.put("describes",edt_publish_22.getText().toString());
        rentOutMap.put("uId", SPStaticUtils.getInt(Constants.SP_USER_ID));
        rentOutMap.put("boutique",boutique);
//        rentOutMap.put("pictureList[0].url","");
        rentOutMap.put("pictureList","");

        Api request = getRetrofit().create(Api.class);
        Call<BaseBean<JsonEntity>> call = request.rentOutAdd(rentOutMap);
        call.enqueue(new Callback<BaseBean<JsonEntity>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BaseBean<JsonEntity>> call, Response<BaseBean<JsonEntity>> response) {
                hideDialog();
                BaseBean<JsonEntity> baseBeanList = response.body();
                if(baseBeanList.getCode()== Constants.HTTP_RESPONSE_OK){
                    ToastUtils.showLong(baseBeanList.getMsg());
                    finish();
                }else{
                    ToastUtils.showLong(baseBeanList.getMsg());
                }
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<BaseBean<JsonEntity>> call, Throwable throwable) {
                LogUtils.e("请求失败");
                LogUtils.e(throwable.getMessage());
            }
        });
    }




}
