package com.example.asynctaskhttpexample;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.example.asynctasksimpleexample.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private DownloadImageTask diTask;
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ImageView imageView = (ImageView) findViewById(R.id.imageView1);
	}

	
	public void doClick(View view) {
		if (diTask != null) {
			AsyncTask.Status diStatus = diTask.getStatus();
			Log.v("doClick", "diTask status is " + diStatus);
			if (diStatus != AsyncTask.Status.FINISHED) {
				Log.v("doClick", "... no need to start a new task");
				return;
			}
		}
		diTask = new DownloadImageTask(this);
		//Lanzo AsyncTask to download this Bitmap
		diTask.execute("http://pessoal.utfpr.edu.br/mansano/arquivos/DPTL004.BMP");
	}

	
	// ASYNCTASK
	class DownloadImageTask extends AsyncTask<String, Integer, Bitmap> {
		private Context mContext;

		DownloadImageTask(Context context) {
			mContext = context;
		}

		//En UI Thread
		protected void onPreExecute() {
		}

		//En Task Thread
		protected Bitmap doInBackground(String... urls) {
			Log.v("doInBackground", "doing download of image");
			//Result is a Bitmap
			return downloadImage(urls);
		}

		//En UI Thread
		protected void onProgressUpdate(Integer... progress) {
			Log.v("onProgressUpdate", "Progress so far: " + progress[0]);
		}

		//En UI Thread: cuando doInBackground termine, le envia el Bitmap
		protected void onPostExecute(Bitmap result) {

		}

		//En Task Thread: called by doInBackground
		private Bitmap downloadImage(String... urls) {
			HttpClient httpClient = CustomHttpClient.getHttpClient();
			try {
				HttpGet request = new HttpGet(urls[0]);
				HttpParams params = new BasicHttpParams();
				HttpConnectionParams.setSoTimeout(params, 60000); // 1 minute
				request.setParams(params);

				publishProgress(25);

				HttpResponse response = httpClient.execute(request);

				publishProgress(50);

				byte[] image = EntityUtils.toByteArray(response.getEntity());

				publishProgress(75);

				Bitmap mBitmap = BitmapFactory.decodeByteArray(image, 0,
						image.length);

				publishProgress(100);

				return mBitmap;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}

}

// Cliente Http
class CustomHttpClient {
	private static HttpClient customHttpClient;

	public static synchronized HttpClient getHttpClient() {
		if (customHttpClient != null) {
			return customHttpClient;
		}
		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params,
				HTTP.DEFAULT_CONTENT_CHARSET);
		HttpProtocolParams.setUseExpectContinue(params, true);

		ConnManagerParams.setTimeout(params, 1000);

		HttpConnectionParams.setConnectionTimeout(params, 5000);
		HttpConnectionParams.setSoTimeout(params, 10000);

		SchemeRegistry schReg = new SchemeRegistry();
		schReg.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		schReg.register(new Scheme("https",
				SSLSocketFactory.getSocketFactory(), 443));
		ClientConnectionManager conMgr = new ThreadSafeClientConnManager(
				params, schReg);

		customHttpClient = new DefaultHttpClient(conMgr, params);
		return customHttpClient;

	}
}