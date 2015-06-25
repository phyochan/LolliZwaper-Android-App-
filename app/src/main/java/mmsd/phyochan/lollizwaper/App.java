package mmsd.phyochan.lollizwaper;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by phyochan on 5/18/2015.
 */
public class App  {


    public static boolean isPackageInstalled(String packagename, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


}
