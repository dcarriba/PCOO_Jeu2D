package com.game.model.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.model.entities.Player;
import com.game.model.map.WorldMap;
import com.game.model.scenes.Hud;
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
    /** The hud displayed on top of the playScreen */
    private final Hud hud;

    /**
     * Constructor to initialize the PlayScreen.
     * @param batch The sprite batch used for rendering.
     * @param worldMap The world map used in the game.
     * @param player The player.
     * @param settings The game settings (width and height).
     */
    public PlayScreen(SpriteBatch batch, WorldMap worldMap, Player player, Settings settings){
        this.batch = batch;
        this.gamecam = new OrthographicCamera();
        int viewportWidth = settings.getWidth()/4;
        int viewportHeight = settings.getHeight()/4;
        this.viewport = new ExtendViewport(viewportWidth, viewportHeight, this.gamecam);
        this.worldMap = worldMap;
        this.gamecam.position.set(viewportWidth /2f, viewportHeight /2f, 0);
        this.player = player;
        this.hud = new Hud(this);
    }

    @Override
    public void resize(int screenWidth, int screenHeight){
        viewport.update(screenWidth, screenHeight);
        hud.resize();
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

    public Hud getHud() {
        return hud;
    }

    public OrthographicCamera getGamecam() {
        return gamecam;
    }

    public void dispose(){
        if (hud != null) hud.dispose();
    }
}
