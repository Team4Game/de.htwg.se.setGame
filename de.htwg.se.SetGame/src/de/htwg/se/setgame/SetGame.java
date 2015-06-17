package de.htwg.se.setgame;


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

    public static SetGame getInstance() {
        return getInstance(true);
    }

    public static SetGame getInstance(boolean activateGui) {
        if (instance == null) {
            instance = new SetGame(activateGui);
        }
        return instance;
    }

    private SetGame(boolean activateGui) {
        initLogger();
        initDependencyInjector();
        initUserInterface(activateGui);
    }

    private void initLogger() {
        PropertyConfigurator.configure("resources/log4j.properties");
    }

    private void initDependencyInjector() {
        Injector injector = Guice.createInjector(new SetGameModule());
        controller = injector.getInstance(IController.class);
    }

    private void initUserInterface(boolean activateGui) {
        if (activateGui) {
            new GUI(controller);
        }

        tui = new TextUI(controller);
        tui.printTUI();
    }

    public IController getIController() {
        return controller;
    }

    public TextUI getTextUI() {
        return tui;
    }

    public static void main(String[] args) {
        SetGame.getInstance();
        boolean cont = true;
        scanner = new Scanner(System.in);
        while (cont) {
            cont = tui.processInputLine(scanner.nextLine());
        }
    }


}
