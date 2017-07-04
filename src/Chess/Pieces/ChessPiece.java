package Chess.Pieces;

import java.util.LinkedList;

import Chess.ChessMove;
import Chess.ChessType;
import GeneralGame.Piece;
import GeneralGame.location;

public class ChessPiece extends Piece {
	protected boolean team;
	protected ChessType type;
	protected int x;
	protected int y;
	protected boolean moved;
	protected boolean attack;
	public ChessPiece(){
		this(false, ChessType.EMPTY,0 ,0);
	}
	public ChessPiece(int x,int y){
		this(false,ChessType.EMPTY,x,y);
	}
	public ChessPiece(boolean team, ChessType ct,int x,int y){
		this.team = team;
		type = ct;
		moved = false;
		attack = false;
		this.x=x;
		this.y=y;
	}
	public boolean getTeam() {
		// TODO Auto-generated method stub
		return this.team;
	}

	public location[] getPath(location d) {
		// TODO Auto-generated method stub
		return null;
	}

	public ChessType getType() {
		// TODO Auto-generated method stub
		return this.type;
	}

	public boolean legalMove(location d) {
		// TODO Auto-generated method stub
		return false;
	}
	public void setAttack(boolean a){
		attack = a;
	}
	public void setMoved(boolean m){
		moved = m;
	}
	public boolean getMoved(){
		return moved;
	}
	public void setLocation (location l){
		x=l.x;
		y=l.y;
	}
	public boolean getAttack() {
		// TODO Auto-generated method stub
		return attack;
	}
	public LinkedList<ChessMove> getPosibleMoves() {
		// TODO Auto-generated method stub
		return new LinkedList<ChessMove>();
	}
}
