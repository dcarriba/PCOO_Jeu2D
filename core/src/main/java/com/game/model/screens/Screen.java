package com.game.model.screens;

/**
 * The <code>Screen</code> interface represents a screen in the game.
 * It provides a method for updating the viewport.
 */
public interface Screen {

    /**
     * Updates the viewport based on the screen size and optional camera centering.
     * @param screenWidth The screen width.
     * @param screenHeight The screen height.
     * @param centerCamera Whether to center the camera after updating.
     */
    void updateViewport(int screenWidth, int screenHeight, boolean centerCamera);
}
