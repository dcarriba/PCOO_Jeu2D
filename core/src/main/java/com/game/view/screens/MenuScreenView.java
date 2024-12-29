package com.game.view.screens;

import com.game.model.screens.MenuScreen;

/** The <code>MenuScreenView</code> class is responsible for rendering the menu screen. */
public class MenuScreenView implements ScreenView {
    /** The MenuScreen instance to be rendered */
    private final MenuScreen menuScreen;

    /**
     * Constructor to initialize the MenuScreenView.
     * @param menuScreen The MenuScreen to be rendered.
     */
    public MenuScreenView(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
    }

    /** Renders the MenuScreen by drawing its stage. */
    @Override
    public void render() {
        menuScreen.getStage().draw();
    }
}
