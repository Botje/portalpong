package edu.vub.portalpong.objects;

import android.graphics.Canvas;
import android.graphics.Color;

public class FancyPaddle extends Paddle {
	
	private final Paddle inner_;
	
	public final static int ALPHA = DoubleBall.ALPHA;

	public FancyPaddle(int x, int y, double ratio) {
		super(x, y);
		inner_ = new Paddle(x, y);
		inner_.height = (int) (this.height * ratio);;
		this.setColor(Color.WHITE);
	}
	
	public FancyPaddle(int x, int y) {
		this(x, y, 0.3);
	}

	@Override
	public void draw(Canvas c) {
		int diff = (this.height - inner_.height) / 2;
		inner_.y = this.y - diff;
		inner_.x = this.x;
		super.draw(c);
		inner_.draw(c);
	}
	
	@Override
	public void setColor(int color) {
		super.setColor(color);
		inner_.setColor(color);
		this.paint.setAlpha(ALPHA);
	}
	
}
