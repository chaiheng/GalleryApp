package com.example.modulebase.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

public class SystemUtils {

    public static boolean isInstalled(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        List<PackageInfo> packageInfoList = manager.getInstalledPackages(0);
        if (packageInfoList != null) {
            for (int i = 0; i < packageInfoList.size(); i++) {
                String package_name = packageInfoList.get(i).packageName;
                if (package_name.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }
//    /**
//     * 检测是否安装支付宝
//     * @param context
//     * @return
//     */
//    public static boolean isAliPayInstalled(Context context) {
//        Uri uri = Uri.parse("alipays://platformapi/startApp");
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        ComponentName componentName = intent.resolveActivity(context.getPackageManager());
//        return componentName != null;
//    }
//
//    /**
//     * 检测是否安装微信
//     * @param context
//     * @return
//     */
//    public static boolean isWeixinAvilible(Context context) {
//        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
//        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
//        if (pinfo != null) {
//            for (int i = 0; i < pinfo.size(); i++) {
//                String pn = pinfo.get(i).packageName;
//                if (pn.equals("com.tencent.mm")) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
}
