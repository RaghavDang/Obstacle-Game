package com.brainmentors.gaming;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	public GameFrame()
	{
		Board board=new Board();
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     setTitle("Game Dev in Java");
		 setSize(1500, 920); 
		 setLocationRelativeTo(null);
		 setResizable(false);
		 add(board);
		 setVisible(true);	
	}
	public static void main(String args[])
	{
		new GameFrame();
		
	}

}
