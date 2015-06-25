package mmsd.phyochan.lollizwaper.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.text.ClipboardManager;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import mmsd.phyochan.lollizwaper.R;
import mmsd.phyochan.lollizwaper.Rabbit.Rabbit;
import mmsd.phyochan.lollizwaper.Rabbit.RabbitEditText;


public class FlotConverter extends Service {

	public static  int ID_NOTIFICATION = 2018;

	private WindowManager windowManager;
	private ImageView chatHead;
	private PopupWindow pwindo;

	boolean mHasDoubleClicked = false;
	long lastPressTime;
	private Boolean _enable = true;


	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override 
	public void onCreate() {
		super.onCreate();
		

		windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

		chatHead = new ImageView(this);
		
		chatHead.setImageResource(R.drawable.test);


		final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_PHONE,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSLUCENT);

		params.gravity = Gravity.TOP | Gravity.LEFT;
		params.x = 0;
		params.y = 100;

		windowManager.addView(chatHead, params);


		try {
			chatHead.setOnTouchListener(new View.OnTouchListener() {
				private WindowManager.LayoutParams paramsF = params;
				private int initialX;
				private int initialY;
				private float initialTouchX;
				private float initialTouchY;

				@Override public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:

						// Get current time in nano seconds.
						long pressTime = System.currentTimeMillis();


						// If double click...
						if (pressTime - lastPressTime <= 300) {
							createNotification();
							FlotConverter.this.stopSelf();
							mHasDoubleClicked = true;
						}
						else {     // If not double click....
							mHasDoubleClicked = false;
						}
						lastPressTime = pressTime; 
						initialX = paramsF.x;
						initialY = paramsF.y;
						initialTouchX = event.getRawX();
						initialTouchY = event.getRawY();
						break;
					case MotionEvent.ACTION_UP:
						break;
					case MotionEvent.ACTION_MOVE:
						paramsF.x = initialX + (int) (event.getRawX() - initialTouchX);
						paramsF.y = initialY + (int) (event.getRawY() - initialTouchY);
						windowManager.updateViewLayout(chatHead, paramsF);
						break;
					}
					return false;
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		}

		chatHead.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {





				if(_enable == false){


					_enable = true;

					pwindo.dismiss();


				}else if(_enable == true){


					initiatePopupWindow(chatHead);

				}

			}
		});

	}


	private void initiatePopupWindow(View anchor) {
		try {
			final LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
			final View popupView = layoutInflater.inflate(R.layout.popup,null);
		final 	RabbitEditText rabbitEditText = (RabbitEditText) popupView.findViewById(R.id.edt);

			rabbitEditText.setHeight(300);
			rabbitEditText.setWidth(300);

			rabbitEditText.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {


					ClipboardManager clipMan = (ClipboardManager)getSystemService(v.getContext().CLIPBOARD_SERVICE);



					SharedPreferences myPref

							= PreferenceManager.getDefaultSharedPreferences(

						getApplicationContext());



					if(myPref.getBoolean("pref_converter_unicode",false)){



						if(clipMan.hasText()){

							String unicode = Rabbit.zg2uni(clipMan.getText().toString());

							rabbitEditText.setText(unicode);

							Typeface tharlon=Typeface.createFromAsset(getAssets(), "fonts/noto.ttf");
							rabbitEditText.setTypeface(tharlon);


						}else{


							Toast.makeText(getApplicationContext(), "Please Copy Some Text", Toast.LENGTH_SHORT).show();
						}





					}else{


						if(clipMan.hasText()){


							String zawgyi = Rabbit.uni2zg(clipMan.getText().toString());

							rabbitEditText.setText(zawgyi);

							Typeface tharlon=Typeface.createFromAsset(getAssets(), "fonts/tharlon.ttf");
							rabbitEditText.setTypeface(tharlon);

						}else{

							Toast.makeText(getApplicationContext(), "Please Copy Some Text", Toast.LENGTH_SHORT).show();
						}




					}

					return false;
				}
			});

			final GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
				public boolean onDoubleTap(MotionEvent e) {

					rabbitEditText.setText("");
					return true;
				}
			});

			rabbitEditText.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					return gestureDetector.onTouchEvent(event);
				}
			});



			pwindo = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT,  ViewGroup.LayoutParams.WRAP_CONTENT);
			if(_enable == true) {
				_enable = false;
				pwindo.showAsDropDown(chatHead, 10, 10);



			}




		} catch (Exception e){
				e.printStackTrace();
		}
	}

	public void createNotification(){
		Intent notificationIntent = new Intent(getApplicationContext(), FlotConverter.class);
		PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 0, notificationIntent, 0);

		Notification notification = new Notification(R.drawable.test, "LolliZwaper's Here!",System.currentTimeMillis());
		notification.setLatestEventInfo(getApplicationContext(), "LolliZwaper" ,  "Unicode To Zawgyi Converter!", pendingIntent);
		notification.flags = Notification.FLAG_AUTO_CANCEL | Notification.FLAG_ONGOING_EVENT;

		NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

		notificationManager.notify(ID_NOTIFICATION,notification);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (chatHead != null) windowManager.removeView(chatHead);
	}



}