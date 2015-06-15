package de.htwg.se.setgame;

import com.google.inject.AbstractModule;
import de.htwg.se.setgame.controller.IController;
import de.htwg.se.setgame.controller.impl.SetController;
import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.model.impl.ModelFactory;
import de.htwg.se.setgame.util.persistence.IGameDao;
import de.htwg.se.setgame.util.persistence.hibernate.GameDao;

public class SetGameModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IModelFactory.class).to(ModelFactory.class);
		bind(IController.class).to(SetController.class);
        bind(IGameDao.class).to(GameDao.class);


    }

}
