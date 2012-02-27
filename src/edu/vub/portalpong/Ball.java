package edu.vub.portalpong;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {

	public int x;
	public int y;
	public double dx;
	public double dy;
	private Paint paint;
	public int size;

	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		
		double angle = 2 * Math.PI * new Random().nextDouble();
		this.dx = Math.cos(angle);
		this.dy = Math.sin(angle);
		
		this.paint = new Paint();
		this.paint.setColor(Color.WHITE);
		this.size = 40 / 2;
	}

	public void draw(Canvas c) {
		c.drawRect(x-size, y-size, x+size, y+size, paint);
	}

}
