package com.ilargia.games.kidgame.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ilargia.games.egdx.api.Engine;
import com.ilargia.games.egdx.api.factories.SceneFactory;
import com.ilargia.games.egdx.impl.managers.AssetsManagerGDX;
import com.ilargia.games.egdx.impl.managers.SceneManagerGDX;
import com.ilargia.games.egdx.logicbricks.data.Bounds;
import com.ilargia.games.egdx.logicbricks.gen.Entitas;
import com.ilargia.games.egdx.logicbricks.gen.game.GameEntity;
import com.ilargia.games.entitas.Entity;


public class SceneOne implements SceneFactory<Engine, Entitas> {
    private String box = "kidGame/box-%s.png";

    @Override
    public void createScene(Engine engine, Entitas entitas) {
        AssetsManagerGDX assetsManager = engine.getManager(AssetsManagerGDX.class);
//        assetsManager.loadTexture("imagenes/fondos/fondo.jpg");
        assetsManager.loadTexture("kidGame/fondoMadera.jpg");
        assetsManager.loadTexture("kidGame/rectangle.png");
        assetsManager.loadTexture("kidGame/box-a.png");
        assetsManager.loadTexture("kidGame/box-d.png");
        assetsManager.loadTexture("kidGame/box-f.png");
        assetsManager.finishLoading();

        float width = 1.2f;
        float height =  1.2f;



//        entitas.scene.createEntity()
//                .addCConeLight(35, Color.GREEN, 40, new Vector2(16,13),250, 60);

        SceneManagerGDX sceneManager = engine.getManager(SceneManagerGDX.class);
        GameEntity ground = sceneManager.createEntity("Ground");
        ground.addTextureView(new TextureRegion(assetsManager.getTexture("kidGame/rectangle.png")), new Bounds(50, 0.7f),
                false, false, 1, 0, Color.WHITE).getRigidBody().body.setTransform(10, 4, 0);



        GameEntity box1 = sceneManager.createEntity("Box");
        box1.addTags("Box4").setInteractive(true)
                .addTextureView(new TextureRegion(assetsManager.getTexture(String.format(box,"a"))), new Bounds(width, height),
                        false, false, 1, 0, Color.WHITE)
                .getRigidBody().body.setTransform(20,5,0);

        GameEntity box2 = sceneManager.createEntity("Box");
        box2.addTags("Box5").setInteractive(true)
                .addTextureView(new TextureRegion(assetsManager.getTexture(String.format(box,"d"))), new Bounds(width, height),
                        false, false, 1, 0, Color.WHITE)
                .getRigidBody().body.setTransform(10,5,0);

        GameEntity box3 = sceneManager.createEntity("Box");
        box3.addTags("Box6").setInteractive(true)
                .addTextureView(new TextureRegion(assetsManager.getTexture(String.format(box,"f"))), new Bounds(width, height),
                false, false, 1, 0, Color.WHITE)
                .getRigidBody().body.setTransform(15,5,0);

        entitas.actuator.setDragActuator(ground.getCreationIndex(), false, 1000);




        GameEntity patternBox1 = sceneManager.createEntity("PatternBox");
        patternBox1.addTags("PatternBox1").setInteractive(true)
                .addTextureView(new TextureRegion(assetsManager.getTexture(String.format(box,"a"))), new Bounds(width, height),
                        false, false, 0.7f, 0, Color.WHITE)
                .getRigidBody().body.setTransform(20,17,0);

        GameEntity patternBox2 = sceneManager.createEntity("PatternBox");
        patternBox2.addTags("PatternBox2").setInteractive(true)
                .addTextureView(new TextureRegion(assetsManager.getTexture(String.format(box,"d"))), new Bounds(width, height),
                        false, false, 0.7f, 0, Color.WHITE)
                .getRigidBody().body.setTransform(10,17,0);

        GameEntity patternBox3 = sceneManager.createEntity("PatternBox");
        patternBox3.addTags("PatternBox3").setInteractive(true)
                .addTextureView(new TextureRegion(assetsManager.getTexture(String.format(box,"f"))), new Bounds(width, height),
                        false, false, 0.7f, 0, Color.WHITE)
                .getRigidBody().body.setTransform(15,17,0);

        entitas.scene.createEntity()
                .addParallaxLayer(new TextureRegion(assetsManager.getTexture("kidGame/fondoMadera.jpg"))
                        , new Vector2(0f,0f),new Vector2(0, 0),new Vector2(0, 0));



    }
}
