package Chess.Pieces;

import java.util.LinkedList;

import Chess.ChessMove;
import Chess.ChessType;
import GeneralGame.location;

public final class Rook extends ChessPiece{
	public Rook(){
		this(true, 0 ,0);
	}
	public Rook(boolean t,int x, int y){
		super(t, ChessType.ROOK, x, y);
	}
	
	public location[] getPath(location d) {
		// TODO Auto-generated method stub
		int size;
		boolean positiveDirection = false;
		boolean xDirection = false;
		if (this.x - d.x != 0){
			size = Math.abs(this.x - d.x);
			xDirection = true;
			if (this.x < d.x)
				positiveDirection = true;
		}
		else {
			size = Math.abs(this.y-d.y);
			if (this.x < d.x)
				positiveDirection = true;
			}
		
		location mpath[] = new location[size];
		location newPlace;
		int xRise =0, yRise=0;
		if (xDirection){
			if(positiveDirection){
				xRise=1;
			}
			else xRise = -1;
		}
		else{
			if (positiveDirection){
				yRise=1;
			}
			else yRise =-1;
		}

		for(int i =1;i<size+1;++i){
			newPlace = new location(this.x+i*xRise,this.y+i*yRise);
			mpath[i-1] = newPlace;
		}
		return mpath;
	}
	public boolean legalMove(location d) {
		// TODO Auto-generated method stub
		if (d.x==this.x && d.y==this.y)
			return false;
		if (d.x==this.x || d.y==this.y)
			return true;
		return false;
		}
	public LinkedList<ChessMove> getPosibleMoves() {
		LinkedList<ChessMove> pm = new LinkedList<ChessMove>();
		location o = new location(x,y);
		int i = x+1; int j = y+1;
		while(i < 8){
			pm.push(new ChessMove(o,new location(i++,y)));
			
		}
		while(j < 8){
			pm.push(new ChessMove(o,new location(x,j++)));
			
		}
		i=x-1; j = y-1;
		while(i >=0){
			pm.push(new ChessMove(o,new location(i--,y)));
			
		}
		while(j >=0){
			pm.push(new ChessMove(o,new location(x,j--)));		
		}
		return pm;
	}
}
