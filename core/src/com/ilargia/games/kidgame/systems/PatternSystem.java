package com.ilargia.games.kidgame.systems;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;
import com.ilargia.games.egdx.impl.EngineGDX;
import com.ilargia.games.egdx.impl.managers.InputManagerGDX;
import com.ilargia.games.egdx.impl.managers.SceneManagerGDX;
import com.ilargia.games.egdx.logicbricks.gen.Entitas;
import com.ilargia.games.egdx.logicbricks.gen.game.GameEntity;
import com.ilargia.games.egdx.logicbricks.gen.game.GameMatcher;
import com.ilargia.games.egdx.logicbricks.gen.sensor.SensorEntity;
import com.ilargia.games.egdx.logicbricks.gen.sensor.SensorMatcher;
import com.ilargia.games.egdx.logicbricks.index.Indexed;
import com.ilargia.games.entitas.api.system.IExecuteSystem;
import com.ilargia.games.entitas.api.system.IInitializeSystem;
import com.ilargia.games.entitas.group.Group;


public class PatternSystem implements IInitializeSystem, IExecuteSystem {

    private final EngineGDX engine;
    private final Group<SensorEntity> group;
    private final Entitas entitas;
    private SceneManagerGDX sceneManager;
    private World physics;


    public PatternSystem(Entitas entitas, EngineGDX engine) {
        this.engine = engine;
        this.entitas = entitas;
        this.group = entitas.sensor.getGroup(SensorMatcher.NearSensor());

    }

    @Override
    public void initialize() {
        this.sceneManager = engine.getManager(SceneManagerGDX.class);
        this.physics = engine.getPhysics();

    }

    @Override
    public void execute(float deltaTime) {

        for (SensorEntity sensorEntity : group.getEntities()) {
            GameEntity patterEntity = Indexed.getInteractiveEntity(sensorEntity.getLink().ownerEntity);
            Body patternBody = patterEntity.getRigidBody().body;
            if(sensorEntity.getLink().pulse) {
                for (Integer indexGameEntity : sensorEntity.getNearSensor().distanceContactList) {
                    GameEntity entityInSensor = Indexed.getInteractiveEntity(indexGameEntity);
                    Body bodyInSensor = entityInSensor.getRigidBody().body;
                    Vector2 position1 = patternBody.getPosition();
                    Vector2 position2 = bodyInSensor.getPosition();

                    float distSqr = position1.dst2(position2);
                    float minRange = 10f;
                    if (distSqr < (minRange * minRange)) {
                        GameEntity fixedBoxBox = sceneManager.createEntity("FixedBox");
                        fixedBoxBox.setInteractive(true).getRigidBody().body.setTransform(patternBody.getPosition(),0);
                        entityInSensor.setDestroy(true);
                        patterEntity.setDestroy(true);
                        sensorEntity.getNearSensor().distanceContactList.clear();
                        entitas.sensor.destroyEntity(sensorEntity);
                    }
                }
            }


        }

    }


}
