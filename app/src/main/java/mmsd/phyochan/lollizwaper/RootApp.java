package mmsd.phyochan.lollizwaper;

import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;
import com.startapp.android.publish.Ad;
import com.startapp.android.publish.AdEventListener;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;
import com.startapp.android.publish.nativead.NativeAdDetails;
import com.startapp.android.publish.nativead.NativeAdPreferences;
import com.startapp.android.publish.nativead.StartAppNativeAd;
import com.startapp.android.publish.splash.SplashConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import mmsd.phyochan.lollizwaper.Root.JSONfunctions;
import mmsd.phyochan.lollizwaper.Root.ListViewAdapter;

/**
 * Created by phyochan on 5/15/2015.
 */
public class RootApp extends AppCompatActivity {

   public static String AppIcon = "appicon";

   public static String AppName = "appname";



 public static String DownloadLink = "downloadlink";

 public static String FileName = "filename";

    JSONObject jsonobject;
    JSONArray jsonarray;
    ListView listview;
    ListViewAdapter adapter;

    ArrayList<HashMap<String, String>> arraylist;

    ProgressWheel loading;

    private StartAppAd startAppAd = new StartAppAd(this);

    /** StartApp Native Ad declaration */
    private StartAppNativeAd startAppNativeAd = new StartAppNativeAd(this);
    private NativeAdDetails nativeAd = null;

    private ImageView imgFreeApp = null;
    private TextView txtFreeApp = null;


   DownloadJSON applist = new DownloadJSON();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        StartAppSDK.init(this, "104444460", "204277447", true); //TODO: Replace with your IDs

        /** Create Splash Ad **/
        StartAppAd.showSplash(this, savedInstanceState,
                new SplashConfig()
                        .setTheme(SplashConfig.Theme.GLOOMY)
                        .setLogo(R.drawable.logo)
                        .setAppName("LolliZwaper")
        );

setContentView(R.layout.root_app);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        StartAppAd.showSlider(this);

      loading = (ProgressWheel) findViewById(R.id.progress_wheel);


         applist.execute();






        startAppNativeAd.loadAd(
                new NativeAdPreferences()
                        .setAdsNumber(1)
                        .setAutoBitmapDownload(true)
                        .setImageSize(NativeAdPreferences.NativeAdBitmapSize.SIZE150X150),

                nativeAdListener);




    }








    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();



            loading.setVisibility(View.VISIBLE);


        }

        @Override
        protected Void doInBackground(Void... params) {

            arraylist = new ArrayList<HashMap<String, String>>();

            jsonobject = JSONfunctions
                    .getJSONfromURL("http://mmsdromfinder.com/lollizwaper/rootapp.json");

            try {

                jsonarray = jsonobject.getJSONArray("MMSD Rom Finder");

                for (int i = 0; i < jsonarray.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    jsonobject = jsonarray.getJSONObject(i);

                    map.put("appicon", jsonobject.getString("appicon"));

                    map.put("appname",jsonobject.getString("appname"));


                    map.put("downloadlink",jsonobject.getString("downloadlink"));

                    map.put("filename",jsonobject.getString("filename"));



                    arraylist.add(map);
                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());




                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {

            listview = (ListView) findViewById(R.id.lst);

            adapter = new ListViewAdapter(RootApp.this,getIntent(), arraylist);

            listview.setAdapter(adapter);

            loading.setVisibility(View.INVISIBLE);

            listview.setVisibility(View.VISIBLE);


        }


    }

    @Override
    public void onPause() {
        super.onPause();


    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("title", "Some Text");

    }



    @Override
    public void onStop() {
        super.onStop();


 this.applist.cancel(true);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

 this.applist.cancel(true);
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
                nativeAd.sendImpression(RootApp.this);

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
