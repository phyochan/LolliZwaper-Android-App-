package mmsd.phyochan.lollizwaper.Root;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.gc.materialdesign.views.ProgressBarDeterminate;

import java.util.ArrayList;
import java.util.HashMap;

import mmsd.phyochan.lollizwaper.R;
import mmsd.phyochan.lollizwaper.RootApp;
import mmsd.phyochan.lollizwaper.TextView.TharLonTextView;


public class ListViewAdapter extends BaseAdapter {



    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultp = new HashMap<String, String>();



    String downloadlink;

    String filename;

    Intent intent;



    public ListViewAdapter(Context context,Intent intent,
                           ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        this.intent = intent;
        data = arraylist;
        imageLoader = new ImageLoader(context);
    }


    public int getCount() {
        return data.size();
    }


    public Object getItem(int position) {
        return null;
    }


    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {


        final ViewHolder holder;



if(convertView == null){


    holder = new ViewHolder();



    inflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    convertView = inflater.inflate(R.layout.lst_item, parent, false);



    holder.appicon = (ImageView) convertView.findViewById(R.id.imgappicon);

    holder.appname = (TharLonTextView) convertView.findViewById(R.id.txtappname);

    holder.bntinstall = (ButtonRectangle) convertView.findViewById(R.id.bntinstall);

    holder.bntopen = (ButtonRectangle) convertView.findViewById(R.id.bntopen);

    holder.progressBarDeterminate = (ProgressBarDeterminate) convertView.findViewById(R.id.progressDeterminate);

    holder.txtdownloading = (TharLonTextView) convertView.findViewById(R.id.txtdownloading);


holder.progressBarDeterminate.setBackgroundColor(Color.parseColor("#607D8B"));


    convertView.setTag(holder);

}else{


    holder = (ViewHolder)convertView.getTag();
}


        resultp = data.get(position);

        imageLoader.DisplayImage(resultp.get(RootApp.AppIcon), holder.appicon);




        holder.appname.setText(resultp.get(RootApp.AppName));


        downloadlink = resultp.get(RootApp.DownloadLink);


        filename = resultp.get(RootApp.FileName);

        resultp = data.get(position);



        holder.bntinstall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                resultp = data.get(position);

                holder.bntinstall.setEnabled(false);

               final DownloadApp downloadApp = new DownloadApp(context,holder.progressBarDeterminate,holder.txtdownloading,resultp.get(RootApp.FileName),holder.bntopen,holder.bntinstall);

                downloadApp.execute(resultp.get(RootApp.DownloadLink));

            }
        });


        return convertView;
    }




    public static class ViewHolder{



        ImageView appicon;

        ButtonRectangle bntinstall;

        ButtonRectangle bntopen;


        ProgressBarDeterminate progressBarDeterminate;




        TharLonTextView appname,txtdownloading;


    }



}
