package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.entities.Player;
import com.game.main.Mygame;
import com.game.scenes.Hud;

public class PlayScreen implements Screen {
    private final Mygame game;
    private final OrthographicCamera gamecam;
    private final Viewport viewport;
    private final Hud hud;
    private final OrthogonalTiledMapRenderer renderer;
    private final Player player;

    public PlayScreen(Mygame game){
        this.game = game;
        this.gamecam = new OrthographicCamera();
        int viewportWidth = 320;
        int viewportHeight = 180;
//        this.viewport = new FitViewport(viewportWidth, viewportHeight, this.gamecam);
        this.viewport = new ExtendViewport(viewportWidth, viewportHeight, this.gamecam);
        this.hud = new Hud(this.game);
        TmxMapLoader maploader = new TmxMapLoader();
        TiledMap map = maploader.load("worlds/world1.tmx");
        this.renderer = new OrthogonalTiledMapRenderer(map);

        this.gamecam.position.set(viewportWidth /2f, viewportHeight /2f, 0);
        this.player = new Player(0, 0, "tilesets/characterstiles/char1_1.png", 16, 16, 0, 9);
    }

    public void update(float delta){
        player.update(delta);
        gamecam.position.x = player.getPositionX() + 8;
        gamecam.position.y = player.getPositionY() + 8; // Update the camera's view to follow the player
        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void show(){

    }

    @Override
    public void render(float delta){
//        update(delta);
//        renderer.render();
//        game.getBatch().setProjectionMatrix(hud.getStage().getCamera().combined);
//        hud.getStage().draw();
//        game.getBatch().begin();
//        player.update(delta);
//        player.draw(game.getBatch());
//        game.getBatch().end();
        // Update the camera to follow the player

        update(delta);
        gamecam.update();  // Make sure camera is updated after position change
        player.update(delta);  // Update player state (e.g., animation)

        // Render the map using the OrthogonalTiledMapRenderer
        renderer.setView(gamecam);  // Set the view for the map renderer based on camera
        renderer.render();  // Render the map

        // Set the projection matrix for the player (same camera as the map)
        game.getBatch().setProjectionMatrix(gamecam.combined);

        // Begin the sprite batch for the player
        game.getBatch().begin();

        player.draw(game.getBatch());  // Draw player on top of the map
        game.getBatch().end();

        // Finally, render the HUD
        game.getBatch().setProjectionMatrix(hud.getStage().getCamera().combined);
        hud.getStage().draw();  // Draw the HUD


    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        player.dispose();
    }
}
