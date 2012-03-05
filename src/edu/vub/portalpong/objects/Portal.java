package edu.vub.portalpong.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class Portal extends DoubleBall {

	protected Paint ringPaint;
	public int ringRadius;
	private int radiusDiff = -1;
	public Object playerId;
	private boolean active;


	public Portal(int x, int y, Object playerId) {
		this(x, y, playerId, 0.6);
	}
	
	public Portal(int x, int y, Object playerId, double ratio) {
		super(x, y, ratio);
		super.setSize(40);
		this.ringRadius = this.size;
		this.playerId = playerId;
		this.ringPaint = new Paint();
		this.ringPaint.setStyle(Style.STROKE);
		this.ringPaint.setStrokeWidth(5);
		this.setColor(Color.GREEN);
		this.active = true;
	}

	public boolean collidesWith(Ball ball) {
		if (!active)
			return false;
		
		int dist2 = (int) Math.pow(ball.x - this.x, 2) + (int) Math.pow(ball.y - this.y, 2);
		return dist2 < this.size * this.size;
	}

	@Override
	public void draw(Canvas c) {
		if (active) {
			super.draw(c);
			c.drawCircle(x, y, this.size, ringPaint);
			if (ringRadius <= this.size * super.ratio * super.ratio) {
				this.radiusDiff = 1;
			}
			if (ringRadius >= this.size) {
				this.radiusDiff = -1;
			}
			ringRadius = ringRadius + radiusDiff;
			c.drawCircle(x, y, ringRadius, ringPaint);
		}
	}
	
	public void show() {
		active = true;
	}
	
	public void hide() {
		active = false;
	}
	
	public void setColor(int color) {
		super.setColor(color);
		this.ringPaint.setColor(color);
	}

	
}
