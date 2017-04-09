package com.ilargia.games.kidgame.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.ilargia.games.egdx.impl.GameStateGDX;
import com.ilargia.games.egdx.impl.managers.PhysicsManagerGDX;
import com.ilargia.games.egdx.impl.managers.SceneManagerGDX;
import com.ilargia.games.egdx.logicbricks.gen.Entitas;
import com.ilargia.games.egdx.logicbricks.index.Indexed;
import com.ilargia.games.egdx.logicbricks.system.actuator.ActuatorSystem;
import com.ilargia.games.egdx.logicbricks.system.actuator.CreateRadialGravityActuatorSystem;
import com.ilargia.games.egdx.logicbricks.system.actuator.RadialGravityActuatorSystem;
import com.ilargia.games.egdx.logicbricks.system.game.*;
import com.ilargia.games.egdx.logicbricks.system.render.BackgroundRenderSystem;
import com.ilargia.games.egdx.logicbricks.system.render.DebugRendererSystem;
import com.ilargia.games.egdx.logicbricks.system.render.LigthRendererSystem;
import com.ilargia.games.egdx.logicbricks.system.render.TextureRendererSystem;
import com.ilargia.games.egdx.logicbricks.system.scene.SceneSystem;
import com.ilargia.games.egdx.logicbricks.system.sensor.*;
import com.ilargia.games.kidgame.KigGameEngine;
import com.ilargia.games.kidgame.entities.Box;
import com.ilargia.games.kidgame.entities.FixedBox;
import com.ilargia.games.kidgame.entities.Ground;
import com.ilargia.games.kidgame.entities.PatternBox;
import com.ilargia.games.kidgame.scenes.SceneOne;
import com.ilargia.games.kidgame.systems.LimitsSceneSystem;
import com.ilargia.games.kidgame.systems.PatternSystem;


public class KidGameSceneState extends GameStateGDX {
    private final KigGameEngine engine;
    private final Entitas entitas;
    private SceneManagerGDX sceneManager;

    public KidGameSceneState(KigGameEngine engine, Entitas entitas) {
        this.engine = engine;
        this.entitas = entitas;

    }

    @Override
    public void loadResources() {
        sceneManager = engine.getManager(SceneManagerGDX.class);
        sceneManager.addEntityFactory("Ground", new Ground());
        sceneManager.addEntityFactory("Box", new Box());
        sceneManager.addEntityFactory("FixedBox", new FixedBox());
        sceneManager.addEntityFactory("PatternBox", new PatternBox());
        sceneManager.addSceneFactory("Pruebas", new SceneOne());
        sceneManager.initialize();
    }

    @Override
    public void initialize() {
        Indexed.initialize(entitas);
        entitas.scene.setCamera((OrthographicCamera) engine.getManager(SceneManagerGDX.class).getDefaultCamera());

        systems.add(new CollisionSensorSystem(entitas, engine))
                .add(new CreateNearSensorSystem(entitas, engine))
                .add(new CreateRadarSensorSystem(entitas, engine))
                .add(new DelaySensorSystem(entitas))
                .add(new NearSensorSystem(entitas, engine))
                .add(new RadarSensorSystem(entitas, engine))
                .add(new RaySensorSystem(entitas, engine.getManager(PhysicsManagerGDX.class).getPhysics()))
                .add(new AddInputControllerSystem(entitas, engine))
                .add(new SceneSystem(entitas, engine))
                .add(new RigidBodySystem(entitas))
                .add(new AnimationSystem(entitas))
                .add(new ActuatorSystem(entitas))
                .add(new CreateRadialGravityActuatorSystem(entitas, engine))
                .add(new RadialGravityActuatorSystem(entitas))
                .add(new LimitsSceneSystem(entitas, engine))
                .add(new PatternSystem(entitas, engine))
                .add(new LigthRendererSystem(entitas, engine))
                .add(new BackgroundRenderSystem(entitas, engine))
                .add(new TextureRendererSystem(entitas, engine))
//                .add(new DebugRendererSystem(entitas, engine.getManager(PhysicsManagerGDX.class).getPhysics(),
//                        engine.getManager(SceneManagerGDX.class).getBatch()))
                .add(new DestroySystem(entitas, engine));

        sceneManager.createScene("Pruebas");
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void unloadResources() {
        //context.core.destroyAllEntities();
        systems.clearSystems();
    }

}
