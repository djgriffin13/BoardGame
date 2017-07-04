package Chess;
import java.util.LinkedList;
import java.util.Vector;

import Chess.Pieces.Bishop;
import Chess.Pieces.ChessPiece;
import Chess.Pieces.King;
import Chess.Pieces.Knight;
import Chess.Pieces.Pawn;
import Chess.Pieces.Queen;
import Chess.Pieces.Rook;
import GeneralGame.Board;
import GeneralGame.Piece;
import GeneralGame.location;

public class ChessBoard extends Board {
	location wKing;
	location bKing;
	boolean pawnToPromote;
	boolean castle;
	boolean en_passant;
	boolean pawnMoved2;
	location lastMove;
	public ChessBoard(){
		pawnMoved2 = false;
		castle = false;
		en_passant = false;
		wKing = new location(0,3);
		bKing = new location(7,3);
		pawnToPromote = false;
		this.pieces = new ChessPiece[8][8];
		this.pieces[0][0] = new Rook   (true, 0,0);
		this.pieces[0][1] = new Knight (true, 0,1);
		this.pieces[0][2] = new Bishop (true, 0,2);
		this.pieces[0][3] = new King  (true, 0,3);
		this.pieces[0][4] = new Queen   (true, 0,4);
		this.pieces[0][5] = new Bishop (true, 0,5);
		this.pieces[0][6] = new Knight (true, 0,6);
		this.pieces[0][7] = new Rook   (true, 0,7);
		for(int i = 0; i< 8; ++i) {
			this.pieces[1][i]= new Pawn(true,  1,i);		
			this.pieces[6][i]= new Pawn(false, 6,i);
		}
		this.pieces[7][0] = new Rook(false,   7,0);
		this.pieces[7][1] = new Knight(false, 7,1);
		this.pieces[7][2] = new Bishop(false, 7,2);
		this.pieces[7][3] = new King(false,  7,3);
		this.pieces[7][4] = new Queen(false,   7,4);
		this.pieces[7][5] = new Bishop(false, 7,5);
		this.pieces[7][6] = new Knight(false, 7,6);
		this.pieces[7][7] = new Rook(false,   7,7);	
		
		for (int j =0;j<8;++j){
			for (int i =2; i<6;++i){
				this.pieces[i][j]= new ChessPiece(i,j);
			}
		}
	}
	
	public ChessPiece getPiece(location l) {
		return (ChessPiece) this.pieces[l.x][l.y];
	}

	public void makeMove(ChessMove cm) {
		location o = cm.getOrigin();
		location d = cm.getDestination();
		// sholud change all calls to getPiece()
		this.pieces[d.x][d.y] = this.pieces[o.x][o.y];
		((ChessPiece)this.pieces[d.x][d.y]).setLocation(d);
		((ChessPiece)this.pieces[d.x][d.y]).setMoved(true);
		this.pieces[o.x][o.y] = new ChessPiece();
		if (castle){
			if (d.y>4){
				this.pieces[o.x][d.y-1] = this.pieces[o.x][7];
				this.pieces[o.x][7] = new ChessPiece();
			}
			else{
				this.pieces[o.x][d.y+1] = this.pieces[o.x][0];				
				this.pieces[o.x][0] = new ChessPiece();
			}
			castle = false;
		}
		if (en_passant){
			this.pieces[o.x][d.y] = new ChessPiece();
			en_passant = false;
		}
		lastMove = d;
		if((d.x == 0 || d.x == 7)&& getPiece(d.x,d.y).getType()==ChessType.PAWN)
			pawnToPromote= true;
		if (getPiece(d.x,d.y).getType()==ChessType.PAWN && Math.abs(o.x-d.x)==2)
			pawnMoved2 = true;
		else
			pawnMoved2 = false;;
display();
	}

	public boolean isThretened(location l) {
		// TODO Auto-generated method stub
		int X=l.x;
		int Y = l.y;
		int pAdd;
		boolean team = ((ChessPiece) this.pieces[l.x][l.y]).getTeam();
		ChessPiece cp = null;
		
		if (team) pAdd = 1;
		else pAdd = -1;
		
		// test pawns
		if (X+pAdd >= 0 &&  X + pAdd < 8 && Y+ 1< 8){
			if(getPiece(X+pAdd,Y+1).getTeam() != team && getPiece(X+pAdd,Y+1).getType() ==ChessType.PAWN){
				return true;
			}
		}
		if (X+pAdd >= 0 &&  X + pAdd < 8 && Y- 1>= 0){
			if((getPiece(X+pAdd,Y-1).getTeam() != team && getPiece(X+pAdd,Y-1).getType() ==ChessType.PAWN)){
				return true;
			}
		}
		boolean u = false, ur = false, ul=false,r=false,L=false,dr=false,dl=false,d=false;
		for (int i =1; i<7; ++i){
			if (Y+i >=8){
				u = true;
				ur = true;
				ul = true;
			}
			if (Y-i <0){
				d=true;
				dr=true;
				dl=true;
			}
			if (X+i >= 8){
				r = true;
				ur=true;
				dr=true;
			}
			if (X-i < 0){
				L = true;
				ul=true;
				dl = true;
			}
			
			if(!u){
				cp = getPiece(X,Y+i);
				if(cp.getTeam() != team && (cp.getType() == ChessType.ROOK||cp.getType() == ChessType.QUEEN))
					return true;
				}
				if (cp.getType()!=ChessType.EMPTY) u=true;
			if (!r){
				cp = getPiece(X+i,Y);
				if(cp.getTeam() != team && (cp.getType() == ChessType.ROOK||cp.getType() == ChessType.QUEEN))
					return true;
				if (cp.getType()!=ChessType.EMPTY) r=true;

			}
			if (!L){
				cp = getPiece(X-i,Y);
				if(cp.getTeam() != team && (cp.getType() == ChessType.ROOK||cp.getType() == ChessType.QUEEN))
					return true;
				if (cp.getType()!=ChessType.EMPTY) L=true;

			}
			if (!d){
				cp = getPiece(X,Y-i);
				if(cp.getTeam() != team && (cp.getType() == ChessType.ROOK||cp.getType() == ChessType.QUEEN))
					return true;
				if (cp.getType()!=ChessType.EMPTY) d=true;

			}
			
			if(!ur){
				cp = getPiece(X+i,Y+i);
				if(cp.getTeam() != team && (cp.getType() == ChessType.BISHOP||cp.getType() == ChessType.QUEEN))
					return true;
				if (cp.getType()!=ChessType.EMPTY) ur=true;

				}
			if (!ul){
				cp = getPiece(X-i,Y+i);
				if(cp.getTeam() != team && (cp.getType() == ChessType.BISHOP||cp.getType() == ChessType.QUEEN))
					return true;
				if (cp.getType()!=ChessType.EMPTY) ul=true;

				}
			if (!dr){
				cp = getPiece(X+i,Y-i);
				if(cp.getTeam() != team && (cp.getType() == ChessType.BISHOP||cp.getType() == ChessType.QUEEN))
					return true;
				if (cp.getType()!=ChessType.EMPTY) dr=true;

			}
			if (!dl){
				cp = getPiece(X-i,Y-i);
				if(cp.getTeam() != team && (cp.getType() == ChessType.BISHOP||cp.getType() == ChessType.QUEEN))
					return true;
				if (cp.getType()!=ChessType.EMPTY) dl=true;

			}
		}
		cp = getPiece(X+1,Y+2);
		if(cp.getTeam() != team && cp.getType() == ChessType.KNIGHT)
			return true;
		cp = getPiece(X-1,Y+2);
		if(cp.getTeam() != team && cp.getType() == ChessType.KNIGHT)
			return true;
		cp = getPiece(X+1,Y-2);
		if(cp.getTeam() != team && cp.getType() == ChessType.KNIGHT)
			return true;
		cp = getPiece(X-1,Y-2);
		if(cp.getTeam() != team && cp.getType() == ChessType.KNIGHT)
			return true;
		cp = getPiece(X+2,Y+1);
		if(cp.getTeam() != team && cp.getType() == ChessType.KNIGHT)
			return true;
		cp = getPiece(X-2,Y+1);
		if(cp.getTeam() != team && cp.getType() == ChessType.KNIGHT)
			return true;
		cp = getPiece(X+2,Y-1);
		if(cp.getTeam() != team && cp.getType() == ChessType.KNIGHT)
			return true;
		cp = getPiece(X-2,Y-1);
		if(cp.getTeam() != team && cp.getType() == ChessType.KNIGHT)
			return true;
		
		
		
		cp = getPiece(X+1,Y);
		if(cp.getTeam() != team && cp.getType() == ChessType.KING)
			return true;
		cp = getPiece(X+1,Y+1);
		if(cp.getTeam() != team && cp.getType() == ChessType.KING)
			return true;
		cp = getPiece(X+1,Y-1);
		if(cp.getTeam() != team && cp.getType() == ChessType.KING)
			return true;
		cp = getPiece(X-1,Y);
		if(cp.getTeam() != team && cp.getType() == ChessType.KING)
			return true;
		cp = getPiece(X-1,Y+1);
		if(cp.getTeam() != team && cp.getType() == ChessType.KING)
			return true;
		cp = getPiece(X-1,Y-1);
		if(cp.getTeam() != team && cp.getType() == ChessType.KING)
			return true;
		cp = getPiece(X,Y+1);
		if(cp.getTeam() != team && cp.getType() == ChessType.KING)
			return true;
		cp = getPiece(X,Y-1);
		if(cp.getTeam() != team && cp.getType() == ChessType.KING)
			return true;

		
		return false;
	}
	private ChessPiece getPiece(int x, int y){
		if (x>7 || x<0 || y>7||y<0) return new ChessPiece();
		return (ChessPiece) this.pieces[x][y];
	}

	public boolean checkMove(ChessMove cm) {
		// this is where I should see if it is a castle move or empasant.
		location o = cm.getOrigin();
		location d = cm.getDestination();
		ChessPiece cp = (ChessPiece) this.pieces[o.x][o.y];
		boolean currentTeam = cp.getTeam();
		int X = cm.getDestination().x;
		int Y = cm.getDestination().y;
		if ( getPiece(X,Y).getType()!= ChessType.EMPTY){
			if (getPiece(X,Y).getTeam() == currentTeam) return false;
			cp.setAttack(true);
		}
		else cp.setAttack(false);


		if (cp.getType()==ChessType.KING && !cp.getMoved()&&Math.abs(o.y-d.y)==2&& o.x==d.x){
			int side;
			if (d.y>4)side = 7;
			else side = 0;
			if (isThretened(o)||isThretened(new location(o.x,(o.y+d.y)/2))) return false;
			if (!getPiece(o.x,side).getMoved())
				castle = true;
			else return false;
		}
		else if (cp.getType()==ChessType.PAWN&&!cp.getAttack()&&o.y!=d.y&&pawnMoved2&&lastMove.x ==o.x&&lastMove.y==d.y&&Math.abs(o.y-d.y)==1){
			en_passant = true;
		}
		else if (!cp.legalMove(d))return false;
		location movmentPath[] = cp.getPath(d);
		for(int i = 0; i < movmentPath.length-1; ++i){
			X = movmentPath[i].x;
			Y = movmentPath[i].y;
			if (getPiece(X,Y).getType()!= ChessType.EMPTY)
				return false;
		}
		return true;
	}
	public boolean isInThrenedAfterMove(boolean team, ChessMove cm) {
		return isInThrenedAfterMove(getKingPosition(team),cm);
	}
	public boolean isInThrenedAfterMove(location l, ChessMove cm) {
// should try with getPieces() method. Knight, and probably king are moving off the board.
		Piece temp =  this.pieces[cm.getDestination().x][cm.getDestination().y];
		this.pieces[cm.getDestination().x][cm.getDestination().y] = this.pieces[cm.getOrigin().x][cm.getOrigin().y];
		this.pieces[cm.getOrigin().x][cm.getOrigin().y] = new ChessPiece();
		boolean threat;
		if (l.equals(cm.getOrigin()))
			threat = isThretened(cm.getDestination());
		else threat = isThretened(l);
		 this.pieces[cm.getOrigin().x][cm.getOrigin().y] = this.pieces[cm.getDestination().x][cm.getDestination().y];
		 this.pieces[cm.getDestination().x][cm.getDestination().y] = temp;
		return threat;
	}
	public void display(){
		String line;
		for (int i =0;i<8;++i){
			line = "";
			for(int j =0; j<8; ++j){
				if (getPiece(i,j).getType() == ChessType.EMPTY){
					line+=" ____ ";
					continue;
				}
				line +=" _";
				if (getPiece(i,j).getTeam())
					line += "w";
				else line += "b";

				if (getPiece(i,j).getType() == ChessType.PAWN){
					line+="P_ ";
				}
				else if (getPiece(i,j).getType() == ChessType.KNIGHT){
					line+="N_ ";
				}
				else if (getPiece(i,j).getType() == ChessType.BISHOP){
					line+="B_ ";
				}
				else if (getPiece(i,j).getType() == ChessType.ROOK){
					line+="R_ ";
				}
				else if (getPiece(i,j).getType() == ChessType.QUEEN){
					line+="Q_ ";
				}
				else if (((ChessPiece)this.pieces[i][j]).getType() == ChessType.KING){
					line+="K_ ";
				}
			}
			System.out.println(line);
		}
	}

	public location getKingPosition(boolean team) {
		// TODO Auto-generated method stub
		if (team) return wKing;
		return bKing;
	}
	public void setKingPosition(boolean team, location l){
		if (team) wKing = l;
		else bKing = l;
	}

	public boolean pawnPromotion() {
		// TODO Auto-generated method stub
		return pawnToPromote;
	}

	public void promotPawn(ChessMove cm, ChessType p) {
		// TODO Auto-generated method stub
		location d = cm.getDestination();
		boolean team = getPiece(d.x,d.y).getTeam();
		if (p == ChessType.QUEEN)
			pieces[d.x][d.y] = new Queen(team,d.x,d.y);
		if (p == ChessType.ROOK)
			pieces[d.x][d.y] = new Rook(team,d.x,d.y);
		if (p == ChessType.KNIGHT)
			pieces[d.x][d.y] = new Knight(team,d.x,d.y);
		if (p == ChessType.BISHOP)
			pieces[d.x][d.y] = new Bishop(team,d.x,d.y);
		else
			pieces[d.x][d.y] = new Queen(team,d.x,d.y);
		getPiece(d.x,d.y).setMoved(true);
		pawnToPromote = false;
	}

	public GameOverType testGameOver(boolean player) {
		// TODO Auto-generated method stub
		boolean underCheck;
		ChessPiece cp;
		LinkedList<ChessMove> posibleMoves;
		ChessMove cm = null;

		if (isThretened(getKingPosition(player)))underCheck = true;
		else underCheck = false;
		
		int c = 0;
		for (int i = 0; i<8;++i){
			for (int j =0;j<8;++j){
				cp = getPiece(i,j);
				if (cp.getTeam()==player){
					posibleMoves = cp.getPosibleMoves();
					while (posibleMoves.size()>0){
						cm = posibleMoves.pop();
						if(checkMove(cm)&&!isInThrenedAfterMove(player,cm))
							return GameOverType.FALSE;
					}
				}
			}
		}
		if (underCheck)
			return GameOverType.CHECKMATE;
		return GameOverType.STALEMATE;
	}
}
