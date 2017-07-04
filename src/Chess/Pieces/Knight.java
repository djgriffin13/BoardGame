package Chess.Pieces;

import java.util.LinkedList;

import Chess.ChessMove;
import Chess.ChessType;
import GeneralGame.location;

public final class Knight extends ChessPiece {
	public Knight(){
		this(true, 0 ,0);
	}
	public Knight(boolean t,int x, int y){
		super(t, ChessType.KNIGHT, x, y);
	}
	public location[] getPath(location d) {
		// TODO Auto-generated method stub
		location mpath[] = new location[1];
		mpath[0]=d;
		return mpath;
	}
	public boolean legalMove(location d) {
		// TODO Auto-generated method stub
		if (Math.abs((d.x-this.x)*(d.y-this.y))==2)
			return true;
		return false;
	}
	public LinkedList<ChessMove> getPosibleMoves() {
		LinkedList<ChessMove> pm = new LinkedList<ChessMove>();
		location o = new location(x,y);
		if (x<6){
			if(y<7)
				pm.push(new ChessMove(o,new location(x+2,y+1)));
			if(y >0)
				pm.push(new ChessMove(o,new location(x+2,y-1)));
			if(x<7){
				if (y<6)
					pm.push(new ChessMove(o,new location(x+1,y+2)));
				if (y>1)
					pm.push(new ChessMove(o,new location(x+1,y-2)));
		}}
		if(x>1){
			if(y<7)
				pm.push(new ChessMove(o,new location(x-2,y+1)));
			if(y >0)
				pm.push(new ChessMove(o,new location(x-2,y-1)));			
			if(x>0){
				if (y <6)	
					pm.push(new ChessMove(o,new location(x-1,y+2)));
				if(y >1)
					pm.push(new ChessMove(o,new location(x-1,y-2)));
		}}
		return pm;
	}
}
