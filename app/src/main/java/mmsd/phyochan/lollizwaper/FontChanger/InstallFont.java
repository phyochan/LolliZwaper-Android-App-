package mmsd.phyochan.lollizwaper.FontChanger;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.io.File;

import me.drakeet.materialdialog.MaterialDialog;
import mmsd.phyochan.lollizwaper.R;
import mmsd.phyochan.lollizwaper.TextView.TharLonTextView;

/**
 * Created by phyochan on 5/26/2015.
 */
public class InstallFont extends AsyncTask<Void, Integer, Void> {


MaterialDialog dialog;

    Context context;

    private int i = 0;

    String su = new String();

    TextDrawable drawable;

    TharLonTextView txtprogress;

    String mmsdzawgyi = "mmsdzawgyi.ttf";

    String sulfur = "mmsdsulfur.apk";

    String systemfont = "system/fonts";

    String NotoSansMyanmarBold = "NotoSansMyanmar-Bold.ttf";
    String NotoSansMyanmarRegular = "NotoSansMyanmar-Regular.ttf";
    String NotoSansMyanmarUIBold = "NotoSansMyanmarUI-Bold.ttf";
    String NotoSansMyanmarUIRegular = "NotoSansMyanmarUI-Regular.ttf";
    String SamsungMyanmar = "SamsungMyanmar.ttf";

    String permission = "chmod 644 system/fonts/";



    public InstallFont(Context context){


        this.context = context;

dialog = new MaterialDialog(context);

        View view = LayoutInflater.from(context).inflate(R.layout.dginstall, null);

      drawable   = TextDrawable.builder()
                .beginConfig()
                .withBorder(4)
                .endConfig()
                .buildRound("", Color.parseColor("#607D8B"));

        ImageView image = (ImageView) view.findViewById(R.id.image_view);
        txtprogress = (TharLonTextView) view.findViewById(R.id.txtprogress);

        image.setImageDrawable(drawable);



       dialog.setView(view).show();


    }



    @Override
    protected void onPreExecute () {
        super.onPreExecute();



    }


    @Override
    protected Void doInBackground (Void...params){



        while(i < 100){
            try{

                i++;



                publishProgress(i);




                Thread.sleep(250);

            }catch(Exception e) {
                Log.d("Exception", e.toString());
            }
        }




        return null;
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        txtprogress.setText(i + "%");

        String folder = "/LolliZwaper";
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        File resources = new File(extStorageDirectory + folder);


        if(i == 4){

            FontCopy.start(context,"mmsdzawgyi.ttf");

        }

        if(i == 10){

            FontCopy.start(context, "mmsdsulfur.apk");
        }


        if(i == 15){


            if (new File("/system/bin/su").exists()
                    || new File("/system/xbin/su").exists())
                su = "su ";
            else {
                su = "sh ";
            }

            Runme.Cmd(su, "mount -o remount,rw mount system");

        }


        if(i == 20){

            Runme.Cmd(su, "dd if=" + resources + "/" + mmsdzawgyi +" of=/" + systemfont + "/" + NotoSansMyanmarBold);

        }

        if(i == 25){

            Runme.Cmd(su, "dd if=" + resources + "/" + mmsdzawgyi +" of=/" + systemfont + "/" + NotoSansMyanmarRegular);
        }

        if(i == 35){

            Runme.Cmd(su, "dd if=" + resources + "/" + mmsdzawgyi +" of=/" + systemfont + "/" + NotoSansMyanmarUIBold);
        }


        if (i == 40){

            Runme.Cmd(su, "dd if=" + resources + "/" + mmsdzawgyi +" of=/" + systemfont + "/" + NotoSansMyanmarUIRegular);

        }

        if(i == 45){

            Runme.Cmd(su, "dd if=" + resources + "/" + mmsdzawgyi +" of=/" + systemfont + "/" + SamsungMyanmar);

        }


        if( i == 50){

            Runme.Cmd(su, permission + NotoSansMyanmarBold);
        }

        if(i == 55){

            Runme.Cmd(su, permission + NotoSansMyanmarRegular);
        }

        if(i == 60){

            Runme.Cmd(su, permission + NotoSansMyanmarUIRegular);
        }

        if(i == 65){

            Runme.Cmd(su, permission + NotoSansMyanmarUIBold);
        }



        if(i == 70){

            Runme.Cmd(su, permission + SamsungMyanmar);
        }



        if(i == 75){

            Runme.Cmd(su, "pm install -r " + resources + "/" + sulfur );
        }



        if(i == 98){


            Runme.Cmd(su, "reboot");
        }


    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        dialog.dismiss();
    }
}
