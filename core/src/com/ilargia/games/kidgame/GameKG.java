package com.ilargia.games.kidgame;

import com.badlogic.gdx.math.Interpolation;


import com.ilargia.games.egdx.api.ChangeStateCommand;
import com.ilargia.games.egdx.api.EventBus;
import com.ilargia.games.egdx.impl.GameGDX;
import com.ilargia.games.egdx.impl.managers.SceneManagerGDX;
import com.ilargia.games.egdx.transitions.SlideTransition;
import com.ilargia.games.kidgame.states.KidGameSceneState;
import net.engio.mbassy.listener.Handler;

public class GameKG extends GameGDX<KigGameEngine> {

    private SlideTransition slideTransition;
    private KidGameSceneState kidGameSceneState;

    public GameKG(KigGameEngine engine, EventBus bus) {
        super(engine, bus);

    }

    @Handler
    public void handleChangeState(ChangeStateCommand command) {
        command.change("GameState", this);
    }

    @Override
    public void init() {
        _engine.initialize();
        ebus.subscribe(this);
    }

    public SlideTransition getSlideTransition() {
        if (slideTransition == null)
            slideTransition = new SlideTransition(1, SlideTransition.DOWN, false, Interpolation.bounceOut, _engine.getManager(SceneManagerGDX.class).getBatch());
        return slideTransition;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public int getErrorState() {
        return 0;
    }

//    public PlatformExampleState getExampleState() {
//        if (kidGameSceneState == null)
//            kidGameSceneState = new PlatformExampleState(_engine);
//        return kidGameSceneState;
//    }

}
