package de.htwg.se.setgame.aview.gui;

import javax.swing.*;


import java.awt.event.*;

/**
 * @author raina
 *
 */
public class MenuBar extends JMenuBar implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JMenuItem mHelp, mExit, mNewGame;
	
	public MenuBar() {
		
		JMenu menu = new JMenu("Game");
		mHelp = new JMenuItem("Help");
		mHelp.addActionListener(this);
		mExit = new JMenuItem("Exit");
		mExit.addActionListener(this);
		mNewGame = new JMenuItem("New Game");
		mNewGame.addActionListener(this);
		menu.add(mNewGame);
		menu.add(mHelp);
		menu.add(mExit);
		this.add(menu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	        if(e.getSource() == mHelp){
	        	JOptionPane.showConfirmDialog(null, ""
	        			+ "The SET game is a puzzle that uses cards\n"
	        			+ "which have 4 features on them:\n"
	        			+ "COLORS, SYMBOLS, SHADINGS\n"
	        			+ "and the NUMBER of symbols.\n\n"
	        			+ "The COLOR can be\n"
	        			+ "RED, GREEN or PURPLE...\n\n"
	        			+ "... the SYMBOLS can be\n"
	        			+ "SQUIGGLES, DIAMONDS or OVALS...\n\n"
	        			+ "...the SHADING can be\n"
	        			+ "SOLID, STRIPED or QUOLINED...\n\n"
	        			+ "...and for NUMBER, each card has either\n"
	        			+ "ONE, TWO or THREE symbols on it.\n\n"
	        			+ "There is only one rule to make a SET.\n\n"
	        			+ "A SET is 3 cards in which each individual feature is\n"
	        			+ "either all the SAME on each card...\n"
	        			+ "...or all DIFFEREN on each card.\n\n"
	        			+ "Have fun!", "Close", JOptionPane.CLOSED_OPTION);
	        }else if ( e.getSource() == mExit ){
	        	exit();
	        }else{
	        	if ( JOptionPane.showConfirmDialog(null,
	        			"Really start a new Game?", "Choice", JOptionPane.YES_NO_OPTION) == 0) {
	        		newGame();
	        	}
	    }
		
	}
	
	public void exit() {
		if(GUI.getController().geTplayerOnePoints() > GUI.getController().geTplayerTwoPoints()) {
    		playerOneWin();
    	} else if (GUI.getController().geTplayerOnePoints() < GUI.getController().geTplayerTwoPoints()) {
    		playerTwoWin();
    	} else {
    		noOneWin();
    	}
    	
    	GUI.getController().newGame();
    	
    	if(JOptionPane.showConfirmDialog(null,"Really finish the game?",
                    "Close",JOptionPane.YES_NO_OPTION) == 0){
    		System.exit(0);
    	}
	}
	
	public void newGame() {
		if(GUI.getController().geTplayerOnePoints() > GUI.getController().geTplayerTwoPoints()) {
    		playerOneWin();
    	} else if (GUI.getController().geTplayerOnePoints() < GUI.getController().geTplayerTwoPoints()) {
    		playerTwoWin();
    	} else {
    		noOneWin();
    	}
		
		GUI.getController().newGame();
	}
	
	public void playerOneWin() {
		JOptionPane.showConfirmDialog(null, "Player1 wins!", "Winner", JOptionPane.CLOSED_OPTION);
	}
	
	public void playerTwoWin() {
		JOptionPane.showConfirmDialog(null, "Player2 wins!", "Winner", JOptionPane.CLOSED_OPTION);
	}
	
	public void noOneWin() {
		JOptionPane.showConfirmDialog(null, "Dead heat!", "Fail", JOptionPane.CLOSED_OPTION);
	}

}
