package GeneralGame;

import Chess.ChessMove;

/**
 * @author Daniel
 * An opject that decides on moves. For human players it will get the moves from the concel or GUI
 */
public abstract class Player {
	/**
	 * Is the player a AI type player
	 */
	boolean AI;
	
	/**
	 * What is the players turn order
	 */
	int turnOrder;

	
}
