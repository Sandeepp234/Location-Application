package locationapplication.com.locationapplication;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

/**
 * Created by SANDIP on 6/14/2018.
 */

public class MyPermission {

    public static boolean checkAndroidPermission(Context context, String permission) {
        int res = context.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    public static void askPermission(Activity activity, String permission, String messageBody, AppPermission appPermission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int hasPermission = ContextCompat.checkSelfPermission(activity, permission);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                if (!activity.shouldShowRequestPermissionRationale(permission)) {
                    UserInterfaceUtil.showPermissionInfo(activity, "Grant permission", messageBody, activity, permission);
                    return;
                }
                activity.requestPermissions(new String[]{permission},
                        Constants.REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
            appPermission.onPermission();
        } else {
            appPermission.onPermission();
        }
    }

   public interface AppPermission{
        void onPermission();
   }


}
