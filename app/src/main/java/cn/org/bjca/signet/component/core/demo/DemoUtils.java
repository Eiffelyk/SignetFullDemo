package cn.org.bjca.signet.component.core.demo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import cn.org.bjca.signet.component.core.bean.results.SignetBaseResult;

/**
 * @Description:
 * @Author:wangzhan
 * @Time:2019/6/3
 * @Tip:
 */
public class DemoUtils {

    public static void toastInfo(Context mContext, SignetBaseResult result) {
        Toast.makeText(mContext, result.getErrCode() + " : " + result.getErrMsg(), Toast.LENGTH_SHORT).show();
    }

    public static void toastInfo(Context mContext,String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
    public static void Log(String msg){
        Log.e("SignetFullDemo",msg);
    }
}
