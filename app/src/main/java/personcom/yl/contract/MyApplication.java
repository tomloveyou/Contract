package personcom.yl.contract;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

import personcom.yl.contract.runtimepermissions.PermissionsManager;

/**
 * Created by changlianjiuzhou on 17/2/16.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PermissionsManager.getInstance();
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"GdjRnMdw5gMFoaoqAaxLt1uo-gzGzoHsz","tGmv1jDeJuqnGIyY1wKALd3h");
        AVOSCloud.setDebugLogEnabled(true);
    }
}
