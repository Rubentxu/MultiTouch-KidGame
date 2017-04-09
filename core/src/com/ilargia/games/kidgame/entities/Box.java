package com.ilargia.games.kidgame.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ilargia.games.egdx.api.Engine;
import com.ilargia.games.egdx.api.factories.EntityFactory;
import com.ilargia.games.egdx.impl.managers.AssetsManagerGDX;
import com.ilargia.games.egdx.impl.managers.PhysicsManagerGDX;
import com.ilargia.games.egdx.logicbricks.data.Bounds;
import com.ilargia.games.egdx.logicbricks.gen.Entitas;
import com.ilargia.games.egdx.logicbricks.gen.game.GameEntity;
import com.ilargia.games.egdx.util.BodyBuilder;


public class Box implements EntityFactory<Entitas, GameEntity> {
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
                .addRigidBody(bodyBuilder.fixture(bodyBuilder.fixtureDefBuilder
                        .boxShape(width, height)
                        .friction(1f)
                        .restitution(0.08f)
                        .density(1f))
                        .type(BodyDef.BodyType.DynamicBody)
                        .build())
                .setDraggable(true);


        return entity;

    }

}
