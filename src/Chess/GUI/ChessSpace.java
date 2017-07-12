package Chess.GUI;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Chess.ChessType;

public class ChessSpace extends JPanel{
	private ChessType piece;
	private JLabel picture;
	private ImageIcon bPawnIcon, bKnightIcon, bBishopIcon, bRookIcon, bQueenIcon, bKingIcon;
	private ImageIcon wPawnIcon, wKnightIcon, wBishopIcon, wRookIcon, wQueenIcon, wKingIcon;
	private ImageIcon scaledPieceIcon;
	ChessSpace(){
		this(true,ChessType.PAWN,true);
	}
	ChessSpace(boolean white, ChessType p, boolean team){
		piece = p;
		initializeIcons();
		picture = new JLabel();
		switch(piece){
		case EMPTY:
			scaledPieceIcon = null;
		case PAWN:
			if (team)
				scaledPieceIcon = wPawnIcon;
			else
				scaledPieceIcon = bPawnIcon;
		case KNIGHT:
			if (team)
				scaledPieceIcon = wKnightIcon;
			else
				scaledPieceIcon = bKnightIcon;		
		case BISHOP:
			if (team)
				scaledPieceIcon = wBishopIcon;
			else
				scaledPieceIcon = bBishopIcon;
		case ROOK:
			if (team)
				scaledPieceIcon = wRookIcon;
			else
				scaledPieceIcon = bRookIcon;
		case QUEEN:
			if (team)
				scaledPieceIcon = wQueenIcon;
			else
				scaledPieceIcon = bQueenIcon;
		case KING:
			if (team)
				scaledPieceIcon = wKingIcon;
			else
				scaledPieceIcon = bKingIcon;
		}
		picture.setIcon(scaledPieceIcon);
		this.add(picture);
		
		if(white)
			this.setBackground(new Color(255,255,255));
		else
			this.setBackground(Color.DARK_GRAY);
		this.update(getGraphics());
	}
	private void initializeIcons() {
		initializeIcons("images/");
	}
	private void initializeIcons(String path) {
		bPawnIcon = new ImageIcon(path+"bPawn.jpg");
		bKnightIcon =new ImageIcon(path+"bKnight.jpg");
		bBishopIcon = new ImageIcon(path+"bBishop.jpg");
		bRookIcon = new ImageIcon(path+"bRook.jpg");
		bQueenIcon =new ImageIcon(path+"bQueen.jpg");
		bKingIcon = new ImageIcon(path+"bKing.jpg");
		
		wPawnIcon = new ImageIcon(path+"wPawn.jpg");
		wKnightIcon= new ImageIcon(path+"wKnight.jpg"); 
		wBishopIcon= new ImageIcon(path+"wBishop.jpg");
		wRookIcon= new ImageIcon(path+"wRook.jpg"); 
		wQueenIcon= new ImageIcon(path+"wQueen.jpg"); 
		wKingIcon= new ImageIcon(path+"wKing.jpg");
	}
}
