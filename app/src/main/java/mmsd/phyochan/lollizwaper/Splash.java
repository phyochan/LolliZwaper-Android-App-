package mmsd.phyochan.lollizwaper;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.gc.materialdesign.views.ProgressBarDeterminate;

import mmsd.phyochan.lollizwaper.TextView.TharLonTextView;


/**
 * Created by phyochan on 11/20/2014.
 */
public class Splash extends AppCompatActivity implements SplashLoading.FinishedListener{



    static boolean active = false;

    ProgressBarDeterminate progressBarDeterminate;

    TharLonTextView txtloading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);

getSupportActionBar().hide();


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        progressBarDeterminate = (ProgressBarDeterminate) findViewById(R.id.progressDeterminate);

        txtloading = (TharLonTextView) findViewById(R.id.txtloading);


        new SplashLoading(progressBarDeterminate,this,txtloading).execute();




        Thread timer = new Thread(){

            public void run(){

                try{

                    sleep(4000);

                }catch (InterruptedException e ){

                    e.printStackTrace();


                }finally{

                    Intent main = new Intent("mmsd.phyochan.lollizwaper.Main");
                    startActivity(main);






                }






            }



        };


        timer.start();


    }



    @Override
    public void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        active = false;
    }
    public static boolean isActive(){
        return active;
    }


    @Override
    public void onTaskFinished() {

    }
}
