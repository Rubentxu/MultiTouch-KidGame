package com.ilargia.games.kidgame.systems;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;
import com.ilargia.games.egdx.impl.EngineGDX;
import com.ilargia.games.egdx.impl.managers.InputManagerGDX;
import com.ilargia.games.egdx.logicbricks.gen.Entitas;
import com.ilargia.games.egdx.logicbricks.gen.game.GameEntity;
import com.ilargia.games.egdx.logicbricks.gen.game.GameMatcher;
import com.ilargia.games.entitas.api.system.IExecuteSystem;
import com.ilargia.games.entitas.api.system.IInitializeSystem;
import com.ilargia.games.entitas.group.Group;


public class LimitsSceneSystem implements IInitializeSystem, IExecuteSystem {

    private final EngineGDX engine;
    private final Group<GameEntity> group;
    private InputManagerGDX inputManager;
    private Camera camera;
    private float limitRigth;
    private float limitLeft;
    private float limitUp;
    private World physics;


    public LimitsSceneSystem(Entitas entitas, EngineGDX engine) {
        this.engine = engine;
        this.group = entitas.game.getGroup(GameMatcher.RigidBody());

    }

    @Override
    public void initialize() {
        this.inputManager = engine.getManager(InputManagerGDX.class);
        this.physics = engine.getPhysics();
        camera = engine.getCamera();
        limitRigth = camera.position.x + camera.viewportWidth / 2;
        limitLeft = camera.position.x - camera.viewportWidth / 2;
        limitUp = camera.position.y + camera.viewportHeight / 2;

    }

    @Override
    public void execute(float deltaTime) {

        for (GameEntity gameEntity : group.getEntities()) {
            Body body = gameEntity.getRigidBody().body;
            Vector2 position = body.getPosition();
            Vector2 velocity = body.getLinearVelocity();
            if (position.x - 0.8f > limitRigth) {
                position.x = limitLeft + 1;
                Transform tranform = body.getTransform();
                tranform.setPosition(position);
                body.setTransform(tranform.getPosition(), tranform.getRotation());
                destroyJoint(body);

            }

            if (position.x + 0.8f < limitLeft) {
                position.x = limitRigth - 1;
                Transform tranform = body.getTransform();
                tranform.setPosition(position);
                body.setTransform(tranform.getPosition(), tranform.getRotation());
                destroyJoint(body);
            }

            if (position.y - 0.8f > limitUp) {
                velocity.y = -Math.abs(velocity.y);
                destroyJoint(body);
            }

            velocity.x -= 0.1f;
            velocity.y -= 0.1f;
            body.setLinearVelocity(velocity);

        }

    }

    private void destroyJoint(Body body) {
        for (int i = 0; i < 5; i++) {
            if (inputManager.isTouchDown(i) && inputManager.getTouchState(i).joint != null
                    && inputManager.getTouchState(i).joint.getBodyB() == body) {
                physics.destroyJoint(inputManager.getTouchState(i).joint);
                inputManager.getTouchState(i).joint = null;
            }
        }
    }

}
