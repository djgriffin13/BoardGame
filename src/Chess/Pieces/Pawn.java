package Chess.Pieces;

import java.util.LinkedList;

import Chess.ChessMove;
import Chess.ChessType;
import GeneralGame.location;

public final class Pawn extends ChessPiece{
	private int movementD;
	public Pawn(){
		this(true, 0 ,0);
	}
	public Pawn(boolean t,int x, int y){
		super(t, ChessType.PAWN, x, y);
		if (t) movementD = 1;
		else  movementD = -1;
	}
	public boolean legalMove(location d) {
		if ((moved||attack) && this.x + movementD != d.x) return false;
		if (attack && Math.abs(this.y-d.y)!=1) return false;
		if (!attack && this.y != d.y)return false;
		else if (!moved && !(this.x + 2* movementD == d.x || this.x +  movementD == d.x)) return false;	
		return true;
	}

	public location[] getPath(location d) {
		// TODO Auto-generated method stub
		int size = Math.abs(this.x-d.x);
		location mpath[] = new location[size];
		mpath[size-1]=d;
		if (size ==2)
			mpath[0] = new location(this.x + movementD,this.y);
		return mpath;
	}
	public LinkedList<ChessMove> getPosibleMoves() {
		LinkedList<ChessMove> pm = new LinkedList<ChessMove>();
		location o = new location(x,y);
		if(x+movementD>=0 &&x+movementD<8){
			pm.push(new ChessMove(o,new location(x+movementD,y)));
			if (y<7)
				pm.push(new ChessMove(o,new location(x+movementD,y+1)));
			if(y>0)
				pm.push(new ChessMove(o,new location(x+movementD,y-1)));
			pm.push(new ChessMove(o,new location(x+2*movementD,y)));
		}
		return pm;
	}
}
