package cn.org.bjca.signet.component.core.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.eiffelyk.signetfulldemo.R;

import java.util.HashMap;

import cn.org.bjca.signet.sdk.OfflineSignResultEntity;
import cn.org.bjca.signet.sdk.ResultEntity;
import cn.org.bjca.signet.sdk.SignetCallBack;
import cn.org.bjca.signet.sdk.SignetSDKInstance;
import cn.org.bjca.signet.sdk.UserEntity;

//import cn.org.bjca.signet.sdk.MessageControler;

//import cn.org.bjca.signet.helper.bean.OCRInfoBean;
//import cn.org.bjca.signet.sdk.MessageControler;

public class OldActivity extends Activity implements SignetCallBack {

    @Override
    public void setHandWritingCallBack(ResultEntity result) {

    }

    @Override
    public void selfRegisterCallBack(ResultEntity result) {

    }

    @Override
    public void setFingerCallBack(ResultEntity result) {

    }

    @Override
    public void selfRegInfoCallBack(UserEntity result) {

    }

    @Override
    public void selfRegGetOcrCallBack(cn.org.bjca.signet.helper.bean.OCRInfoBean result) {

    }


    @Override
    public void enterPriseSealCallBack(ResultEntity result) {

    }

    @Override
    public void enterPriseSealSingleCallBack(ResultEntity result) {

    }

    @Override
    public void enterPriseSealImageSingleCallBack(ResultEntity result) {

    }

    @Override
    public void reqOfflineCertCallBack(ResultEntity result) {

    }

    @Override
    public void offlineSignCallBack(OfflineSignResultEntity result) {

    }

    @Override
    public void getDocuInfoCallBack(ResultEntity result) {

    }

    private Button btnReg, btnFindBack, btnSign, btnSelfReg;
    private SignetSDKInstance sdkInstance;
    private String TAG = this.getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old);
        sdkInstance = SignetSDKInstance.getInstance("67f6c99b74484c2b8bcb35473850cdd7");

        btnReg = findViewById(R.id.btn_old_reg);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String activeCode = "{\"version\":\"2.0\",\"operType\":\"ActiveEnterpriseOperator\",\"data\":\"4bf251ad\"}";
//                sdkInstance.register(OldActivity.this,activeCode);
                sdkInstance.qrRegister(OldActivity.this);
                HashMap<String, String> userList = sdkInstance.getUserList(OldActivity.this);
                Log.e("TAG", userList.toString());
            }
        });

        btnFindBack = findViewById(R.id.btn_old_findback);
        btnFindBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sdkInstance.findBackUser(OldActivity.this, "王站", "330902198403267012");
            }
        });

        btnSign = findViewById(R.id.btn_old_sign);
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                sdkInstance.login(OldActivity.this,"ENA_849efa8f-b89c-4fb7-98d5-fbeac19e7217","SD_b7768f34-f960-4c81-9936-ed7cd154c464");
//                sdkInstance.signData(OldActivity.this, "ENA_849efa8f-b89c-4fb7-98d5-fbeac19e7217", "SD_b7768f34-f960-4c81-9936-ed7cd154c464");
                sdkInstance.qrSignData(OldActivity.this,"ENA_849efa8f-b89c-4fb7-98d5-fbeac19e7217");
//                sdkInstance.qrLogin(OldActivity.this,"ENA_849efa8f-b89c-4fb7-98d5-fbeac19e7217");
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        MessageControler.messageHandle(this,data);
    }

    @Override
    public void loginCallBack(ResultEntity result) {
        Log.e(TAG, result.getMsg());
    }

    @Override
    public void qrLoginCallBack(ResultEntity result) {
        Log.e(TAG, result.getMsg());
    }

    @Override
    public void registerCallBack(ResultEntity result) {
        Log.e(TAG, result.getMsg());
    }

    @Override
    public void qrRegisterCallBack(ResultEntity result) {
        Log.e(TAG, result.getMsg());
    }

    @Override
    public void signDataCallBack(ResultEntity result) {
        Log.e(TAG, result.toString());
    }

    @Override
    public void qrSignDataCallBack(ResultEntity result) {
        Log.e("123","123");
    }

    @Override
    public void findBackUserCallBack(ResultEntity result) {
        Log.e(TAG, result.toString());
    }

    @Override
    public void signDocuCallBack(ResultEntity result) {

    }

}
