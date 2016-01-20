package de.htwg.se.setgame;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import de.htwg.se.setgame.controller.IController;
import de.htwg.se.setgame.controller.IPlugin;
import de.htwg.se.setgame.controller.impl.SetController;
import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.model.impl.ModelFactory;
import de.htwg.se.setgame.controller.impl.ki.EasyKi;
import de.htwg.se.setgame.controller.impl.ki.MediumKi;
import de.htwg.se.setgame.controller.impl.ki.HardKi;
import de.htwg.se.setgame.util.persistance.IGameDao;
import de.htwg.se.setgame.util.persistance.couchDB.GameDao; //Google Guice @Injection Annotation

public class SetGameModule extends AbstractModule {


	@Override
	protected void configure() {
		bind(IModelFactory.class).to(ModelFactory.class);
		bind(IController.class).to(SetController.class);
		bind(IGameDao.class).to(GameDao.class);
		Multibinder<IPlugin> kiPlugins = Multibinder.newSetBinder(binder(), IPlugin.class);
		kiPlugins.addBinding().to(EasyKi.class);
		kiPlugins.addBinding().to(MediumKi.class);
		kiPlugins.addBinding().to(HardKi.class);

		//SetGame Module fuer eine Liste --> diese Liste in den Controller (IController) einfuegen


	}

}
