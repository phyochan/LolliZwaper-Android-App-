package mmsd.phyochan.lollizwaper.FontChanger;

import android.content.Context;

import com.stericson.RootTools.RootTools;

import mmsd.phyochan.lollizwaper.Root.RootErrorDialog;
import mmsd.phyochan.lollizwaper.Root.RootOkDialog;

/**
 * Created by phyochan on 5/26/2015.
 */
public class StartInstall {


    public static void OK(Context context){

        if(RootTools.isRootAvailable()){

            RootOkDialog.Show(context);


        }else {

            RootErrorDialog.Show(context);


        }
    }

}
