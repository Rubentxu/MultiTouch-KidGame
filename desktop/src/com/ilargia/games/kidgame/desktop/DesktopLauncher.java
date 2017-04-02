package com.ilargia.games.kidgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ilargia.games.egdx.impl.managers.PreferencesManagerGDX;
import com.ilargia.games.kidgame.KidGame;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		KidGame game = new KidGame();
		game.preferencesManager = new PreferencesManagerGDX();

		config.title = game.preferencesManager.APP_NAME;
		config.width = game.preferencesManager.VIRTUAL_DEVICE_WIDTH;
		config.height = game.preferencesManager.VIRTUAL_DEVICE_HEIGHT;
		new LwjglApplication(game, config);
	}
}
