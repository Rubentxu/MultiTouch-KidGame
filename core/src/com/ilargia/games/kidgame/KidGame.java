package com.ilargia.games.kidgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ilargia.games.egdx.api.managers.LogManager;
import com.ilargia.games.egdx.impl.EventBusGDX;
import com.ilargia.games.egdx.impl.managers.*;
import com.ilargia.games.egdx.logicbricks.gen.Entitas;
import com.ilargia.games.kidgame.states.KidGameSceneState;
import net.engio.mbassy.bus.MBassador;

public class KidGame implements ApplicationListener {
	public static PreferencesManagerGDX preferencesManager;
	private static GameKG game;
	private KigGameEngine engine;
	private Entitas entitas;

	@Override
	public void create() {
		engine = new KigGameEngine();
		entitas = new Entitas();

		preferencesManager.LOG_LEVEL = LogManager.LOG_DEBUG;
		AssetManager assetsManager = new AssetManager();

		engine.addManager(new AssetsManagerGDX(assetsManager, preferencesManager));
		engine.addManager(new PhysicsManagerGDX(new Vector2(0,-9.8f)));
		engine.addManager(new GUIManagerGDX(new ScreenViewport(), new BitmapFont(), engine));
		engine.addManager(new SceneManagerGDX(engine, entitas));
		engine.addManager(new LogManagerGDX(preferencesManager));
		engine.addManager(new InputManagerGDX(entitas, engine));
		engine.addManager(preferencesManager);

		game = new GameKG(engine, new EventBusGDX(new MBassador()));
		game.init();
		game.pushState(new KidGameSceneState(engine, entitas));

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render() {
		game.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {


	}


}