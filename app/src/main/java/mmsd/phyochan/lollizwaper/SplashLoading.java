package mmsd.phyochan.lollizwaper;



import android.graphics.Color;
import android.os.AsyncTask;

import com.gc.materialdesign.views.ProgressBarDeterminate;

import mmsd.phyochan.lollizwaper.TextView.TharLonTextView;


public class SplashLoading extends AsyncTask<String, Integer, Integer>{



	
	
	
	
	public interface FinishedListener {
		void onTaskFinished(); // If you want to pass something back to the listener add a param to this method
	}

	
private	final ProgressBarDeterminate progressBarDeterminate;
	
private	final FinishedListener finishedListener;

    private final TharLonTextView txtloading;
	
		
		public SplashLoading (ProgressBarDeterminate progressBarDeterminate,FinishedListener finishedListener,TharLonTextView txtloading){
			
			
			this.progressBarDeterminate = progressBarDeterminate;
			this.finishedListener = finishedListener;
            this.txtloading = txtloading;

            progressBarDeterminate.setMax(100);
            progressBarDeterminate.setBackgroundColor(Color.parseColor("#ffffff"));
	
			
		}
		
		
		
		@Override
		protected Integer doInBackground(String... params) {

            int count = 6;


            for (int i = 0; i < count; i++) {


                int progress = (int) ((i / (float) count) * 120);
                publishProgress(progress);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignore) {

                }


            }

            return 1234;

        }
		
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			
			progressBarDeterminate.setProgress(values[0]);

            txtloading.setText("Loading . . .  " + values[0] + "%");





        }
		
		
		
		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			
			finishedListener.onTaskFinished();
			
			
		}
		
		
		
	
}