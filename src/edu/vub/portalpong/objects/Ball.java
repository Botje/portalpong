package edu.vub.portalpong.objects;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball extends GameObject{


	public double dx;
	public double dy;
	private Paint paint;
	public int size;

	public Ball(int x, int y) {
		super(x,y);
		
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
