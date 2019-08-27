package com.ssd.yiqiwa.ui.activities.gerenzhongxing;

import android.view.View;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.api.Api;
import com.ssd.yiqiwa.model.entity.BaseBean;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

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


    @Override
    public Object offerLayout() {
        return R.layout.z_activity_updateuser;
    }

    @Override
    public void onBindView() {

    }

    @Override
    public void destory() {

    }


    @OnClick({R.id.img_back})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
        }
    }


//    /**
//     * 响应上传点击事件的方法
//     */
//    private void upLoadingMethod() {
//
//        //创建文件(你需要上传到服务器的文件)
//        //file1Location文件的路径 ,我是在手机存储根目录下创建了一个文件夹,里面放着了一张图片;
//        File file = new File(file1Location);
//
//        //创建表单map,里面存储服务器本接口所需要的数据;
//        MultipartBody.Builder builder = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                //在这里添加服务器除了文件之外的其他参数
//                .addFormDataPart("参数1", "值1")
//
//
//        //设置文件的格式;两个文件上传在这里添加
//        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        // RequestBody imageBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
//        //添加文件(uploadfile就是你服务器中需要的文件参数)
//        builder.addFormDataPart("uploadfile", file.getName(), imageBody);
//        //builder.addFormDataPart("uploadfile1", file1.getName(), imageBody1);
//        //生成接口需要的list
//        List<MultipartBody.Part> parts = builder.build().parts();
//        //创建设置OkHttpClient
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(20, TimeUnit.SECONDS)
//                .readTimeout(20, TimeUnit.SECONDS)
//                .writeTimeout(20, TimeUnit.SECONDS)
//                //允许失败重试
//                .retryOnConnectionFailure(true)
//                .build();
//        //创建retrofit实例对象
//        Retrofit retrofit = new Retrofit.Builder()
//                //设置基站地址(基站地址+描述网络请求的接口上面注释的Post地址,就是要上传文件到服务器的地址,
//                // 这只是一种设置地址的方法,还有其他方式,不在赘述)
//                .baseUrl("你的基站地址")
//                //设置委托,使用OKHttp联网,也可以设置其他的;
//                .client(okHttpClient)
//                //设置数据解析器,如果没有这个类需要添加依赖:
//                .addConverterFactory(GsonConverterFactory.create())
//                //设置支持rxJava
//                // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//        //实例化请求接口,把表单传递过去;
//        Call<BaseBean> call = retrofit.create(Api.class).uploadFile(parts);
//        //开始请求
//        call.enqueue(new Callback<BaseBean>() {
//            @Override
//            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
//                //联网有响应或有返回数据
//                System.out.println(response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<BaseBean> call, Throwable t) {
//                //连接失败,多数是网络不可用导致的
//                System.out.println("网络不可用");
//            }
//        });
//
//    }


}
