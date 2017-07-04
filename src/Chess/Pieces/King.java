package Chess.Pieces;

import java.util.LinkedList;

import Chess.ChessMove;
import Chess.ChessType;
import GeneralGame.location;
public final class King extends ChessPiece{
	public King(){
		this(true, 0 ,0);
	}
	public King(boolean t,int x, int y){
		super(t, ChessType.KING, x, y);
	}
	public location[] getPath(location d) {
		// TODO Auto-generated method stub
		location mpath[] = new location[1];
		mpath[0]=d;
		return mpath;
	}
	public boolean legalMove(location d) {
		// TODO Auto-generated method stub
		if (Math.abs((d.x-this.x))+Math.abs(d.y-this.y)== 1+Math.abs((d.x-this.x)*(d.y-this.y)))
			return true;
		return false;
	}
	public LinkedList<ChessMove> getPosibleMoves() {
		LinkedList<ChessMove> pm = new LinkedList<ChessMove>();
		location o = new location(x,y);
		if(x<7){
			pm.push(new ChessMove(o,new location(x+1,y)));
			if(y<7)
				pm.push(new ChessMove(o,new location(x+1,y+1)));
			if(y>0)
				pm.push(new ChessMove(o,new location(x+1,y-1)));
		}
		if(y<7)
			pm.push(new ChessMove(o,new location(x,y+1)));
		if(y>0)
			pm.push(new ChessMove(o,new location(x,y-1)));
		if(x>0){
			pm.push(new ChessMove(o,new location(x-1,y)));
			if(y<7)
				pm.push(new ChessMove(o,new location(x-1,y+1)));
			if(y>0)
				pm.push(new ChessMove(o,new location(x-1,y-1)));
		}
		return pm;
	}
}
