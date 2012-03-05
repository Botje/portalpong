package edu.vub.portalpong.objects;

import android.graphics.Canvas;
import android.graphics.Color;

public class DoubleBall extends Ball {
	
	public final static int ALPHA = 170;
	
	private final Ball inner_;
	public double ratio;

	public DoubleBall(int x, int y, double ratio) {
		super(x, y);
		inner_ = new Ball(x, y);
		inner_.setSize((int) (this.size * ratio));
		this.paint.setColor(Color.WHITE);
		this.ratio = ratio;
	}
	
	public DoubleBall(int x, int y) {
		this(x, y, 0.8);
	}

	@Override
	public void draw(Canvas c) {
		inner_.dx = this.dx;
		inner_.dy = this.dy;
		inner_.x = this.x;
		inner_.y = this.y;
		super.draw(c);
		inner_.draw(c);
	}

	@Override
	public void setColor(int color) {
		super.setColor(color);
		inner_.setColor(color);
		this.paint.setAlpha(ALPHA);
	}
	
	@Override
	public void setSize(int size) {
		super.setSize(size);
		inner_.setSize((int) (this.size * ratio));
	}

}
