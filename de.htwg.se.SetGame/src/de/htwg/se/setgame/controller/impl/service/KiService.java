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
    public void startKiPlugin(IKiPlugin kiPlugin) {
        isPlaying = true;
        if(this.kiPlugin != null) {
            this.kiPlugin.stopKI();
        }
        this.kiPlugin = kiPlugin;
        kiPlugin.setController(controller);
        kiPlugin.initKI();

    }
    public boolean isKiPlaying(){
        return isPlaying;
    }
    public void stopKI(){
        isPlaying = false;
        if(this.kiPlugin != null){
            this.kiPlugin.stopKI();
        }
    }
    public void notifyKi(){
        this.kiPlugin.gamerFoundASet();
    }




}
