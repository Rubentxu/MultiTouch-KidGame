package com.ilargia.games.kidgame;

import com.ilargia.games.egdx.impl.EngineGDX;
import com.ilargia.games.egdx.logicbricks.gen.Entitas;
import com.ilargia.games.entitas.factories.CollectionsFactories;

public class KigGameEngine extends EngineGDX {

    public KigGameEngine() {
        super(new CollectionsFactories() {});

    }

}
