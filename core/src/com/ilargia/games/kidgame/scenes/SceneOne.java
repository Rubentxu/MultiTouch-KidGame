package com.ilargia.games.kidgame.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ilargia.games.egdx.api.Engine;
import com.ilargia.games.egdx.api.factories.SceneFactory;
import com.ilargia.games.egdx.impl.managers.AssetsManagerGDX;
import com.ilargia.games.egdx.impl.managers.SceneManagerGDX;
import com.ilargia.games.egdx.logicbricks.gen.Entitas;
import com.ilargia.games.egdx.logicbricks.gen.game.GameEntity;
import com.ilargia.games.entitas.Entity;


public class SceneOne implements SceneFactory<Engine, Entitas> {

    @Override
    public void createScene(Engine engine, Entitas entitas) {
        AssetsManagerGDX assetsManager = engine.getManager(AssetsManagerGDX.class);
        assetsManager.loadTexture("imagenes/fondos/fondo.jpg");
//        assetsManager.loadTexture("imagenes/fondos/nubes.png");
//        assetsManager.loadTexture("imagenes/fondos/arboles.png");
        assetsManager.finishLoading();


        entitas.scene.createEntity()
                .addParallaxLayer(new TextureRegion(assetsManager.getTexture("imagenes/fondos/fondo.jpg"))
                        , new Vector2(0.7f,0f),new Vector2(0, 1),new Vector2(0, 0));


        entitas.scene.createEntity()
                .addCConeLight(35, Color.GREEN, 40, new Vector2(16,13),250, 60);

        SceneManagerGDX sceneManager = engine.getManager(SceneManagerGDX.class);
        GameEntity ground = sceneManager.createEntity("Ground");
        ground.getRigidBody().body.setTransform(10, 1, 0);

        GameEntity box1 = sceneManager.createEntity("Box");
        box1.addTags("Box1").setInteractive(true).getRigidBody().body.setTransform(20,5,0);

        GameEntity box2 = sceneManager.createEntity("Box");
        box2.addTags("Box2").setInteractive(true).getRigidBody().body.setTransform(10,5,0);

        GameEntity box3 = sceneManager.createEntity("Box");
        box3.addTags("Box3").setInteractive(true).getRigidBody().body.setTransform(15,5,0);

        entitas.actuator.setDragActuator(ground.getCreationIndex(), false, 1000);


        GameEntity patternBox1 = sceneManager.createEntity("PatternBox");
        patternBox1.addTags("PatternBox1").setInteractive(true).getRigidBody().body.setTransform(20,12,0);

        GameEntity patternBox2 = sceneManager.createEntity("PatternBox");
        patternBox2.addTags("PatternBox2").setInteractive(true).getRigidBody().body.setTransform(10,12,0);

        GameEntity patternBox3 = sceneManager.createEntity("PatternBox");
        patternBox3.addTags("PatternBox3").setInteractive(true).getRigidBody().body.setTransform(15,12,0);


    }
}
