package Chess.Pieces;

import java.util.LinkedList;

import Chess.ChessMove;
import Chess.ChessType;
import GeneralGame.location;

public final class Bishop extends ChessPiece {
	public Bishop(){
		this(true, 0 ,0);
	}
	public Bishop(boolean t,int x, int y){
		super(t, ChessType.BISHOP, x, y);
	}
	public location[] getPath(location d) {
		// TODO Auto-generated method stub
		int size = Math.abs(this.x - d.x);
		location mpath[] = new location[size];
		location newPlace;
		int xRise, yRise;
		if (this.x<d.x) xRise=1;
		else xRise = -1;
		if (this.y<d.y) yRise = 1;
		else yRise = -1;
		for(int i =1;i<size+1;++i){
			newPlace = new location(this.x+i*xRise,this.y+i*yRise);
			mpath[i-1] = newPlace;
		}
		return mpath;
	}
	public boolean legalMove(location d) {
		// TODO Auto-generated method stub
		if (Math.abs((d.x-this.x))==Math.abs(d.y-this.y))
			return true;
		return false;
	}
	public LinkedList<ChessMove> getPosibleMoves() {
		LinkedList<ChessMove> pm = new LinkedList<ChessMove>();
		location o = new location(x,y);
		int i = x+1; int j = y+1;
		while(i < 8 && j < 8){
			pm.push(new ChessMove(o,new location(i++,j++)));
			
		}
		 i=x+1; j = y-1;
		while(i < 8 && j >= 0){
			pm.push(new ChessMove(o,new location(i++,j--)));
		}
		 i=x-1; j = y+1;
		while(i >= 0 && j < 8){
			pm.push(new ChessMove(o,new location(i--,j++)));
		}
		 i=x-1; j = y-1;
		while(i >= 0 && j >= 0){
			pm.push(new ChessMove(o,new location(i--,j--)));
		}
		return pm;
	}
}
