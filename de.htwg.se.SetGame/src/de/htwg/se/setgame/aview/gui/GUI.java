package de.htwg.se.setgame.aview.gui;

import java.awt.GridLayout;

import javax.swing.*;

import com.google.inject.Inject;

import de.htwg.se.setgame.controller.IController;
import de.htwg.se.setgame.util.observer.Event;
import de.htwg.se.setgame.util.observer.IObserver;

public class GUI extends JFrame implements IObserver {
	
	private static final long serialVersionUID = 1L;	
	private static IController controller;
	@SuppressWarnings("unused")
	private JPanel mainPanel;
	
	private SetButton setbutton;
	private GameField gamefield;
	
	@Inject
	public GUI(final IController controller) {
		GUI.setController(controller);
		controller.addObserver(this);

		this.setJMenuBar(new MenuBar());

		JPanel mainPanel = new JPanel();
		JPanel miniPanel = new JPanel();
		
		
		mainPanel.setLayout(new GridLayout(2,1));
		miniPanel.setLayout(new GridLayout(2,1));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		setbutton = new SetButton();
		gamefield = new GameField();
		mainPanel.add(gamefield);
		miniPanel.add(setbutton);
		mainPanel.add(miniPanel);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setContentPane(mainPanel);

		this.setTitle("SET - The ultimate challenge!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
	}


//	public static void main(String[] args) {
//		IController controller = new SetController();
//		controller.newGame();
//		new GUI(controller);
//	}


	@SuppressWarnings("static-access")
	@Override
	public void update(Event e) {
	 setbutton.updateSB();
	 gamefield.updateLink();
	 
		
	}


	public static IController getController() {
		return controller;
	}


	public static void setController(IController controller) {
		GUI.controller = controller;
	}
}
