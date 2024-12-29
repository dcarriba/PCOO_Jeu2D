package com.game.model.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.model.entities.Player;
import com.game.model.map.WorldMap;
import com.game.model.settings.Settings;

/**
 * The <code>PlayScreen</code> class represents the main gameplay screen.
 * It manages the camera, world map, and player.
 */
public class PlayScreen implements Screen {
    /** The batch used for drawing */
    private final SpriteBatch batch;
    /** The world map */
    private final WorldMap worldMap;
    /** The player */
    private final Player player;
    /** The camera used to view the world */
    private final OrthographicCamera gamecam;
    /** The viewport of the game screen */
    private final Viewport viewport;

    /**
     * Constructor to initialize the PlayScreen.
     * @param batch The sprite batch used for rendering.
     * @param worldMap The world map used in the game.
     * @param player The player.
     * @param settings The screen settings (width and height).
     */
    public PlayScreen(SpriteBatch batch, WorldMap worldMap, Player player, Settings settings){
        this.batch = batch;
        this.gamecam = new OrthographicCamera();
        int viewportWidth = settings.getWidth()/4;
        int viewportHeight = settings.getHeight()/4;
        this.viewport = new FitViewport(viewportWidth, viewportHeight, this.gamecam);
        this.worldMap = worldMap;
        this.gamecam.position.set(viewportWidth /2f, viewportHeight /2f, 0);
        this.player = player;
    }

    @Override
    public void updateViewport(int screenWidth, int screenHeight, boolean centerCamera){
        viewport.update(screenWidth, screenHeight, centerCamera);
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public WorldMap getWorldMap() {
        return worldMap;
    }

    public Player getPlayer() {
        return player;
    }

    public OrthographicCamera getGamecam() {
        return gamecam;
    }
}
