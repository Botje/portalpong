package edu.vub.portalpong.objects;


import java.util.ArrayList;
import java.util.Collection;

import android.graphics.Canvas;
import android.graphics.Color;

public class GameWorld {
	private int width, height;
	
	public Paddle paddle;
	public Ball ball;
	private Collection<Portal> portals;

	public GameWorld(int width, int height) {
		this.width = width;
		this.height = height;
		this.paddle = new FancyPaddle(width / 2, (int)(height * 0.90), 0.7);
		this.paddle.setColor(Color.WHITE);
        this.ball = new DoubleBall(width / 2, height / 2, 0.7);
        this.ball.setColor(Color.WHITE);
		this.portals = new ArrayList<Portal>();
		this.portals.add(new Portal(400, 200));
	}

	public void update(float dx) {
		updatePaddle(dx);
		updateBall();
	}
	
	private void updatePaddle(float dx) {
		paddle.x += Math.signum(dx) * Math.sqrt(Math.abs(dx)) * 30;
		int halfwidth = paddle.width /2;
		if (paddle.x - halfwidth < 0 + 10)
			paddle.x = 10 + halfwidth;
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
		
		for (Portal p: portals) {
			if (p.collidesWith(ball)) {
				p.enter(ball);
				break;
			}
		}
	}

	public void draw(Canvas c) {
		paddle.draw(c);
		ball.draw(c);
		for (Portal p: portals)
			p.draw(c);
	}
}
