package com.game.controller.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.game.model.screens.MenuScreen;

/**
 * The <code>MenuScreenController</code> class manages the input events for the menu screen,
 * including button clicks for starting a new game and loading an existing game.
 */
public class MenuScreenController {
    /** The MenuScreen object that this controller is managing */
    private final MenuScreen menuScreen;

    /**
     * Constructor to initialize the MenuScreenController.
     * @param menuScreen The MenuScreen that the controller will manage.
     */
    public MenuScreenController(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
    }

    /**
     * Creates and adds listeners to the buttons on the menu screen, including: <br>
     * - New Game button: Starts a new game when clicked. <br>
     * - Load Game button: Loads an existing game if a save is available, otherwise shows a "No Save Available" message.
     */
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
