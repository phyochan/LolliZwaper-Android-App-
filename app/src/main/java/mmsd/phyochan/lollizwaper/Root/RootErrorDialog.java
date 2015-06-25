package mmsd.phyochan.lollizwaper.Root;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import mmsd.phyochan.lollizwaper.Dialog.ErrorDialog;
import mmsd.phyochan.lollizwaper.NetWorkCheck;
import mmsd.phyochan.lollizwaper.R;

/**
 * Created by phyochan on 5/26/2015.
 */
public class RootErrorDialog {


    public static void Show(final Context context){

        AlertDialog.Builder dgrooterror = new AlertDialog.Builder(context);

       dgrooterror.setView(R.layout.rootdg);

        dgrooterror.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                if(NetWorkCheck.isInternetAvailable(context)){


                    Intent rootapp = new Intent("mmsd.phyochan.lollizwaper.RootApp");

                    context.startActivity(rootapp);


                }else {

                    ErrorDialog.Show(context,"Error!","Please open internet connection.");

                }


            }
        });

        dgrooterror.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });

        dgrooterror.show();


    }

}
