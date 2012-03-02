package edu.vub.portalpong;

import java.util.Random;

import edu.vub.portalpong.objects.GameWorld;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

public class GameLoop extends Thread implements Callback, SensorEventListener, JPortalPong {

	private SurfaceHolder holder;
	private boolean running;
	private SensorManager sm;
	private Sensor rv;
	private float dx;
	private GameWorld world;
	public ATPortalPong atpp;

	public GameLoop(SurfaceHolder holder, SensorManager sm) {
		this.holder = null;
		this.running = false;
		this.sm = sm;
	}
	
	@Override
	public void run() {
		long lastTick = 0;
		while (running) {
			long now = System.currentTimeMillis();
			if (lastTick == 0) {
				lastTick = now-20;
			}
			
			if (now - lastTick >= 20) {
				lastTick = now;
				world.update(dx);
				draw();
			}
		}
	}

	public void stopRunning() {
		running = false;
		sm.unregisterListener(this, rv);
	}
	
	private void draw() {
		Canvas c = holder.lockCanvas();
		c.drawColor(Color.BLACK);
		world.draw(c);
		holder.unlockCanvasAndPost(c);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		this.holder = holder;
		this.world = new GameWorld(width, height);
		this.running = true;
		
		rv = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
		sm.registerListener(this, rv, 100000, new Handler());
		this.start();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSensorChanged(SensorEvent ev) {
		if (ev.sensor.getType() == Sensor.TYPE_GRAVITY) {
			final float g = SensorManager.GRAVITY_EARTH;
			ev.values[0] /= g; ev.values[1] /= g; ev.values[2] /= g;
//			Log.d("portal-pong", String.format("g_vec: (%f,%f,%f)", ev.values[0], ev.values[1], ev.values[2]));
			dx = ev.values[1];
		}
	}

	public void doHandshake(ATPortalPong atpp) {
		this.atpp = atpp;
		atpp.handshake(this);
	}

	@Override
	public void spawnPortal(Object playerId) {
		world.addPortal(playerId);
	}


}
