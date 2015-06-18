package de.htwg.se.setgame.controller.impl.service;

import de.htwg.se.setgame.controller.IController;
import de.htwg.se.setgame.controller.IKiPlugin;

/**
 * Created by raina on 18.06.2015.
 */
public class KiService {


    private boolean isPlaying;
    private IKiPlugin kiPlugin;
    private IController controller;

    public KiService( IController controller){
        this.controller = controller;
        kiPlugin = null;
    }

    /**
     *
     * @param kiPlugin ki that user choice
     */
    public void startKiPlugin(IKiPlugin kiPlugin) {
        isPlaying = true;
        if(this.kiPlugin != null) {
            this.kiPlugin.stopKI();
        }
        this.kiPlugin = kiPlugin;
        kiPlugin.setController(controller);
        kiPlugin.initKI();

    }

    /**
     *
     * @return if ki is on use
     */
    public boolean isKiPlaying(){
        return isPlaying;
    }

    /**
     * when a user found a set
     */
    public void notifyKi(){
        this.kiPlugin.gamerFoundASet();
    }




}
