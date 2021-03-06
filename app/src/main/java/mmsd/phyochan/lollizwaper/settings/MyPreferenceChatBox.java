package mmsd.phyochan.lollizwaper.settings;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.CheckBoxPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by phyochan on 5/26/2015.
 */
public class MyPreferenceChatBox extends CheckBoxPreference {


    Context context;

    public MyPreferenceChatBox(Context context) {
        super(context);

        this.context = context;


    }

    public MyPreferenceChatBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public MyPreferenceChatBox(Context context, AttributeSet attrs,
                               int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        TextView titleView = (TextView) view.findViewById(android.R.id.title);
        TextView summaryView = (TextView) view.findViewById(android.R.id.summary);
        Button button = (Button) view.findViewById(android.R.id.checkbox);
        titleView.setTextColor(Color.parseColor("#607D8B"));
        summaryView.setTextColor(Color.parseColor("#607D8B"));

        Typeface tharlon=Typeface.createFromAsset(context.getAssets(), "fonts/tharlon.ttf");


        titleView.setTypeface(tharlon);
        summaryView.setTypeface(tharlon);


    }
}
