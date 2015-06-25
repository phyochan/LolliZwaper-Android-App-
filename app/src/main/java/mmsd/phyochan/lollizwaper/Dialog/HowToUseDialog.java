package mmsd.phyochan.lollizwaper.Dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import mmsd.phyochan.lollizwaper.R;

/**
 * Created by phyochan on 5/28/2015.
 */
public class HowToUseDialog {


    public static void Show(Context context){


        AlertDialog.Builder htu = new AlertDialog.Builder(context);


        htu.setView(R.layout.htu);

        htu.setTitle("How to Use?");

        htu.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });

htu.show();

    }
}
