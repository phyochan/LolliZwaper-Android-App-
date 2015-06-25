package mmsd.phyochan.lollizwaper.Dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by phyochan on 5/26/2015.
 */
public class ErrorDialog {



    public static void Show(Context context,String title,String message){

        AlertDialog.Builder alBuilder = new AlertDialog.Builder(context);


        alBuilder.setTitle(title);

        alBuilder.setMessage(message);

        alBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        alBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        alBuilder.show();

    }
}

