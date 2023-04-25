package cn.org.bjca.signet.component.core.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.eiffelyk.signetfulldemo.R;

import cn.org.bjca.signet.component.core.activity.SignetCoreApi;
import cn.org.bjca.signet.component.core.bean.results.LoginResult;
import cn.org.bjca.signet.component.core.callback.LoginCallBack;


/**
 * 登录相关接口调用展示
 */
public class TestLoginApiActivity extends Activity implements View.OnClickListener {

    private Context mContext=TestLoginApiActivity.this;
    private Button btnLogin, btnQRLogin;
    private String msspID = "6db08148727abefb55bef4ab05b7f8f8f976094a7ef6f64a24645d236d5d169c";   //用户在云服务系统中的唯一标识
    private String enterMsspID="ENA_2386699a-acce-4144-a5d9-16fafd167c55";   //用户在云服务系统中的唯一标识，企业用户一般以"ENA_"开头
    String signId="SD_cf4c15fe-9ecc-47a6-be24-d4dc5372fdc8";   //登录任务ID，由业务应用通过网关向云服务后台发起请求，云服务后台返回，具体参见集成方案。登录任务ID有效期三天
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_api_login);

        btnLogin = findViewById(R.id.btn_api_login);
        btnLogin.setOnClickListener(this);

        btnQRLogin = findViewById(R.id.btn_api_qr_login);
        btnQRLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //调用登录接口进行APP登录
            case R.id.btn_api_login:
                //接口调用
                SignetCoreApi.useCoreFunc(new LoginCallBack(mContext, msspID, signId) {

                    @Override
                    public void onLoginResult(LoginResult loginResult) {
                        DemoUtils.toastInfo(mContext,loginResult);
                    }
                });

                break;

            //扫码登录业务系统
            case R.id.btn_api_qr_login:

                //接口调用
                SignetCoreApi.useCoreFunc(new LoginCallBack(mContext,msspID) {
                    @Override
                    public void onLoginResult(LoginResult loginResult) {
                        DemoUtils.toastInfo(mContext,loginResult);
                    }
                });

                break;
            default:
                break;
        }

    }


}
