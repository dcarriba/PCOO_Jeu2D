package com.game.view.entities;

import com.game.model.entities.Player;
import com.game.model.screens.PlayScreen;

public class PlayerView {
    private final PlayScreen playScreen;
    private final Player player;

    public PlayerView(PlayScreen playScreen, Player player) {
        this.playScreen = playScreen;
        this.player = player;
    }

    public void render(){
        // Set the projection matrix for the player (same camera as the map)
        playScreen.getBatch().setProjectionMatrix(playScreen.getGamecam().combined);

        // Begin the sprite batch for the player
        playScreen.getBatch().begin();
        // Draw player on top of the map
        playScreen.getBatch().draw(player.getEntityAnimation().getCurrentFrame(), player.getPositionX(), player.getPositionY(), playScreen.getWorldMap().getTileWidth(), playScreen.getWorldMap().getTileHeight());
        playScreen.getBatch().end();
    }
}
