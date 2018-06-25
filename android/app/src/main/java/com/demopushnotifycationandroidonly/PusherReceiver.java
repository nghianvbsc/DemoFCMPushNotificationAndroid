package com.demopushnotifycationandroidonly;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import java.util.List;

/**
 * Created by nguyenvannghia on 6/25/18
 */
public class PusherReceiver extends WakefulBroadcastReceiver {
    private boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }

    public void onReceive(final Context context, Intent intent) {
        if (!isAppIsInBackground((context))) {
            Bundle bundle = intent.getExtras();

            for (String key : bundle.keySet()) {
                Log.w("YGASYSAVTSASA", key + " = \"" + bundle.get(key) + "\"");
            }

            Intent service = new Intent(context, BackgroundService.class);
            service.putExtra("titke", bundle.getString("google.c.a.c_l"));
            service.putExtra("message", bundle.getString("gcm.notification.body"));
            // Put here your data from the json as extra in in the intent

            startWakefulService(context, service);
        }
    }
}