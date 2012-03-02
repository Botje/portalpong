package edu.vub.portalpong;

import java.io.IOException;

import edu.vub.at.IAT;
import edu.vub.at.android.util.IATAndroid;
import edu.vub.at.exceptions.InterpreterException;
import android.app.Activity;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceView;

public class PortalPongActivity extends Activity {
    public static IATAndroid iat_;

	public class StartIATTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {
			try {
				iat_ = IATAndroid.create(PortalPongActivity.this);
				ATPortalPong atpp = (ATPortalPong) iat_.evalAndWrap("/.demo.portalpong.portalpong.return", ATPortalPong.class);
				gl.doHandshake(atpp);
			} catch (Exception e) {
				Log.d("portal-pong", "Could not create IAT", e);
			}
			return null;
		}

	}

	private GameLoop gl;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startActivityForResult(new Intent(this, PPInstaller.class), 0);
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        SurfaceView sv = (SurfaceView) findViewById(R.id.field);
        SensorManager sm = (SensorManager)getSystemService(SENSOR_SERVICE);
		gl = new GameLoop(sv.getHolder(), sm);
		new StartIATTask().execute();
        sv.getHolder().addCallback(gl);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		finish();
		gl.stopRunning();
		return super.onCreateOptionsMenu(menu);
	}
    
    
}