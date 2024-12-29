package com.game.view.scenes;

import com.game.model.scenes.Hud;
import com.game.model.screens.PlayScreen;

/** The <code>HudView</code> class is responsible for rendering the hud on the screen. */
public class HudView {
    /** The PlayScreen instance to get the sprite batch for rendering */
    private final PlayScreen playScreen;
    /** The Hud instance to be rendered */
    private final Hud hud;

    /**
     * Constructor to initialize the HudView.
     * @param playScreen The PlayScreen to retrieve the batch for rendering.
     * @param hud The Hud to be rendered.
     */
    public HudView(PlayScreen playScreen, Hud hud) {
        this.playScreen = playScreen;
        this.hud = hud;
    }

    /** Renders the HUD by updating it and drawing it on the batch of the playSscreen. */
    public void render(){
        hud.update();
        playScreen.getBatch().setProjectionMatrix(hud.getStage().getCamera().combined);
        hud.getStage().draw();  // Draw the HUD
    }
}
