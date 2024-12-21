package com.game.view.scenes;

import com.game.model.scenes.Hud;
import com.game.model.screens.PlayScreen;

public class HudView {
    private final PlayScreen playScreen;
    private final Hud hud;

    public HudView(PlayScreen playScreen, Hud hud) {
        this.playScreen = playScreen;
        this.hud = hud;
    }

    public void render(){
        playScreen.getBatch().setProjectionMatrix(hud.getStage().getCamera().combined);
        hud.getStage().draw();  // Draw the HUD
    }
}
