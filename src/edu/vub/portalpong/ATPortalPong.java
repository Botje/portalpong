package edu.vub.portalpong;


public interface ATPortalPong {

	void handshake(JPortalPong jpp);
	void enterPortal(Object playerId, double dx, double dy);
}
