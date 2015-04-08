package de.htwg.se.setgame;

import com.google.inject.AbstractModule;

import de.htwg.se.setgame.controller.IController;
import de.htwg.se.setgame.model.IField;

public class SetGameModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IField.class).to(de.htwg.se.setgame.model.impl.Field.class);
		bind(IController.class).to(
				de.htwg.se.setgame.controller.impl.SetController.class);

	}

}
