package cn.org.bjca.signet.component.core.demo.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.eiffelyk.signetfulldemo.R;

public class ServiceTestActivity extends Activity {

    private Button btnStartService, btnBindService, btnServReg, btnServSignData, btnServGetUserList, btnServUnBind, btnServClose;

    private TestService.SignetBinder signetBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);

        btnStartService = findViewById(R.id.btn_startService);
        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ServiceTestActivity.this, TestService.class);
                startService(intent);
            }
        });

        btnBindService = findViewById(R.id.btn_bindService);
        btnBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceTestActivity.this, TestService.class);
                bindService(intent, servConnection, Context.BIND_AUTO_CREATE);
            }
        });

        btnServReg = findViewById(R.id.btn_serv_activeuser);
        btnServReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String activeCode = "{\"version\":\"2.0\",\"operType\":\"ActiveEnterpriseOperator\",\"data\":\"2ea006e9\"}";


                signetBinder.regFromServ(activeCode);
            }
        });

        btnServSignData = findViewById(R.id.btn_serv_signdata);
        btnServSignData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnServGetUserList = findViewById(R.id.btn_serv_getUserList);
        btnServGetUserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnServUnBind = findViewById(R.id.btn_serv_unbind);
        btnServUnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                unbindService(servConnection);
            }
        });

        btnServClose = findViewById(R.id.btn_serv_close);
        btnServClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ServiceTestActivity.this, TestService.class);
                stopService(intent);
            }
        });
    }

    private ServiceConnection servConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            signetBinder = (TestService.SignetBinder) iBinder;


        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}
