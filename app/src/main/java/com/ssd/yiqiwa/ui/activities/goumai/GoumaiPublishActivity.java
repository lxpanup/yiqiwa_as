package com.ssd.yiqiwa.ui.activities.goumai;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.api.Api;
import com.ssd.yiqiwa.model.entity.BaseBeanList;
import com.ssd.yiqiwa.model.entity.JsonEntity;
import com.ssd.yiqiwa.model.entity.MachineBrandBean;
import com.ssd.yiqiwa.model.entity.MachineTypeBean;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.activities.chushou.CSPublishActivity;
import com.ssd.yiqiwa.ui.adapter.CheckBoxAdapter;
import com.ssd.yiqiwa.utils.AddressInitTask;
import com.ssd.yiqiwa.utils.Constants;
import com.ssd.yiqiwa.widget.common.CommomDialog;

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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 出售购买-二手出售
 */
public class GoumaiPublishActivity extends BaseActivity {



    @BindView(R.id.grv_sblx_check)
    GridView grv_sblx_check;

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
    @BindView(R.id.edt_publish_34)
    EditText edt_publish_34;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_33)
    EditText edt_publish_33;


    @BindView(R.id.txt_publish_10)
    TextView txt_publish_10;
    @BindView(R.id.txt_publish_19_1)
    TextView txt_publish_19_1;
    @BindView(R.id.txt_publish_17_1)
    TextView txt_publish_17_1;
    @BindView(R.id.txt_publish_35_1)
    TextView txt_publish_35_1;



    @BindView(R.id.lil_publish_03)
    LinearLayout lil_publish_03;


    @BindView(R.id.spr_publish_10)
    Spinner spr_publish_10;
    @BindView(R.id.spr_publish_08)
    Spinner spr_publish_08;
    @BindView(R.id.spr_publish_18)
    Spinner spr_publish_18;
    @BindView(R.id.spr_publish_16)
    Spinner spr_publish_16;
    @BindView(R.id.spr_publish_36)
    Spinner spr_publish_36;

    @BindView(R.id.rbn_geren)
    RadioButton rbn_geren;
    @BindView(R.id.rbn_gongshi)
    RadioButton rbn_gongshi;

    // 品牌列表
    List<MachineBrandBean> machineBrandBeans = new ArrayList<>();
    //设备类型
    List<MachineTypeBean> machineTypeBeans = new ArrayList<>();

    private String machineBrand;
    private String standard;
    private String factoryDate;
    private String arrivalTime;
    private String paymentMethod;
    private String workTimeUint;
    private String machineType;
    private String fbShebeidunwei;
    private String fb_province;
    private String fb_city;
    private String fb_county;





    private List<String> sblxCheckBoxList = new ArrayList<>();

    @Override
    public Object offerLayout() {
        return R.layout.goumai_activity_publish;
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



        spr_publish_08.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(machineBrandBeans.size()!=0){
                    machineBrand = machineBrandBeans.get(position).getMbId();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        spr_publish_10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fbShebeidunwei = getResources().getStringArray(R.array.shebeidunwei)[position].trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spr_publish_36.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                workTimeUint = getResources().getStringArray(R.array.gongzuoshichang1)[position].trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spr_publish_16.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                paymentMethod = getResources().getStringArray(R.array.fukuanfangshi)[position].trim();
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
        getMachineBrandAll();
    }

    @Override
    public void destory() {

    }

    @OnClick({R.id.img_back,R.id.txt_verify_publish,R.id.txt_publish_19_1,R.id.txt_publish_17_1,R.id.txt_publish_35_1,
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(GoumaiPublishActivity.this,DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        factoryDate = year + "-" + (monthOfYear+1) + "-" + dayOfMonth;
                        txt_publish_17_1.setText(factoryDate);

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            case R.id.txt_publish_35_1:
                Calendar calendar2 = Calendar.getInstance();
                DatePickerDialog datePickerDialog2 = new DatePickerDialog(GoumaiPublishActivity.this,DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        arrivalTime = year + "-" + (monthOfYear+1) + "-" + dayOfMonth;
                        txt_publish_35_1.setText(arrivalTime);

                    }
                }, calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH));
                datePickerDialog2.show();
                break;
        }
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
                    CheckBoxAdapter sblxCheckBoxAdapter = new CheckBoxAdapter(machineList, context, new CheckBoxAdapter.OnClickCheckChanged() {
                        @Override
                        public void onCheckedChanged(int postion, boolean isCheck) {
//                            if(sblxCheckBoxList.size()>0) {
//                                for (int i = 0; i < machineTypeBeans.size(); i++) {
//                                    if (machineTypeBeans.get(postion).getMbId().equals(sblxCheckBoxList.get(postion-1))) {
//                                        if (!isCheck) {
//                                            sblxCheckBoxList.remove(i);
//                                            return;
//                                        }
//                                    }
//                                }
//                            }
                            sblxCheckBoxList.add("1");
                        }
                    });
                    grv_sblx_check.setAdapter(sblxCheckBoxAdapter);
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
                    spr_publish_08.setAdapter(new ArrayAdapter<>(GoumaiPublishActivity.this, android.R.layout.simple_spinner_item, machineList) );
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
     * 验证信息
     */
    private void verifyPublishDialog(){


        if(txt_publish_19_1.getText().toString().isEmpty()){
            ToastUtils.showLong("请选择设备停靠省市区");
            return;
        }

        CommomDialog commomDialog = new CommomDialog(GoumaiPublishActivity.this, "确认发布此条承租信息吗", "", (dialog, confirm) -> {
            if (confirm) {
                getRentOutAdd();
            }
        });
        commomDialog.show();
    }








    /**
     * 获取产品信息
     */
    public void getRentOutAdd(){

        if(txt_publish_19_1.getText().toString().isEmpty()){
            ToastUtils.showLong("请选择设备停靠省市区");
            return;
        }


        Map<String,Object> rentOutMap = new HashMap<>();
        rentOutMap.put("bId","");
        rentOutMap.put("title",edt_publish_01.getText().toString());
        rentOutMap.put("coverImage","xxxxxx");
        if(rbn_geren.isChecked()) {
            rentOutMap.put("rentFrom", "个人");
            rentOutMap.put("contactPerson", edt_publish_04.getText().toString());
//            rentOutMap.put("companyName", "");
        }else{
            rentOutMap.put("rentFrom", "公司");
//            rentOutMap.put("companyName", edt_publish_03.getText().toString());
            rentOutMap.put("contactPerson", edt_publish_04.getText().toString());
        }

        rentOutMap.put("contactPhone",edt_publish_05.getText().toString());

        rentOutMap.put("price", edt_publish_12.getText().toString());

        rentOutMap.put("tonnage",fbShebeidunwei);
        rentOutMap.put("count",edt_publish_33.getText().toString());
        rentOutMap.put("workTime",edt_publish_16.getText().toString());
        rentOutMap.put("workTimeUint",workTimeUint);
        rentOutMap.put("standard",standard);
//        rentOutMap.put("factoryDate",factoryDate);
        rentOutMap.put("arrivalTime",arrivalTime);
        rentOutMap.put("paymentMethod",paymentMethod);
        rentOutMap.put("mbId",machineBrand);

        rentOutMap.put("province",fb_province);
        rentOutMap.put("city",fb_city);
        rentOutMap.put("county",fb_county);
        rentOutMap.put("address",edt_publish_20.getText().toString());
        rentOutMap.put("describes",edt_publish_34.getText().toString());
        rentOutMap.put("uId", SPStaticUtils.getInt(Constants.SP_USER_ID));
        for(int i = 0; i < sblxCheckBoxList.size();i++){
            rentOutMap.put("typeList["+i+"].mtId",sblxCheckBoxList.get(i));
        }
//        rentOutMap.put("pictureList","");

        Api request = getRetrofit().create(Api.class);
        Call<JsonEntity> call = request.buyAdd(rentOutMap);
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
