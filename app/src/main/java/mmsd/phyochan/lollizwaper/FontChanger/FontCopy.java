package mmsd.phyochan.lollizwaper.FontChanger;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by phyochan on 12/19/2014.
 */
public class FontCopy {


    public static void start (Context context,String fontname){



        String tempfolder = "/LolliZwaper";
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        File temp = new File(extStorageDirectory + tempfolder);



        if(!temp.exists()){

            temp.mkdir();

        }

        AssetManager assetManager = context.getAssets();
        String[] files = null;
        InputStream in = null;
        OutputStream out = null;


        try
        {
            in = assetManager.open("file/"+fontname);



            out = new FileOutputStream(Environment.getExternalStorageDirectory() +"/LolliZwaper" + "/" + fontname);




            copyFile(in, out);


            in.close();
            in = null;


            out.flush();
            out.close();
            out = null;



        }
        catch(IOException e)
        {

        }




    }

    private static void copyFile(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }

    }





    }
