package mmsd.phyochan.lollizwaper.Rabbit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.Toast;

import mmsd.phyochan.lollizwaper.Service.FlotConverter;

/**
 * Created by phyochan on 5/25/2015.
 */
public class RabbitEditText extends EditText {

    private final Context context;


    public RabbitEditText(Context context) {
        super(context);
        this.context = context;

        Typeface tharlon=Typeface.createFromAsset(context.getAssets(), "fonts/tharlon.ttf");
        this.setTypeface(tharlon);
    }

    public RabbitEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        Typeface tharlon=Typeface.createFromAsset(context.getAssets(), "fonts/tharlon.ttf");
        this.setTypeface(tharlon);
    }

    public RabbitEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        Typeface tharlon=Typeface.createFromAsset(context.getAssets(), "fonts/tharlon.ttf");
        this.setTypeface(tharlon);

    }






    @Override
    public boolean onTextContextMenuItem(int id) {
        // Do your thing:
        boolean consumed = super.onTextContextMenuItem(id);
        // React:
        switch (id){
            case android.R.id.cut:
                onTextCut();
                break;
            case android.R.id.paste:
                onTextPaste();
                break;
            case android.R.id.copy:
                onTextCopy();
        }
        return consumed;
    }


    public void onTextCut(){
        Toast.makeText(context, "Cut!", Toast.LENGTH_SHORT).show();
    }

    public void onTextCopy(){
        Toast.makeText(context, "Copy!", Toast.LENGTH_SHORT).show();
    }


    public void onTextPaste(){




        SharedPreferences myPref

                = PreferenceManager.getDefaultSharedPreferences(

                context);



        if(myPref.getBoolean("pref_converter_unicode",false)){


            String unicode = Rabbit.zg2uni(getText().toString());

            setText(unicode);

        }else{



            String zawgyi = Rabbit.uni2zg(getText().toString());

            setText(zawgyi);


        }

    }





}
