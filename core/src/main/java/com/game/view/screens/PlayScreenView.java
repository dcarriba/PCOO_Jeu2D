package com.game.view.screens;

import com.game.model.entities.Enemy;
import com.game.model.screens.PlayScreen;

/**
 * The <code>PlayScreenView</code> class is responsible for rendering the playScreen,
 * including the worldMap, player, and enemies.
 */
public class PlayScreenView implements ScreenView {
    /** The PlayScreen instance to be rendered. */
    private final PlayScreen playScreen;

    /**
     * Constructor to initialize the PlayScreenView with the given PlayScreen.
     * @param playScreen The PlayScreen instance to be rendered.
     */
    public PlayScreenView(PlayScreen playScreen){
        this.playScreen = playScreen;
    }

    /** Updates the camera position to follow the player. */
    public void update(){
        playScreen.getGamecam().position.x = playScreen.getPlayer().getPositionX() + 8;
        playScreen.getGamecam().position.y = playScreen.getPlayer().getPositionY() + 8;
        playScreen.getGamecam().update(); // Make sure camera is updated after position change
    }

    /** Renders the map, player, and enemies on the screen. */
    @Override
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
