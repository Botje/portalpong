package edu.vub.portalpong.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;

public class Portal extends GameObject {

	private int radius;
	private Paint paint;

	public Portal(int x, int y) {
		super(x, y);
		this.radius = 50;
		this.paint = new Paint();
		this.paint.setColor(Color.GREEN);
		this.paint.setStyle(Style.STROKE);
		this.paint.setStrokeWidth(5);
	}

	public boolean collidesWith(Ball ball) {
		int dist2 = (int) Math.pow(ball.x - this.x, 2) + (int) Math.pow(ball.y - this.y, 2);
		return dist2 < radius * radius;
	}

	@Override
	public void draw(Canvas c) {
		c.drawCircle(x, y, radius, paint);
	}

	public void enter(Ball ball) {
		Log.d("portal-pong","Portal entered!");
	}

	
}
