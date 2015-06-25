package mmsd.phyochan.lollizwaper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.ClipboardManager;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.startapp.android.publish.Ad;
import com.startapp.android.publish.AdEventListener;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;
import com.startapp.android.publish.nativead.NativeAdDetails;
import com.startapp.android.publish.nativead.NativeAdPreferences;
import com.startapp.android.publish.nativead.StartAppNativeAd;

import java.util.ArrayList;

import info.hoang8f.widget.FButton;
import mmsd.phyochan.lollizwaper.Dialog.ErrorDialog;
import mmsd.phyochan.lollizwaper.Dialog.HowToUseDialog;
import mmsd.phyochan.lollizwaper.FontChanger.StartInstall;
import mmsd.phyochan.lollizwaper.Rabbit.Rabbit;
import mmsd.phyochan.lollizwaper.Rabbit.RabbitEditText;
import mmsd.phyochan.lollizwaper.Service.FlotConverter;
import mmsd.phyochan.lollizwaper.settings.Settings;


public class Main extends AppCompatActivity {

   private RabbitEditText edt;

    private FButton btnclick;

    Typeface tharlon;

    ClipboardManager clipMan;

    private StartAppAd startAppAd = new StartAppAd(this);

    /** StartApp Native Ad declaration */
    private StartAppNativeAd startAppNativeAd = new StartAppNativeAd(this);
    private NativeAdDetails nativeAd = null;

    private ImageView imgFreeApp = null;
    private TextView txtFreeApp = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


      //  CheckPref();

        StartAppSDK.init(this, "104444460", "204277447", true);



        setContentView(R.layout.main);

        StartAppAd.showSlider(this);







        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        final GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
            public boolean onDoubleTap(MotionEvent e) {

              edt.setText("");
                return true;
            }
        });

        edt = (RabbitEditText) findViewById(R.id.edt);

        btnclick = (FButton) findViewById(R.id.btnclick);


       tharlon= Typeface.createFromAsset(getAssets(), "fonts/tharlon.ttf");


btnclick.setTypeface(tharlon);


        edt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                clipMan = (ClipboardManager) getSystemService(v.getContext().CLIPBOARD_SERVICE);


                if (clipMan.hasText()) {

                    Z2U();

                } else {

                    Toast.makeText(Main.this, "Please Copy Some Text", Toast.LENGTH_SHORT).show();
                }


                return false;
            }
        });


        edt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });


        edt.setFocusable(false);


        btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (Build.VERSION.SDK_INT) {

                    case 21:

                        StartInstall.OK(Main.this);

                        break;


                    case 22:

                        StartInstall.OK(Main.this);

                        break;

                    default:

                       ErrorDialog.Show(Main.this, "Error!", "Your device isn't compatible with this app.Because this app can support Lollipop Only!");




                        break;

                }
            }
        });


        startAppNativeAd.loadAd(
                new NativeAdPreferences()
                        .setAdsNumber(1)
                        .setAutoBitmapDownload(true)
                        .setImageSize(NativeAdPreferences.NativeAdBitmapSize.SIZE150X150),

                nativeAdListener);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent i = new Intent(this, Settings.class);
            startActivityForResult(i, 0);


            return true;
        }else if (id == R.id.appdec){


            HowToUseDialog.Show(Main.this);

            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // TODO Auto-generated method stub

        super.onActivityResult(requestCode, resultCode, data);

      CheckPref();

     //   Z2U();



    }



    private void CheckPref(){

        SharedPreferences myPref

                = PreferenceManager.getDefaultSharedPreferences(

                Main.this);


        if(myPref.getBoolean("pref_converter",true)){

            startService(new Intent(Main.this, FlotConverter.class));


        }else{

            stopService(new Intent(Main.this,FlotConverter.class));
        }
    }


    private void Z2U(){


        SharedPreferences myPref

                = PreferenceManager.getDefaultSharedPreferences(

                Main.this);


        if(myPref.getBoolean("pref_converter_unicode",false)){


            String unicode = Rabbit.zg2uni(clipMan.getText().toString());

            edt.setText(unicode);

            Typeface tharlon=Typeface.createFromAsset(getAssets(), "fonts/noto.ttf");
            edt.setTypeface(tharlon);

        }else{



            String zawgyi = Rabbit.uni2zg(clipMan.getText().toString());

            edt.setText(zawgyi);

            Typeface tharlon=Typeface.createFromAsset(getAssets(), "fonts/tharlon.ttf");
            edt.setTypeface(tharlon);



        }

    }

private AdEventListener nativeAdListener = new AdEventListener() {

    @Override
    public void onReceiveAd(Ad ad) {

        // Get the native ad
        ArrayList<NativeAdDetails> nativeAdsList = startAppNativeAd.getNativeAds();
        if (nativeAdsList.size() > 0){
            nativeAd = nativeAdsList.get(0);
        }

        // Verify that an ad was retrieved
        if (nativeAd != null){

            // When ad is received and displayed - we MUST send impression
            nativeAd.sendImpression(Main.this);

            if (imgFreeApp != null && txtFreeApp != null){

                // Set button as enabled
                imgFreeApp.setEnabled(true);
                txtFreeApp.setEnabled(true);

                // Set ad's image
                imgFreeApp.setImageBitmap(nativeAd.getImageBitmap());

                // Set ad's title
                txtFreeApp.setText(nativeAd.getTitle());
            }
        }
    }

    @Override
    public void onFailedToReceiveAd(Ad ad) {

        // Error occurred while loading the native ad
        if (txtFreeApp != null) {
            txtFreeApp.setText("Error while loading Native Ad");
        }
    }
};


}
