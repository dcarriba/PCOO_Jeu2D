package com.game.view.scenes;

import com.game.model.scenes.Hud;

/** The <code>HudView</code> class is responsible for rendering the hud on the screen. */
public class HudView {
    /** The Hud instance to be rendered */
    private final Hud hud;

    /**
     * Constructor to initialize the HudView.
     * @param hud The Hud to be rendered.
     */
    public HudView(Hud hud) {
        this.hud = hud;
    }

    /** Renders the HUD by updating it and drawing it. */
    public void render(){
        hud.update();
        hud.getStage().draw();  // Draw the HUD
    }
}
