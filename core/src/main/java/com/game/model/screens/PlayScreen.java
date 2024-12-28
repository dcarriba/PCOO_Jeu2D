package com.game.model.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.model.entities.Player;
import com.game.model.map.WorldMap;
import com.game.model.settings.Settings;

public class PlayScreen implements Screen {
    private final SpriteBatch batch;
    private final WorldMap worldMap;
    private final Player player;
    private final OrthographicCamera gamecam;
    private final Viewport viewport;

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

    public Viewport getViewport() {
        return viewport;
    }
}
