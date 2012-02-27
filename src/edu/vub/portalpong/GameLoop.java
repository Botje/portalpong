package edu.vub.portalpong;

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

public class GameLoop extends Thread implements Callback, SensorEventListener {

	private int width;
	private int height;
	private SurfaceHolder holder;
	private boolean running;
	private Paddle paddle;
	private SensorManager sm;
	private Sensor rv;
	private float dx;
	private Ball ball;

	public GameLoop(SurfaceHolder holder, SensorManager sm) {
		this.width = 0;
		this.height = 0;
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
				updateBall();
				updatePaddle();
				draw();
			}
		}
	}
	
	private void draw() {
		Canvas c = holder.lockCanvas();
		c.drawColor(Color.BLACK);
		paddle.draw(c);
		ball.draw(c);
		holder.unlockCanvasAndPost(c);
	}

	private void updatePaddle() {
		paddle.x -= Math.signum(dx) * Math.sqrt(Math.abs(dx)) * 30;
		int halfwidth = paddle.width /2;
		if (paddle.x - halfwidth < 10)
			paddle.x =10 + halfwidth;
		if (paddle.x + halfwidth > width - 10)
			paddle.x = width - 10 - halfwidth;
	}

	private void updateBall() {
		ball.x += ball.dx * 10;
		ball.y += ball.dy * 10;
		
		if (ball.x < ball.size) {
			ball.x = ball.size;
			ball.dx *= -1;
		}
		
		if (ball.x > width - ball.size) {
			ball.x = width - ball.size;
			ball.dx *= -1;
		}
		
		if (ball.y < ball.size) {
			ball.y = ball.size;
			ball.dy *= -1;
		}
		
		if (ball.y > height - ball.size) {
			ball.y = height - ball.size;
			ball.dy *= -1;
		}
		
		if (ball.x + ball.size >= paddle.x - paddle.width / 2
		 && ball.x - ball.size <= paddle.x + paddle.width / 2
		 && ball.y + ball.size >= paddle.y - paddle.height / 2
		 && ball.dy > 0)
			ball.dy *= -1;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		this.width = width;
		this.height = height;
		this.holder = holder;
		this.running = true;
		this.paddle = new Paddle(width / 2, (int)(height * 0.90) );
		this.ball = new Ball(width / 2, height / 2);
		
		rv = sm.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
		sm.registerListener(this, rv, 100000, new Handler());
		this.start();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		running = false;
		sm.unregisterListener(this, rv);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSensorChanged(SensorEvent ev) {
		if (ev.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
			Log.d("portal-pong", String.format("rotvec: (%f,%f,%f)", ev.values[0], ev.values[1], ev.values[2]));
			dx = ev.values[1];
		}
	}

}
