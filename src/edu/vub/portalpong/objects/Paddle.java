package edu.vub.portalpong.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Paddle extends GameObject {

	public int width;
	public int height;
	protected Paint paint;

	public Paddle(int x, int y) {
		super(x,y);
		this.width = 200;
		this.height = 20;
		this.paint = new Paint();
		this.paint.setColor(Color.WHITE);
	}

	public void draw(Canvas c) {
		int halfwidth = width / 2;
		int halfheight = height / 2;
		c.drawRect(x-halfwidth, y-halfheight, x+halfwidth, y+halfheight, paint);
	}
	
	public void setColor(int color) {
		this.paint.setColor(color);
	}

}
