package mmsd.phyochan.lollizwaper.Root;

import android.app.Activity;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Runme extends Activity {
	
    public static String  inputStream2String  (InputStream  in , String encoding)  throws  Exception  {   
        StringBuffer  out  =  new  StringBuffer();   
        InputStreamReader inread = new InputStreamReader(in,encoding);   
           
        char[]  b  =  new  char[4096];   
        for  (int  n;  (n  =  inread.read(b))  !=  -1;)  {   
                out.append(new  String(b,  0,  n));   
        }   
      
          return out.toString();   
    } 
	public static void Cmd(String su,String command) {

		Process process = null;

		DataOutputStream processOutput = null;
	//	InputStream processInput = null;

		try {

			process = Runtime.getRuntime().exec(su);

			processOutput = new DataOutputStream(process.getOutputStream());

			processOutput.writeBytes(command + "\n");

			processOutput.writeBytes("exit\n");

			processOutput.flush();
	//		processInput = process.getInputStream();
			process.waitFor();
			

		} catch (Exception e) {

			Log.d("*** DEBUG ***", "ROOT REE" + e.getMessage());

			return;
		}

		finally {

			try {

				if (processOutput != null) {
					processOutput.close();
				}

				process.destroy();
			} catch (Exception e) {
			}

		}

		Log.d("*** DEBUG ***", "RootSUC ");

		return ;

	}

	public static String removeRepeatedChar(String s) {
        if (s == null)
            return s;
        StringBuilder sb = new StringBuilder();
        int i = 0, len = s.length();
        while (i < len) {
            char c = s.charAt(i);
            sb.append(c);
            i++;
            if(c == '/')
            while (i < len && s.charAt(i) == c) {
                i++;
            }
        }
        return sb.toString();
    }
}


