package cn.org.bjca.signet.component.core.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.eiffelyk.signetfulldemo.R;

import cn.org.bjca.signet.component.core.activity.SignetCoreApi;
import cn.org.bjca.signet.component.core.bean.results.SignDataPinResult;
import cn.org.bjca.signet.component.core.bean.results.SignDataResult;
import cn.org.bjca.signet.component.core.callback.SignDataBackPinCallBack;
import cn.org.bjca.signet.component.core.callback.SignDataCallBack;
import cn.org.bjca.signet.component.core.callback.SignDataWithPinCallBack;
import cn.org.bjca.signet.component.core.enums.AlgoPolicy;
import cn.org.bjca.signet.component.core.enums.DataType;
import cn.org.bjca.signet.component.core.enums.SignType;

/**
 * 签名相关接口功能调用
 */
public class TestSignDataApiActivity extends Activity implements View.OnClickListener {

    private Button btnSignData,btnSignDataWithPin,btnSignDataBackPin, btnQRSignData,  btnOfflineSign;

    private Context mContext=TestSignDataApiActivity.this;
    private String msspID = "6db08148727abefb55bef4ab05b7f8f8f976094a7ef6f64a24645d236d5d169c";   //用户在云服务系统中的唯一标识
    //    private String msspID="ENA_2386699a-acce-4144-a5d9-16fafd167c55";   //用户在云服务系统中的唯一标识，企业用户一般以"ENA_"开头
//    String signId="SD_778ede55-7c1e-49c3-846b-ae7cf1bd35ef";   //签名任务ID，由业务应用通过网关向云服务后台发起请求，云服务后台返回，具体参见集成方案。登录任务ID有效期三天
    String signId="SD_38a27a74-df80-45c8-8ce0-8a0f344a5311";   //签名任务ID，由业务应用通过网关向云服务后台发起请求，云服务后台返回，具体参见集成方案。登录任务ID有效期三天
    private String pin="123123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiey_test_api_signdata);

        btnSignData=findViewById(R.id.btn_api_signdata);
        btnSignData.setOnClickListener(this);

        btnSignDataWithPin = findViewById(R.id.btn_api_signdata_withpin);
        btnSignDataWithPin.setOnClickListener(this);

        btnSignDataBackPin=findViewById(R.id.btn_api_signdata_backpin);
        btnSignDataBackPin.setOnClickListener(this);

        btnQRSignData = findViewById(R.id.btn_api_qr_signdata);
        btnQRSignData.setOnClickListener(this);

        btnOfflineSign = findViewById(R.id.btn_api_offline_sign);
        btnOfflineSign.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //使用传入签名任务ID的方式完成签名
            case R.id.btn_api_signdata:
                //接口调用
                SignetCoreApi.useCoreFunc(new SignDataCallBack(mContext,msspID,signId) {
                    @Override
                    public void onSignDataResult(SignDataResult signDataResult) {
                        DemoUtils.toastInfo(mContext,signDataResult);
                    }
                });

                break;

            //调用签名接口，使用传入pin码的方式完成一次电子签名
            case R.id.btn_api_signdata_withpin:
                //接口调用
                SignetCoreApi.useCoreFunc(new SignDataWithPinCallBack(TestSignDataApiActivity.this, msspID, signId, pin) {
                    @Override
                    public void onSignDataPinResult(final SignDataPinResult result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                DemoUtils.toastInfo(mContext,result);
                            }
                        });
                    }
                });

                break;

            //调用签名接口，以传入签名任务ID的方式完成签名，并在签名完成后返回pin码
            case R.id.btn_api_signdata_backpin:
                //接口调用
                SignetCoreApi.useCoreFunc(new SignDataBackPinCallBack(mContext,msspID,signId) {
                    @Override
                    public void onSignDataPinResult(SignDataPinResult result) {
                        DemoUtils.toastInfo(mContext,result);
                    }
                });

                break;

            //扫码对业务系统的签名任务进行签名
            case R.id.btn_api_qr_signdata:
                //接口调用
                SignetCoreApi.useCoreFunc(new SignDataCallBack(mContext, msspID) {
                    @Override
                    public void onSignDataResult(SignDataResult signDataResult) {
                       DemoUtils.toastInfo(mContext,signDataResult);
                    }
                });

                break;

            //离线签名接口，需业务应用在云服务后台开通离线证书功能并提前下载好离线证书
            case R.id.btn_api_offline_sign:

                //接口调用
                SignetCoreApi.useCoreFunc(new SignDataCallBack(this, msspID, AlgoPolicy.SM3withSM2, DataType.CLEAR_DATA, SignType.AUTH, "MTIzMTIzMTIzMzQyNTUzNDI1MzQ1") {
                    @Override
                    public void onSignDataResult(SignDataResult signDataResult) {
                        DemoUtils.toastInfo(mContext,signDataResult);
                    }

                });

                break;

            default:
                break;
        }

    }

}
