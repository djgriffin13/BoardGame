package GeneralGame.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import Chess.GUI.ChessBoardGUI;

public class GameFrame extends JFrame {
	GameBoardGUI gbg;

	public GameFrame(){
		gbg = new ChessBoardGUI();
		this.setTitle("Chess");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(500,500));
		this.setContentPane(gbg);
		gbg.setLayout(new BorderLayout());
		this.pack();
		this.setVisible(true);

	}
}
