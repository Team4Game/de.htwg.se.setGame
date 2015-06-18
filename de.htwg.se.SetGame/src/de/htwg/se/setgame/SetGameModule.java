package de.htwg.se.setgame;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import de.htwg.se.setgame.controller.IController;
import de.htwg.se.setgame.controller.IKiPlugin;
import de.htwg.se.setgame.controller.impl.SetController;
import de.htwg.se.setgame.controller.impl.ki.KIHard;
import de.htwg.se.setgame.controller.impl.ki.KIEasy;
import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.model.impl.ModelFactory;
import de.htwg.se.setgame.util.persistence.IGameDao;
import de.htwg.se.setgame.util.persistence.db4o.GameDao;


public class SetGameModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IModelFactory.class).to(ModelFactory.class);
		bind(IController.class).to(SetController.class);
        bind(IGameDao.class).to(GameDao.class);
        Multibinder<IKiPlugin> plugins =
                Multibinder.newSetBinder(binder(), IKiPlugin.class);
        plugins.addBinding().to(KIEasy.class);
        plugins.addBinding().to(KIHard.class);



    }

}
