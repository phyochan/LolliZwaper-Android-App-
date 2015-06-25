package mmsd.phyochan.lollizwaper.Root;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.gc.materialdesign.views.ButtonRectangle;
import com.gc.materialdesign.views.ProgressBarDeterminate;
import com.stericson.RootTools.RootTools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import mmsd.phyochan.lollizwaper.TextView.TharLonTextView;

/**
 * Created by phyochan on 5/18/2015.
 */

public class DownloadApp extends AsyncTask<String, Integer, String> {

    ProgressBarDeterminate progressBarDeterminate;

    TharLonTextView txtdownloading;

    String appname;

    Context context;

    String tempfolder = "/LolliZwaper";

    ButtonRectangle open;

    ButtonRectangle install;

    String su = new String();

    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
    File temp = new File(extStorageDirectory + tempfolder);

    public  DownloadApp(Context context,ProgressBarDeterminate progressBarDeterminate,TharLonTextView txtdownloading,String appname,ButtonRectangle open,ButtonRectangle install){


        this.context = context;

       this.progressBarDeterminate = progressBarDeterminate;

        this.txtdownloading = txtdownloading;

        this.appname = appname;

        this.open = open;

        this.install = install;

    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();

progressBarDeterminate.setVisibility(View.VISIBLE);

        txtdownloading.setVisibility(View.VISIBLE);




    }

    @Override
    protected String doInBackground(String... Url) {
        try {
            URL url = new URL(Url[0]);
            URLConnection connection = url.openConnection();
            connection.connect();


            int fileLength = connection.getContentLength();





            InputStream input = new BufferedInputStream(url.openStream());





            temp.mkdir();


            OutputStream output = new FileOutputStream(temp + "/"
                    + appname);

            byte data[] = new byte[1024];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                total += count;

                publishProgress((int) (total * 100 / fileLength));
                output.write(data, 0, count);
            }


            output.flush();
            output.close();
            input.close();
        } catch (Exception e) {
            // Error Log
            Log.e("Error", e.getMessage());


            File file = new File(temp + "/" + appname);
            boolean deleted = file.delete();

            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);


        progressBarDeterminate.setProgress(progress[0]);

        txtdownloading.setText("Downloading . . .  " + progress[0] + "%");


    }


    @Override
    protected void onPostExecute(String result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);

        progressBarDeterminate.setVisibility(View.GONE);

        txtdownloading.setVisibility(View.GONE);




        if(RootTools.isRootAvailable()){





                if (new File("/system/bin/su").exists()
                        || new File("/system/xbin/su").exists())
                    su = "su ";
                else {
                    su = "sh ";
                }





                Runme.Cmd(su, "pm install -r " + temp + "/" + appname);


                open.setEnabled(true);
                install.setEnabled(false);


        }else {


            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + tempfolder + "/" + appname)), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

            open.setEnabled(true);
            install.setEnabled(false);



        }


    }
}


