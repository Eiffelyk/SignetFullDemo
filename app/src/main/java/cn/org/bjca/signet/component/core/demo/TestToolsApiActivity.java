package cn.org.bjca.signet.component.core.demo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.core.app.ActivityCompat;

import com.eiffelyk.signetfulldemo.R;

import java.util.Map;

import cn.org.bjca.signet.component.core.activity.SignetCoreApi;
import cn.org.bjca.signet.component.core.activity.SignetToolApi;
import cn.org.bjca.signet.component.core.bean.results.AnalyzeCertResult;
import cn.org.bjca.signet.component.core.bean.results.DocuInfoResult;
import cn.org.bjca.signet.component.core.bean.results.EnterpriseSealBaseInfo;
import cn.org.bjca.signet.component.core.bean.results.EnterpriseSealImg;
import cn.org.bjca.signet.component.core.bean.results.EnterpriseSealTotalInfo;
import cn.org.bjca.signet.component.core.bean.results.GetCertResult;
import cn.org.bjca.signet.component.core.bean.results.GetDeviceIdResult;
import cn.org.bjca.signet.component.core.bean.results.GetUserListResult;
import cn.org.bjca.signet.component.core.bean.results.LiveCheckResult;
import cn.org.bjca.signet.component.core.bean.results.SignImageResult;
import cn.org.bjca.signet.component.core.bean.results.SignetBaseResult;
import cn.org.bjca.signet.component.core.callback.DocuInfoCallBack;
import cn.org.bjca.signet.component.core.callback.EnterpriseSealBaseCallBack;
import cn.org.bjca.signet.component.core.callback.EnterpriseSealImgCallBack;
import cn.org.bjca.signet.component.core.callback.EnterpriseSealTotalCallBack;
import cn.org.bjca.signet.component.core.callback.LiveCheckCallBack;
import cn.org.bjca.signet.component.core.callback.OcrCallBack;
import cn.org.bjca.signet.component.core.callback.SetFingerCallBack;
import cn.org.bjca.signet.component.core.callback.SetSignImageCallBack;
import cn.org.bjca.signet.component.core.enums.CertType;
import cn.org.bjca.signet.component.core.enums.OcrOperType;
import cn.org.bjca.signet.component.core.enums.SetFingerOperType;
import cn.org.bjca.signet.component.core.enums.SetSignImgType;

/**
 * 工具类接口调用展示页
 */
public class TestToolsApiActivity extends Activity implements View.OnClickListener {

    private Button btnEnableFinger, btnDisableFinger, btnSetHandWriting, btnPictureHandWriting, btnGetOcrInfo,
            btnAnalyzeCert, btnGetCert, btnGetDeviceId, btnGetSignImg, btnGetUserList,
            btnGetEnterSealBase, btnGetEnterSealImg, btnGetEnterSealTotal, btnGetDocuInfo, btnGetLiveCheckBestFace;

    private final Context mContext = TestToolsApiActivity.this;
    private final String msspID = "6db08148727abefb55bef4ab05b7f8f8f976094a7ef6f64a24645d236d5d169c";   //用户在云服务系统中的唯一标识
    private final String enterMsspID = "ENA_2386699a-acce-4144-a5d9-16fafd167c55";   //用户在云服务系统中的唯一标识，企业用户一般以"ENA_"开头
    private final int READ_PHONE_STATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_api_tools);

        btnEnableFinger = findViewById(R.id.btn_tool_enable_finger);
        btnEnableFinger.setOnClickListener(this);

        btnDisableFinger = findViewById(R.id.btn_tool_disable_finger);
        btnDisableFinger.setOnClickListener(this);

        btnSetHandWriting = findViewById(R.id.btn_tool_set_handwriting);
        btnSetHandWriting.setOnClickListener(this);

        btnPictureHandWriting = findViewById(R.id.btn_tool_picture_handwriting);
        btnPictureHandWriting.setOnClickListener(this);

        btnGetOcrInfo = findViewById(R.id.btn_tool_ocr_info);
        btnGetOcrInfo.setOnClickListener(this);

        btnAnalyzeCert = findViewById(R.id.btn_tool_analyze_cert);
        btnAnalyzeCert.setOnClickListener(this);

        btnGetCert = findViewById(R.id.btn_tool_get_cert);
        btnGetCert.setOnClickListener(this);

        btnGetDeviceId = findViewById(R.id.btn_tool_get_deviceId);
        btnGetDeviceId.setOnClickListener(this);

        btnGetSignImg = findViewById(R.id.btn_tool_get_signimg);
        btnGetSignImg.setOnClickListener(this);

        btnGetUserList = findViewById(R.id.btn_tool_get_userlist);
        btnGetUserList.setOnClickListener(this);

        btnGetEnterSealBase = findViewById(R.id.btn_tool_get_enterseal_base);
        btnGetEnterSealBase.setOnClickListener(this);

        btnGetEnterSealImg = findViewById(R.id.btn_tool_get_enterseal_img);
        btnGetEnterSealImg.setOnClickListener(this);

        btnGetEnterSealTotal = findViewById(R.id.btn_tool_get_enterseal_total);
        btnGetEnterSealTotal.setOnClickListener(this);

        btnGetDocuInfo = findViewById(R.id.btn_tool_get_docuinfo);
        btnGetDocuInfo.setOnClickListener(this);

        btnGetLiveCheckBestFace = findViewById(R.id.btn_tool_get_livecheck_bestface);
        btnGetLiveCheckBestFace.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //开启指纹签名接口，企业用户不支持开/关指纹操作
            case R.id.btn_tool_enable_finger:

                //接口调用
                SignetCoreApi.useCoreFunc(
                        new SetFingerCallBack(mContext, msspID, SetFingerOperType.ENABLE_FINGER) {

                            @Override
                            public void onSetFingerResult(SignetBaseResult setFingerResult) {
                                DemoUtils.toastInfo(mContext, setFingerResult);

                            }
                        });

                break;

            //开启指纹签名接口，企业用户不支持开/关指纹操作
            case R.id.btn_tool_disable_finger:
                //接口调用
                SignetCoreApi.useCoreFunc(
                        new SetFingerCallBack(mContext, msspID, SetFingerOperType.DISABLE_FINGER) {

                            @Override
                            public void onSetFingerResult(SignetBaseResult setFingerResult) {
                                DemoUtils.toastInfo(mContext, setFingerResult);

                            }
                        });
                break;

            //用户手写设置个人签章（该接口不支持企业用户调用）
            case R.id.btn_tool_set_handwriting:
                //接口调用
                SignetCoreApi.useCoreFunc(
                        new SetSignImageCallBack(mContext, msspID, SetSignImgType.SET_HANDWRITING) {

                            @Override
                            public void onSetSignImageResult(SignImageResult setSignImageResult) {
                                DemoUtils.toastInfo(mContext, setSignImageResult);
                                DemoUtils.Log("signImageSrc=" + setSignImageResult.getSignImageSrc());
                            }

                        });
                break;

            //用户拍照设置个人签章（该接口不支持企业用户调用）
            case R.id.btn_tool_picture_handwriting:
                //接口调用
                SignetCoreApi.useCoreFunc(new SetSignImageCallBack(mContext, msspID, SetSignImgType.PICTURE_HANDWRITING) {
                    @Override
                    public void onSetSignImageResult(SignImageResult setSignImageResult) {
                        DemoUtils.toastInfo(mContext, setSignImageResult);

                    }
                });

                break;

            //获取OCR信息
            case R.id.btn_tool_ocr_info:
                //接口调用
                SignetCoreApi.useCoreFunc(new OcrCallBack(mContext, OcrOperType.GET_INFO) {

                    @Override
                    public void onOcrResult(Map<String, String> ocrResultMap) {
                        DemoUtils.toastInfo(mContext, ocrResultMap.get("ERR_CODE") + " : " + ocrResultMap.get("ERR_MSG"));
                    }
                });
                break;

            //解析证书接口

            case R.id.btn_tool_analyze_cert:

                //用户证书，必须为Base64格式，否则解析失败
                String cert = "MIIDOTCCAiGgAwIBAgIJIBA1AAAAIXwPMA0GCSqGSIb3DQEBBQUAMDExCzAJBgNVBAYTAkNOMQ0wCwYDVQQKDARCSkNBMRMwEQYDVQQDDAptc3NwdGVzdGNhMB4XDTE4MDMyMjEwMzIwMloXDTIzMDMyMjExMzIwMlowdjELMAkGA1UEBgwCQ04xFTATBgNVBAMMDOm5sOa9rea1i+ivlTFQME4GCgmSJomT8ixkAQEMQGFmZmYwYmZiYTFhMzlmYzMwMjU4NjgyMmRhYTVmNTE5ZWVkYTg2NWU0N2ZlMzQ1YzkyY2UwY2FiYmQwZTk5NDMwgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAK7QtIZnncgxd8uZ4TTN6eg/6oQ1Vjd0QG5qlGeU8eznIYPZkEjk0p+SHfkKgwte854DvUPPTeLY8/5FHxbeJAtPR+rfJOD7IV9/hPCMS36OsrtHobfgug7FJJzLYmiz2vCMFDdQA7I6ps53XJ185K/V/FvIcP13jjxxWnxV5X7lAgMBAAGjgZIwgY8wHwYDVR0jBBgwFoAUfgaZPuO42ZCfGehz3Oc2ZlUYtuMwHQYDVR0OBBYEFLxnjmbBzxR958d4ap9FnVW0TLA9MAsGA1UdDwQEAwIGwDBABgNVHSAEOTA3MDUGCSqBHIbvMgICAzAoMCYGCCsGAQUFBwIBFhpodHRwOi8vd3d3LmJqY2Eub3JnLmNuL2NwczANBgkqhkiG9w0BAQUFAAOCAQEAbvKcgiuJpJnN7xfv7nQ1MK3GgP9o4HQq0pGdL2qqo+b2C83ZEvJ8kOq3pXB1ikvDugLU8yILk0QkB5nu9R8Mej6E975Cs+g1D5QQmnjWTHK+aFh3qN8uRpyZK52gnnAcYPvJkEU/KmwSZ3aKlFTyafTnYkhXOnCKapf9pRTm8ZUl8BWjYBcE3P84cqHo+ih4GD7CwROU2dDhdDLNUsoN4MFMvssTBwJMkuegW6Zhp0K/+7X39VRdl5Y11vdQEimCbN+w27MdYys8YdPCX7VUXQ511MOZgoDYFc6WMF5x+V9KPV2socf1DBtcp85lboq4nHwKOaTEEUP4Y/guzjtGfA==";

                //接口调用获取结果
                AnalyzeCertResult result = SignetToolApi.analyzeCert(cert);
                DemoUtils.toastInfo(mContext, result.getInfoMap().toString());
                break;

            //获取证书接口
            case R.id.btn_tool_get_cert:
                //接口调用获取结果
                GetCertResult result1 = SignetToolApi.getUserCert(mContext, msspID, CertType.ALL_CERT);
                DemoUtils.toastInfo(mContext, result1.getUserCertMap().toString());
                DemoUtils.Log("cert=" + result1.getUserCertMap().toString());
                break;

            case R.id.btn_tool_get_deviceId:

                //获取设备唯一标识接口

                //调用接口获取结果

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, READ_PHONE_STATE);
                } else {
                    GetDeviceIdResult result2 = SignetToolApi.getDeviceId(mContext);
                    if (result2.getDeviceId() != null)
                        DemoUtils.toastInfo(mContext, result2.getDeviceId());
                    else
                        DemoUtils.toastInfo(mContext, result2.getErrMsg());
                }
                break;

            //获取用户签章图片编码接口
            case R.id.btn_tool_get_signimg:

                //调用接口获取结果
                SignImageResult result3 = SignetToolApi.getSignImage(mContext, msspID);
                DemoUtils.toastInfo(mContext, result3.getSignImageSrc());
                DemoUtils.Log("signImageSrc=" + result3.getSignImageSrc());
                break;

            //获取设备本地用户列表
            case R.id.btn_tool_get_userlist:

                //调用接口获取结果
                GetUserListResult result4 = SignetToolApi.getUserList(mContext);
                DemoUtils.toastInfo(mContext, result4.getUserListMap().toString());
                break;
            //获取企业签章基本信息接口，该接口仅支持企业用户调用
            case R.id.btn_tool_get_enterseal_base:

                //接口调用获取结果
                SignetCoreApi.useCoreFunc(new EnterpriseSealBaseCallBack(mContext, enterMsspID) {
                    @Override
                    public void onGetEnterpriseSealBase(EnterpriseSealBaseInfo baseInfo) {
                        DemoUtils.toastInfo(mContext, baseInfo.getErrMsg());
                    }
                });
                break;

            //指定企业签章ID，获取签章图片Base64编码接口，该接口仅支持企业用户调用,详见接口文档
            case R.id.btn_tool_get_enterseal_img:

                String imageID = "bde557c131644ac0a7b3704d51cdc87d";
                //接口调用获取结果
                SignetCoreApi.useCoreFunc(new EnterpriseSealImgCallBack(mContext, enterMsspID, imageID) {
                    @Override
                    public void onGetEnterpriseSealImgResult(EnterpriseSealImg result) {
                        DemoUtils.toastInfo(mContext, result.getErrMsg());
                    }
                });
                break;

            //获取企业用户签章全部信息接口，该接口仅支持企业用户调用,详见接口文档
            case R.id.btn_tool_get_enterseal_total:
                //接口调用获取结果
                SignetCoreApi.useCoreFunc(new EnterpriseSealTotalCallBack(mContext, enterMsspID) {
                    @Override
                    public void onEnterpriseSealTotal(EnterpriseSealTotalInfo result) {
                        DemoUtils.toastInfo(mContext, result.getErrMsg());
                    }
                });
                break;

            //获取文档信息接口，包括文档状态、签章位置等信息，详见接口文档
            case R.id.btn_tool_get_docuinfo:

                //文档ID
                String docuId = "DOCU_19e875e8-2d95-4cd2-836e-546d42bb9a48";
                //接口调用获取结果
                SignetCoreApi.useCoreFunc(new DocuInfoCallBack(mContext, enterMsspID, docuId) {
                    @Override
                    public void onGetDocuInfo(DocuInfoResult result) {
                        DemoUtils.toastInfo(mContext, result.getErrMsg());
                    }
                });

                break;

            //获取活体检测最佳人脸图片接口，此接口调用需集成活体检测相关.so库
            case R.id.btn_tool_get_livecheck_bestface:

                //接口调用获取结果
                SignetCoreApi.useCoreFunc(new LiveCheckCallBack(mContext, 3) {
                    @Override
                    public void onLiveCheckResult(LiveCheckResult liveCheckResult) {
                        DemoUtils.toastInfo(mContext, liveCheckResult.getErrMsg());
                    }
                });
                break;

            default:
                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == READ_PHONE_STATE) {
            // 若用户取消授权则grantResults为空，如果你同时申请两个权限，那么grantResults的length就为2，分别记录你两个权限的申请结果
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                GetDeviceIdResult result2 = SignetToolApi.getDeviceId(mContext);
                if (result2.getDeviceId() != null)
                    DemoUtils.toastInfo(mContext, result2.getDeviceId());
                else
                    DemoUtils.toastInfo(mContext, result2.getErrMsg());
            } else {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!this.shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE)) {
                        //用户完全拒绝

                    } else {
                        //用户一直拒绝并一直不勾选“不再提醒”
                        //不执行该权限对应功能模块，也不用提示，因为下次需要权限还会弹出对话框
                    }
                }
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
