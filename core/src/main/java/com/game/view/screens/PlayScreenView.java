package com.game.view.screens;

import com.game.model.entities.Enemy;
import com.game.model.screens.PlayScreen;

public class PlayScreenView {
    private final PlayScreen playScreen;

    public PlayScreenView(PlayScreen playScreen){
        this.playScreen = playScreen;
    }

    public void update(){
        // Update the camera's view to follow the player
        playScreen.getGamecam().position.x = playScreen.getPlayer().getPositionX() + 8;
        playScreen.getGamecam().position.y = playScreen.getPlayer().getPositionY() + 8;

        // Make sure camera is updated after position change
        playScreen.getGamecam().update();
    }

    public void render(){
        update(); // updates the camera

        // Render the map
        playScreen.getWorldMap().getRenderer().setView(playScreen.getGamecam()); // Set the view for the map renderer based on camera
        playScreen.getWorldMap().getRenderer().render(); // Render the map

        // Set the projection matrix for the player (same camera as the map)
        playScreen.getBatch().setProjectionMatrix(playScreen.getGamecam().combined);

        // Begin the sprite batch for the player and enemies
        playScreen.getBatch().begin();
        // Draw player on top of the map
        playScreen.getBatch().draw(
            playScreen.getPlayer().getEntityAnimation().getCurrentFrame(),
            playScreen.getPlayer().getPositionX(),
            playScreen.getPlayer().getPositionY(),
            playScreen.getWorldMap().getTileWidth(),
            playScreen.getWorldMap().getTileHeight()
        );
        // Render all enemies
        for (Enemy enemy : playScreen.getWorldMap().getEnemies()) {
            // Render each enemy at its position
            playScreen.getBatch().draw(
                enemy.getEntityAnimation().getCurrentFrame(),
                enemy.getPositionX(),
                enemy.getPositionY(),
                playScreen.getWorldMap().getTileWidth(),
                playScreen.getWorldMap().getTileHeight()
            );
        }
        // End the sprite batch
        playScreen.getBatch().end();

    }
}
