package com.ilargia.games.kidgame.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ilargia.games.egdx.api.Engine;
import com.ilargia.games.egdx.api.factories.EntityFactory;
import com.ilargia.games.egdx.impl.managers.AssetsManagerGDX;
import com.ilargia.games.egdx.impl.managers.PhysicsManagerGDX;
import com.ilargia.games.egdx.logicbricks.data.Bounds;
import com.ilargia.games.egdx.logicbricks.gen.Entitas;
import com.ilargia.games.egdx.logicbricks.gen.game.GameEntity;
import com.ilargia.games.egdx.util.BodyBuilder;


public class PatternBox implements EntityFactory<Entitas, GameEntity> {
    private String box2 = "imagenes/textures/box2.png";
    private AssetsManagerGDX assetsManager;

    @Override
    public void loadAssets(Engine engine) {
        assetsManager = engine.getManager(AssetsManagerGDX.class);
        assetsManager.loadTexture(box2);
    }

    @Override
    public GameEntity create(Engine engine, Entitas entitas) {
        BodyBuilder bodyBuilder = engine.getManager(PhysicsManagerGDX.class).getBodyBuilder();

        float width = 1.2f;
        float height =  1.2f;

        GameEntity entity = entitas.game.createEntity()
                .addRigidBody(bodyBuilder
                        .type(BodyDef.BodyType.StaticBody)
                        .build());

        entitas.sensor.createEntity()
                .addNearSensor("Box"+entity.getCreationIndex(), 1.6f, 1.6f)
                .addLink(entity.getCreationIndex(), "PatternSensor");

        return entity;

    }

}
