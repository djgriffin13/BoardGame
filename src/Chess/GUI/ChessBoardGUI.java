package Chess.GUI;

import java.awt.Dimension;
import java.awt.GridLayout;

import GeneralGame.GUI.GameBoardGUI;

public class ChessBoardGUI extends GameBoardGUI {
	ChessSpace[][] board;
	public ChessBoardGUI(){
		board = new ChessSpace[8][8];
		setLayout();
		for(int i= 0; i <8; ++i){
			for (int j =0;j<8;++j){
				board[i][j] = new ChessSpace();
			}
		}
		
	}

	private void setLayout() {
		this.setPreferredSize(new Dimension(400, 400));
		this.setLayout(new GridLayout(height, width));
		((GridLayout) this.getLayout()).setHgap(2);
		((GridLayout) this.getLayout()).setVgap(1);

	}

}
