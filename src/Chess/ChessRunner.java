package Chess;

import Chess.Pieces.ChessPiece;
import GeneralGame.Player;

/**
 * @author Daniel
 * This runs the game of Chess. It will be changed to game runner when other games are added
 */
public class ChessRunner {
	
	/**
	 * This is the game board
	 */
	private ChessBoard gb;
	/**
	 * There are two chess player for Chess eventually there will be options to set these as
	 * AI or not
	 */
	private ChessPlayer black, white;
	
	/**
	 * Constructor that statrs the game
	 */
	private ChessRunner(){
		gb = new ChessBoard();
		black = new ChessPlayer("Black", false);
		white = new ChessPlayer("White", true);
			gb.display();
		playChess();
	}
	/**
	 * Method for actually starting game play
	 */
	private void playChess(){
		GameOverType gameOver = GameOverType.FALSE;
		ChessPlayer nextToPlay = white;
		ChessMove cm;
		boolean legalMove = false;
		
		//Continues play untill the GameOverType is set to something other than FALSE
		while(gameOver == GameOverType.FALSE){
			nextToPlay.yourTurn();
			cm = nextToPlay.getMove();
			
			//gets a legal move
			legalMove = tryMakeMove(nextToPlay.getTeam(), cm);
			while (legalMove == false){
				cm = nextToPlay.getMove();
				legalMove = tryMakeMove(nextToPlay.getTeam(), cm);
			}
			
			// if there is a pawn to promote it gets their choice
			if (gb.pawnPromotion()){
				gb.promotPawn(cm, nextToPlay.getPromotePawnOption());
				gb.display();
			}
			// double check if game is over
			gameOver = gb.testGameOver(!nextToPlay.getTeam());	
			
			//switch active player
			if (nextToPlay == white){
				nextToPlay = black;
			}
			else nextToPlay = white;
		}
		if (gameOver == GameOverType.CHECKMATE)
			System.out.print("You lost");
		else System.out.print("It is a tie.");
	}
	
	//I will move this into the game board object eventually
	private boolean tryMakeMove(boolean team, ChessMove cm) {
		// makes an illigal move message if illigal move
		if (gb.checkMove(cm)==false){
			illigalMove();
			return false;
		}
		// makes a check message if you would be or are still in check
		if (gb.isThrenedAfterMove(gb.getKingPosition(team),cm)){
			checkErr();
			return false;
		}
		//makes the move
		gb.makeMove(cm);
		
		//updates the kings position
		if (cm.getOrigin().equals(gb.getKingPosition(team)))
			gb.setKingPosition(team, cm.getDestination());
		return true;
	}
	private void checkErr() {
		System.out.println("You are in Check.");
	}
	private void illigalMove() {
		System.out.println("That is an illagal move.");
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChessRunner();
	}
}
