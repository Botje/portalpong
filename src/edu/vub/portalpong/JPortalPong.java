package edu.vub.portalpong;

import edu.vub.portalpong.objects.Portal;

public interface JPortalPong {
	Portal spawnPortal(Object player);
	void spawnBall(Portal p, double dx, double dy);
}
