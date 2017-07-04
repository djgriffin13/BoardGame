package Chess;

import Chess.Pieces.ChessPiece;
import GeneralGame.Player;

public class ChessRunner {
	private ChessBoard gb;
	private ChessPlayer black, white;
	private ChessRunner(){
		gb = new ChessBoard();
		black = new ChessPlayer("Black", false);
		white = new ChessPlayer("White", true);
			gb.display();
		playChess();
	}
	private void playChess(){
		GameOverType gameOver = GameOverType.FALSE;
		ChessPlayer nextToPlay = white;
		ChessMove cm;
		boolean legalMove = false;
		while(gameOver == GameOverType.FALSE){
			nextToPlay.yourTurn();
			cm = nextToPlay.getMove();
			legalMove = tryMakeMove(nextToPlay.getTeam(), cm);
			while (legalMove == false){
				cm = nextToPlay.getMove();
				legalMove = tryMakeMove(nextToPlay.getTeam(), cm);
			}
			if (gb.pawnPromotion()){
				gb.promotPawn(cm, nextToPlay.getPromotePawnOption());
				gb.display();
			}
			gameOver = gb.testGameOver(!nextToPlay.getTeam());	
			
			if (nextToPlay == white){
				nextToPlay = black;
			}
			else nextToPlay = white;
		}
		if (gameOver == GameOverType.CHECKMATE)
			System.out.print("You lost");
		else System.out.print("It is a tie.");
	}
	private boolean tryMakeMove(boolean team, ChessMove cm) {
		// sholud move this into game board
		if (gb.checkMove(cm)==false){
			illigalMove();
			return false;
		}
		if (gb.isInThrenedAfterMove(gb.getKingPosition(team),cm)){
			checkErr();
			return false;
		}
		gb.makeMove(cm);
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
