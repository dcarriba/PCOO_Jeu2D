package com.game.controller.player;

import com.game.model.entities.Player;
import com.game.model.inputhandler.player.PlayerInputHandler;

/**
 * The <code>PlayerController</code> class handles the logic for controlling the player's actions.
 * It updates the player and processes user input to control the player’s movement and actions.
 */
public class PlayerController {
    /** The player being controlled */
    private final Player player;
    /** The handler for processing player input */
    private final PlayerInputHandler playerInputHandler;

    /**
     * Constructor to initialize the player controller.
     * @param player The player to be controlled
     */
    public PlayerController(Player player) {
        this.player = player;
        this.playerInputHandler = new PlayerInputHandler(player);
    }

    /**
     * Controls the player by updating the player and handling input.
     * It is called each frame to process player movement and actions.
     */
    public void control(){
        player.update();
        playerInputHandler.handleInput();
        player.update();
    }
}
