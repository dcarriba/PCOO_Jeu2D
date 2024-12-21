package com.game.controller;

import com.badlogic.gdx.Gdx;
import com.game.model.entities.Player;
import com.game.controller.inputhandler.player.PlayerInputHandler;

public class PlayerController {
    private final Player player;
    private final PlayerInputHandler playerInputHandler;

    public PlayerController(Player player) {
        this.player = player;
        this.playerInputHandler = new PlayerInputHandler(player);
    }

    public Player getPlayer() {
        return player;
    }

    public void update(){
        player.update(Gdx.graphics.getDeltaTime());
        playerInputHandler.handleInput();
        player.update(Gdx.graphics.getDeltaTime());
    }
}
