package edu.vub.portalpong;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.SurfaceView;

public class PortalPongActivity extends Activity {
    private GameLoop gl;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SurfaceView sv = (SurfaceView) findViewById(R.id.field);
        SensorManager sm = (SensorManager)getSystemService(SENSOR_SERVICE);
		gl = new GameLoop(sv.getHolder(), sm);
        sv.getHolder().addCallback(gl);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		finish();
		gl.stopRunning();
		return super.onCreateOptionsMenu(menu);
	}
    
    
}