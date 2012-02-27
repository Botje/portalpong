package edu.vub.portalpong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Paddle {

	public int x;
	public int y;
	public int width;
	public int height;
	private Paint paint;

	public Paddle(int x, int y) {
		this.x = x;
		this.y = y;	
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
	

}
