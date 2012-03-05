package edu.vub.portalpong.objects;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import edu.vub.portalpong.ATPortalPong;

public class GameWorld {
	private int width, height;
	
	public Paddle paddle;
	private Collection<Portal> portals;
	private Collection<Ball> balls;

	private ATPortalPong atpp;

	public GameWorld(int width, int height) {
		this.width = width;
		this.height = height;
		
		this.paddle = new FancyPaddle(width / 2, (int)(height * 0.90), 0.7);
		this.paddle.setColor(Color.WHITE);
		
		this.balls = new ArrayList<Ball>();
		Ball fancyBall = new DoubleBall(width / 2, height / 2, 0.7);
		fancyBall.setColor(Color.WHITE);
		this.balls.add(fancyBall);

		this.portals = new ArrayList<Portal>();
	}

	public synchronized void update(float dx) {
		updatePaddle(dx);

		ArrayList<Ball> tbr = new ArrayList<Ball>();
		for (Ball b: balls) {
			if (updateBall(b)) {
				tbr.add(b);
			}
		}
		balls.removeAll(tbr);
	}
	
	private void updatePaddle(float dx) {
		paddle.x += Math.signum(dx) * Math.sqrt(Math.abs(dx)) * 30;
		int halfwidth = paddle.width /2;
		if (paddle.x - halfwidth < 0 + 10)
			paddle.x = 10 + halfwidth;
		if (paddle.x + halfwidth > width - 10)
			paddle.x = width - 10 - halfwidth;
	}

	private boolean updateBall(Ball ball) {
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
				atpp.enterPortal(p.playerId, ball.dx, ball.dy);
				return true;
			}
		}
		
		return false;
	}

	public synchronized void draw(Canvas c) {
		paddle.draw(c);
		for (Ball b: balls)
			b.draw(c);
		for (Portal p: portals)
			p.draw(c);
	}

	public synchronized Portal addPortal(Object playerId) {
		Random r = new Random();
		int x = (int)((width - 100) * r.nextFloat() + 50);
		int y = (int)((height / 2) * r.nextFloat() + 50);
		Portal p = new Portal(x,y,playerId);
		portals.add(p);
		return p;
	}

	public synchronized void addBall(int x, int y, double dx, double dy) {
		balls.add(new Ball(x,y,dx,dy));
	}

	public void setATPP(ATPortalPong atpp_) {
		atpp = atpp_;
	}
}
