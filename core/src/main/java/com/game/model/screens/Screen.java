package com.game.model.screens;

/**
 * The <code>Screen</code> interface represents a screen in the game.
 * It defines a method to resize the screen.
 */
public interface Screen {

    /**
     * Resizes the screen
     * @param screenWidth  The screen width.
     * @param screenHeight The screen height.
     */
    void resize(int screenWidth, int screenHeight);
}
