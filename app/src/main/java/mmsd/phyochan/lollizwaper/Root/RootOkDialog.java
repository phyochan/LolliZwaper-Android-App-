package mmsd.phyochan.lollizwaper.Root;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import mmsd.phyochan.lollizwaper.FontChanger.InstallFont;

/**
 * Created by phyochan on 5/26/2015.
 */
public class RootOkDialog {

    public static void Show(final Context context){

        AlertDialog.Builder rootok = new AlertDialog.Builder(context);


        rootok.setTitle("Ready?");

        rootok.setMessage("Are U Ready?");

        rootok.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                InstallFont installFont = new InstallFont(context);

                installFont.execute();

            }
        });

        rootok.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });


        rootok.show();

    }
}
