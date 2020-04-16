package locationapplication.com.locationapplication;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by SANDIP on 6/14/2018.
 */

public class UserInterfaceUtil {

    @RequiresApi(api = Build.VERSION_CODES.M)
    static void showPermissionInfo(Context context, String title, String body, final Activity activity, final String permission) {
        new MaterialDialog.Builder(context)
                .title(title)
                .content(body)
                .positiveText(R.string.button_ok)
                .negativeText(R.string.button_cancel)
                .onPositive((dialog, which) -> {
                    SharedPreferences.Editor editor = PreferenceUtil.getPreferenceEditor(context);
                    if (PreferenceUtil.getSharedPreference(context).contains("PERMISSION")) {
                        String permissionString = PreferenceUtil.getSharedPreference(context).getString("PERMISSION", "");
                        if (permissionString.contains(permission)) {
                            dialog.dismiss();
                            openAppSettings(context);
                        } else {
                            editor.putString("PERMISSION", permissionString + "," + permission);
                            editor.apply();
                            dialog.dismiss();
                            activity.requestPermissions(new String[]{permission}, Constants.REQUEST_CODE_ASK_PERMISSIONS);
                        }
                    } else {
                        editor.putString("PERMISSION", permission);
                        editor.apply();
                        dialog.dismiss();
                        activity.requestPermissions(new String[]{permission}, Constants.REQUEST_CODE_ASK_PERMISSIONS);
                    }
                })
                .onNegative((dialog, which) -> dialog.dismiss())
                .show();
    }

    private static void openAppSettings(Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }


}


