package com.game.model.activescreen;

import com.game.model.screens.Screen;
import com.game.view.screens.ScreenView;

/**
 * The <code>ActiveScreen</code> class holds the current screen and its corresponding view.
 * It is used to manage the active screen and render its view in the game.
 */
public class ActiveScreen {
    /** The current screen being displayed in the game. */
    private final Screen screen;
    /** The view that renders the current screen. */
    private final ScreenView screenView;

    /**
     * Initialize the activeScreen with the specified screen and screenView.
     * @param screen the screen to be displayed
     * @param screenView the view that renders the screen
     */
    public ActiveScreen(Screen screen, ScreenView screenView) {
        this.screen = screen;
        this.screenView = screenView;
    }

    /**
     * Gets the current screen.
     * @return the current screen
     */
    public Screen getScreen() {
        return screen;
    }

    /**
     * Gets the view associated with the current screen.
     * @return the screen view
     */
    public ScreenView getScreenView() {
        return screenView;
    }
}
