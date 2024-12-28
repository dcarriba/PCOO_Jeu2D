package com.game.view.screens;

import com.game.model.screens.MenuScreen;

public class MenuScreenView {
    private final MenuScreen menuScreen;

    public MenuScreenView(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
    }

    public void render() {
        menuScreen.getStage().draw();
    }
}
