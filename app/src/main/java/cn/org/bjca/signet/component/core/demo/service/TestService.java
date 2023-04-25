package cn.org.bjca.signet.component.core.demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import cn.org.bjca.signet.component.core.activity.SignetCoreApi;
import cn.org.bjca.signet.component.core.bean.results.RegisterResult;
import cn.org.bjca.signet.component.core.callback.RegisterCallBack;
import cn.org.bjca.signet.component.core.enums.RegisterType;

public class TestService extends Service {
    private String TAG=TestService.class.getSimpleName();
    private SignetBinder signetBinder=new SignetBinder();
    public TestService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"Service onBind");
        return signetBinder;
    }

    @Override
    public void onCreate() {
        Log.d(TAG,"Service onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"Service onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"Service onDestroy");
        super.onDestroy();
    }

    public class SignetBinder extends Binder{
        public void regFromServ(final String authCode){
            SignetCoreApi.useCoreFunc(new RegisterCallBack(TestService.this,authCode, RegisterType.COORDINATE) {
                @Override
                public void onRegisterResult(RegisterResult result) {
                    Log.d(TAG,result.getErrCode());
                }
            });
        }

        public void signDataFromServ(){

        }

        public void getUserListFromServ(){

        }

    }
}
