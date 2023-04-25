package cn.org.bjca.signet.component.core.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.eiffelyk.signetfulldemo.R;

import java.util.List;

import cn.org.bjca.signet.component.core.activity.SignetCoreApi;

/**
 * Demo 工程主页面，跳转至各个模块功能展示页，仅展示接口的调用
 * WARNING：因Demo工程发布后，开发者可能会对工程内数据做各种找回/注销/删除等操作
 * 且部分数据（如签名任务ID等）有时效性，同时测试环境后台数据会做清理，故不保证Demo工程内的数据随时可用
 * 集成前可配合演示平台http://demo.isignet.cn:8080/demo/ （个人版）或http://demo.isignet.cn:8080/demo/enter （企业版）生成新的测试数据进行接口功能的调用/调试
 * 正式集成时具体接口的调用请参照方案与接口文档
 */
public class TestApiActivity extends Activity implements View.OnClickListener {

    private Button btnLifeTime, btnLogin, btnSignData, btnTools, btnOldApi, btnYunSign, btnYunSignByData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_api);

        SignetCoreApi.setShowPrivacyMode(TestApiActivity.this, 1);

        btnLifeTime = findViewById(R.id.btn_lifetime_manage);
        btnLifeTime.setOnClickListener(this);

        btnLogin = findViewById(R.id.btn_login_module);
        btnLogin.setOnClickListener(this);

        btnSignData = findViewById(R.id.btn_signdata_module);
        btnSignData.setOnClickListener(this);

        btnTools = findViewById(R.id.btn_tools_module);
        btnTools.setOnClickListener(this);

        btnOldApi = findViewById(R.id.btn_tools_oldapi);
        btnOldApi.setOnClickListener(this);
        btnYunSign = findViewById(R.id.btn_tools_start_yun);
        btnYunSign.setOnClickListener(this);
        btnYunSignByData = findViewById(R.id.btn_tools_start);
        btnYunSignByData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_lifetime_manage:
                //跳转生命周期相关接口功能展示页
                jumpToActivity(TestLifeTimeApiActivity.class);
                break;
            case R.id.btn_login_module:
                //跳转登录相关接口功能展示页
                jumpToActivity(TestLoginApiActivity.class);
                break;
            case R.id.btn_signdata_module:
                //跳转签名相关接口功能展示页
                jumpToActivity(TestSignDataApiActivity.class);
                break;
            case R.id.btn_tools_module:
                //跳转工具类接口功能展示页
                jumpToActivity(TestToolsApiActivity.class);
                break;
            case R.id.btn_tools_oldapi:
                //旧接口功能展示页：除已集成项目外，不建议调用旧接口
                jumpToActivity(OldActivity.class);
                break;
            case R.id.btn_tools_start_yun:
                Intent intent1 = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("signetdemo://jump.app/open"));
                startActivity(intent1);
                break;
            case R.id.btn_tools_start:
                //云签功能展示页
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("signetdemo://jump.app/open?signJobId=" + "SD_38a27a74-df80-45c8-8ce0-8a0f344a5311" + "&name=" + "唐彪" + "&idNumber" + "500225198309060312"));
                startActivityForResult(intent,10001);
                break;
            default:
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        DemoUtils.Log("onActivityResult requestCode = " + requestCode + " resultCode = " + resultCode+" data = "+data);
    }

    private void jumpToActivity(Class aimClass) {
        Intent intent = new Intent();
        intent.setClass(TestApiActivity.this, aimClass);
        startActivity(intent);
    }

}
