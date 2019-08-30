package com.ssd.yiqiwa.ui.activities.chengzhu;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.api.Api;
import com.ssd.yiqiwa.model.entity.BaseBeanList;
import com.ssd.yiqiwa.model.entity.JsonEntity;
import com.ssd.yiqiwa.model.entity.MachineBrandBean;
import com.ssd.yiqiwa.model.entity.MachineModelBean;
import com.ssd.yiqiwa.model.entity.MachineTypeBean;
import com.ssd.yiqiwa.model.entity.UploadImageBean;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.adapter.ImageUploadAdapter;
import com.ssd.yiqiwa.utils.AddressInitTask;
import com.ssd.yiqiwa.utils.Constants;
import com.ssd.yiqiwa.widget.GlideImageThisLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.AddressPicker;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 承租发布
 */
public class ChengZhuPublishActivity extends BaseActivity {


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
    @BindView(R.id.edt_publish_12)
    EditText edt_publish_12;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_16)
    EditText edt_publish_16;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_20)
    EditText edt_publish_20;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_22)
    EditText edt_publish_22;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_27)
    EditText edt_publish_27;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_28)
    EditText edt_publish_28;

    @BindView(R.id.txt_publish_10)
    TextView txt_publish_10;
    @BindView(R.id.txt_publish_19_1)
    TextView txt_publish_19_1;
    @BindView(R.id.txt_publish_17_1)
    TextView txt_publish_17_1;

    @BindView(R.id.lil_publish_03)
    LinearLayout lil_publish_03;


    @BindView(R.id.spr_publish_06)
    Spinner spr_publish_06;
    @BindView(R.id.spr_publish_10)
    Spinner spr_publish_10;
    @BindView(R.id.spr_publish_18)
    Spinner spr_publish_18;

    @BindView(R.id.rbn_geren)
    RadioButton rbn_geren;
    @BindView(R.id.rbn_gongshi)
    RadioButton rbn_gongshi;
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

    private String fb_province;
    private String fb_city;
    private String fb_county;

    private String coverImage;

    private int boutique = 0;

    @Override
    public Object offerLayout() {
        return R.layout.chengzu_activity_publish;
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
                ToastUtils.showLong("资料请填写完整");
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
                if(machineTypeBeans.size()!=0) {
                    if (selectList.get(position) != null) {
                        photoShowDialog(position);
                    }
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

//        spr_publish_17.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(yearList.size()!=0) {
//                    factoryDate = yearList.get(position).trim();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        spr_publish_18.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                standard = getResources().getStringArray(R.array.paifangbiaozhun)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getMachineTypeAll();

//        int currentYear = Integer.parseInt(DateFormatUtil.getDateCurrentFormat(DateFormatUtil.FORMAT_yyyy));
//         yearList = new ArrayList<>();
//        for(int i = 0; i < 15; i++){
//            int cyear = currentYear-i;
//            yearList.add(cyear+"");
//        }
//        spr_publish_17.setAdapter(new ArrayAdapter<String>(CZPublishActivity.this, android.R.layout.simple_spinner_item, yearList) );

    }

    @Override
    public void destory() {

    }

    @OnClick({R.id.img_back,R.id.txt_verify_publish,R.id.rab_dunwei,R.id.rab_xinghao,R.id.txt_publish_19_1,R.id.txt_publish_17_1,
            R.id.rbn_gongshi,R.id.rbn_geren})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.txt_verify_publish:
                validator.validate();

//                isUploadImage();
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
            case R.id.rbn_gongshi:
                lil_publish_03.setVisibility(View.VISIBLE);
                break;
            case R.id.rbn_geren:
                lil_publish_03.setVisibility(View.GONE);
                break;
            case R.id.txt_publish_19_1:
                new AddressInitTask(this, new AddressInitTask.InitCallback() {
                    @Override
                    public void onDataInitFailure() {
                        showToast("数据初始化失败");
                    }

                    @Override
                    public void onDataInitSuccess(ArrayList<Province> provinces) {
                        AddressPicker picker = new AddressPicker(activity, provinces);
                        picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
                            @Override
                            public void onAddressPicked(Province province, City city, County county) {
                                fb_province = province.getName();
                                fb_city = "";
                                if (city != null) {
                                    fb_city = city.getName();
                                    //忽略直辖市的二级名称
                                    if (fb_city.equals("市辖区") || fb_city.equals("市") || fb_city.equals("县")) {
                                        fb_city = "";
                                    }
                                }
                                fb_county = "";
                                if (county != null) {
                                    fb_county = county.getName();
                                }
//                                showToast(provinceName + " " + cityName + " " + countyName);
                                txt_publish_19_1.setText(fb_province+fb_city+fb_county);
                            }
                        });
                        picker.show();
                    }
                }).execute();
                break;
            case R.id.txt_publish_17_1:
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(ChengZhuPublishActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        factoryDate = year + "-" + (monthOfYear+1) + "-" + dayOfMonth;
                        txt_publish_17_1.setText(factoryDate);

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;

        }
    }

    /**
     * 打开相册
     */
    public void showPicture(){
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(ChengZhuPublishActivity.this)
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

        if(selectList.size()<1){
            ToastUtils.showLong("必须要上传一张图片作为主页展示。");
            return;
        }

        if(txt_publish_19_1.getText().toString().isEmpty()){
            ToastUtils.showLong("请选择设备停靠省市区");
            return;
        }

        if(factoryDate.isEmpty()){
            ToastUtils.showLong("请选择出场时间");
            return;
        }


//        if(machineType==null||machineType.isEmpty()){
//            ToastUtils.showLong("请选择型号或吨位。");
//            return;
//        }xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        machineType = "1";




        Dialog dia = new Dialog(activity, R.style.dialog);
        dia.setContentView(R.layout.item_cs_verify_publish);


        ImageView img_tipian = dia.findViewById(R.id.img_tipian);
        Glide.with(activity).load(selectList.get(0).getPath()).into(img_tipian);
        TextView txt_cart_product_title = dia.findViewById(R.id.txt_cart_product_title);
        txt_cart_product_title.setText(edt_publish_01.getText().toString());
        TextView txt_cart_product_type = dia.findViewById(R.id.txt_cart_product_type);
        String productType = fb_province + fb_city +" | " + factoryDate + " | "+ edt_publish_16.getText().toString();
        txt_cart_product_type.setText(productType);
        TextView txt_product_price = dia.findViewById(R.id.txt_product_price);
        txt_product_price.setText( edt_publish_12.getText().toString());

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
                boutique = 0;
                isUploadImage();
                dia.dismiss();
            }
        });
        dia.findViewById(R.id.txt_jingpingfabu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boutique = 1;
                isUploadImage();
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
                    boolean isSprAddType = true;
                    for (MachineTypeBean item:baseBeanList.getData()){
                        if(isSprAddType) {
                            machineType = item.getName();
                            isSprAddType = false;
                        }
                        machineList.add(item.getName());
                    }

                    spr_publish_06.setAdapter(new ArrayAdapter<>(ChengZhuPublishActivity.this, android.R.layout.simple_spinner_item, machineList) );
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

                    boolean isSprAddType = true;
                    for (MachineModelBean item:baseBeanList.getData()){
                        if(type==0){
                            if(isSprAddType) {
                                machineModelType = item.getName();
                                isSprAddType = false;
                            }
                            machineList.add(item.getTonnage());
                        }else {
                            if(isSprAddType) {
                                machineModelType = item.getName();
                                isSprAddType = false;
                            }
                            machineList.add(item.getName());
                        }
                    }
                    spr_publish_10.setAdapter(new ArrayAdapter<>(ChengZhuPublishActivity.this, android.R.layout.simple_spinner_item, machineList) );
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



    List<UploadImageBean> uploadImageBeans = new ArrayList<>();

    /**
     *  上传图
     */
    private void uploadImageFile(String filePath,int index)  {
        //创建文件(你需要上传到服务器的文件)
        //file1Location文件的路径 ,我是在手机存储根目录下创建了一个文件夹,里面放着了一张图片;
        File file = new File(filePath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("fileData", file.getName(), requestFile);

        Call<JsonEntity> call = getRetrofit().create(Api.class).uploadFile(body);
        call.enqueue(new Callback<JsonEntity>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<JsonEntity> call, Response<JsonEntity> response) {
                hideDialog();
                if(response.body().getCode()== Constants.HTTP_RESPONSE_OK) {

                    UploadImageBean uploadImageBean = uploadImageBeans.get(index);
                    uploadImageBean.setUrlFile(response.body().getData());
                    uploadImageBeans.remove(index);
                    uploadImageBeans.add(index,uploadImageBean);
//                    Collections.replaceAll(uploadImageBeans,uploadImageBeans.get(index),uploadImageBean);

                    LogUtils.e(GsonUtils.toJson(uploadImageBeans));
                    if(index ==0) {
                        coverImage = response.body().getData();
                    }
                    for(UploadImageBean item:uploadImageBeans){
                        if(item.getUrlFile().isEmpty()){
                            return;
                        }
                    }
                    getRentOutAdd();
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
     * 是否上传图片
     */
    public void isUploadImage(){
        uploadImageBeans.clear();
        for(int i = 0; i < selectList.size();i++){
            String filePath = selectList.get(i).getPath();
            uploadImageBeans.add(new UploadImageBean(i,filePath,""));
            uploadImageFile(filePath,i);
        }
    }


    /**
     * 获取产品信息
     */
    public void getRentOutAdd(){

        if(txt_publish_19_1.getText().toString().isEmpty()){
            ToastUtils.showLong("请选择设备停靠省市区");
            return;
        }
        if(coverImage.isEmpty()){
            ToastUtils.showLong("必须要上传一张图片作为主页展示。");
            return;
        }

        if(factoryDate.isEmpty()){
            ToastUtils.showLong("请选择出场时间");
            return;
        }

//        if(machineType==null||machineType.isEmpty()){
//            ToastUtils.showLong("请选择型号或吨位。");
//            return;
//        }xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        machineType = "1";

        Map<String,Object> rentOutMap = new HashMap<>();
        rentOutMap.put("sId","");
        rentOutMap.put("title",edt_publish_01.getText().toString());
        rentOutMap.put("coverImage",coverImage);
        if(rbn_geren.isChecked()) {
            rentOutMap.put("rentFrom", "个人");
            rentOutMap.put("contactPerson", edt_publish_04.getText().toString());
            rentOutMap.put("companyName", "");
        }else{
            rentOutMap.put("rentFrom", "公司");
            rentOutMap.put("companyName", edt_publish_03.getText().toString());
            rentOutMap.put("contactPerson", edt_publish_04.getText().toString());
        }

        rentOutMap.put("contactPhone",edt_publish_05.getText().toString());
        rentOutMap.put("mtId",machineType);
//        rentOutMap.put("mtId",1);
        rentOutMap.put("productDesc",edt_publish_07.getText().toString());
        rentOutMap.put("mbId",machineBrand);
//        rentOutMap.put("mbId",1);
        rentOutMap.put("mbmId",machineModelType);
//        rentOutMap.put("mbmId",1);
        rentOutMap.put("price",edt_publish_12.getText().toString());
        rentOutMap.put("workTime",edt_publish_16.getText().toString());
        rentOutMap.put("factoryDate",factoryDate);
        rentOutMap.put("standard",standard);
        rentOutMap.put("serialNumber",edt_publish_27.getText().toString());
        rentOutMap.put("envCode",edt_publish_28.getText().toString());
        rentOutMap.put("province",fb_province);
        rentOutMap.put("city",fb_city);
        rentOutMap.put("county",fb_county);
        rentOutMap.put("address",edt_publish_20.getText().toString());
        rentOutMap.put("describes",edt_publish_22.getText().toString());
        rentOutMap.put("uId", SPStaticUtils.getInt(Constants.SP_USER_ID));
        rentOutMap.put("boutique",boutique);
        for(int i = 0; i < uploadImageBeans.size();i++){
            rentOutMap.put("pictureList["+i+"].url",uploadImageBeans.get(i).getUrlFile());
        }
//        rentOutMap.put("pictureList","");

        Api request = getRetrofit().create(Api.class);
        Call<JsonEntity> call = request.sellAdd(rentOutMap);
        call.enqueue(new Callback<JsonEntity>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<JsonEntity> call, Response<JsonEntity> response) {
                hideDialog();
                JsonEntity baseBeanList = response.body();
                if(baseBeanList.getCode()== Constants.HTTP_RESPONSE_OK){
                    ToastUtils.showLong(baseBeanList.getMsg());
                    finish();
                }else{
                    ToastUtils.showLong(baseBeanList.getMsg());
                }
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<JsonEntity> call, Throwable throwable) {
                LogUtils.e("请求失败");
                LogUtils.e(throwable.getMessage());
                ToastUtils.showLong("发布失败");
            }
        });
    }





}
