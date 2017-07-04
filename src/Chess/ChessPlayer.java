package Chess;

import java.util.Scanner;

import GeneralGame.Player;
import GeneralGame.location;

public final class ChessPlayer extends Player{
	private String name;
	private boolean team;
	ChessPlayer(String s, boolean team){
		name = s;
		this.team = team;
	}
	public ChessMove getMove() {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int ox =   sc.nextInt();
		int oy =   sc.nextInt();
		int dx =   sc.nextInt();
		int dy =   sc.nextInt();
		return new ChessMove(ox,oy,dx,dy);
	}
	public void yourTurn() {
		System.out.println("");
		System.out.println(name + " it is your turn...");
		System.out.println("");
	}
	public boolean getTeam() {
		// TODO Auto-generated method stub
		return team;
	}
	public ChessType getPromotePawnOption() {
		// TODO Auto-generated method stub
		return ChessType.QUEEN;
	}

}
