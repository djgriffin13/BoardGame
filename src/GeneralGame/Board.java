package GeneralGame;

/**
 * 
 * @author Daniel
 * This is an abstract class which holds the board and keeps track of all pieces
 */
public abstract class Board {
protected int width;
protected int length;
protected Piece pieces[][];

	/**
	 * Constructor
	 */
	public Board(){
		
	}
	/**
	 * Resets the board after a game or at the begining
	 */
	protected void reset(){
		
	}
	/**
	 * Prompts the board to be re rendered
	 */
	protected void display(){
		
	}
}
