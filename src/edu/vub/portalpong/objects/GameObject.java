package edu.vub.portalpong.objects;

import android.graphics.Canvas;

public class GameObject {
	public int x;
	public int y;
	
	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Canvas c) {
		// do nothing
	}
}
