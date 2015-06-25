package mmsd.phyochan.lollizwaper.TextView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * Created by phyochan on 5/10/15.
 */
public class TharLonTextView extends TextView {





    public TharLonTextView(Context context) {
        super(context);


        Typeface myan=Typeface.createFromAsset(context.getAssets(), "fonts/tharlon.ttf");
        this.setTypeface(myan);


    }

    public TharLonTextView(Context context, AttributeSet attrs) {
        super(context, attrs);



        Typeface myan=Typeface.createFromAsset(context.getAssets(), "fonts/tharlon.ttf");
        this.setTypeface(myan);

    }

    public TharLonTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);


        Typeface myan=Typeface.createFromAsset(context.getAssets(), "fonts/tharlon.ttf");
        this.setTypeface(myan);
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);


    }


}
