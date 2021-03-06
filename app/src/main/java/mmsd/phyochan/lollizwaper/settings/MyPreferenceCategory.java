package mmsd.phyochan.lollizwaper.settings;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceCategory;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by phyochan on 5/25/2015.
 */

public class MyPreferenceCategory extends PreferenceCategory {


Context context;

    public MyPreferenceCategory(Context context) {
        super(context);

this.context = context;


    }

    public MyPreferenceCategory(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public MyPreferenceCategory(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        TextView titleView = (TextView) view.findViewById(android.R.id.title);
        titleView.setTextColor(Color.parseColor("#607D8B"));

        Typeface tharlon=Typeface.createFromAsset(context.getAssets(), "fonts/tharlon.ttf");

     titleView.setTypeface(tharlon);

    }
}