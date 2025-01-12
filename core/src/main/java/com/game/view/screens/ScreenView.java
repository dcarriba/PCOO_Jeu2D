package com.game.view.screens;

/**
 * The <code>ScreenView</code> interface defines the contract for rendering screens in the game.
 * Implementing classes are responsible for rendering their respective screen contents.
 */
public interface ScreenView {

    /**
     * Renders the content of the screen.
     * This method is called every frame, to update the visuals of the screen.
     */
    void render();
}
