package de.htwg.se.setgame;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.setgame.aview.gui.GUI;
import de.htwg.se.setgame.aview.tui.TextUI;
import de.htwg.se.setgame.controller.IController;

public final class SetGame {
	private static Scanner scanner;
	private static TextUI tui;
	private IController controller;
	private static SetGame instance = null;
	
	public static SetGame getInstance(){
		if(instance == null){
			instance = new SetGame();
		}
		return instance;
	}
    private SetGame(){
        initLogger();
        initDependencyInjector();
        initUserInterface();
    }

    private void initLogger() {
        try {
            Properties props = new Properties();
            props.load(getClass().getResourceAsStream("/log4j.properties"));
            PropertyConfigurator.configure(props);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initDependencyInjector() {
        Injector injector = Guice.createInjector(new SetGameModule());
        controller = injector.getInstance(IController.class);
    }

    private void initUserInterface() {
        new GUI(controller);
        tui = new TextUI(controller);
        tui.printTUI();
    }
	public IController getIController(){
		return this.controller;
	}

	public TextUI getTextUI(){
		return this.tui;
	}

	public static void main(String[] args) {
		SetGame.getInstance();
		boolean cont = true;
		scanner = new Scanner(System.in);
		while(cont){ 
			cont = tui.processInputLine(scanner.nextLine());
		}
	}
		
	
}
