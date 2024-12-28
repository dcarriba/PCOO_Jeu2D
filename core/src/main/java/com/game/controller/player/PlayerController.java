package com.game.controller.player;

import com.game.model.entities.Player;
import com.game.model.inputhandler.player.PlayerInputHandler;

public class PlayerController {
    private final Player player;
    private final PlayerInputHandler playerInputHandler;

    public PlayerController(Player player) {
        this.player = player;
        this.playerInputHandler = new PlayerInputHandler(player);
    }

    public void control(){
        player.update();
        playerInputHandler.handleInput();
        player.update();
    }
}
