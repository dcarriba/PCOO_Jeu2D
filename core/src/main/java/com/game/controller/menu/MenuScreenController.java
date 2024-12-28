package com.game.controller.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.game.model.screens.MenuScreen;

public class MenuScreenController {
    private final MenuScreen menuScreen;

    public MenuScreenController(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
    }

    public void createMenuScreenButtonsListeners() {
        // Listener for New Game button
        menuScreen.getNewGameButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuScreen.getGame().startNewGame();
            }
        });

        // Listener for Load Game button
        menuScreen.getLoadGameButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (menuScreen.getGame().isSaveAvailable()) {
                    menuScreen.getGame().loadGame();
                } else {
                    menuScreen.showNoSaveMessage();
                }
            }
        });

        Gdx.input.setInputProcessor(menuScreen.getStage());
    }
}
