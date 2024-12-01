package com.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
//import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.entities.Player;
import com.game.graphics.SpriteSheet;
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
        int viewportWidth = game.getSettings().getWidth()/4;
        int viewportHeight = game.getSettings().getHeight()/4;
        this.viewport = new FitViewport(viewportWidth, viewportHeight, this.gamecam);
//        this.viewport = new ExtendViewport(viewportWidth, viewportHeight, this.gamecam);
        this.hud = new Hud(this.game);
        TmxMapLoader maploader = new TmxMapLoader();
        TiledMap map = maploader.load("worlds/world1.tmx");
        this.renderer = new OrthogonalTiledMapRenderer(map);
        this.gamecam.position.set(viewportWidth /2f, viewportHeight /2f, 0);
        SpriteSheet spriteSheet = new SpriteSheet("tilesets/characterstiles/char1_1.png", 16, 16, 0, 0);
        this.player = new Player(1, 2, spriteSheet);
    }

    public void update(float delta){
        player.update(delta);

        // Update the camera's view to follow the player
        gamecam.position.x = player.getPositionX() + 8;
        gamecam.position.y = player.getPositionY() + 8;

        gamecam.update(); // Make sure camera is updated after position change
        player.update(delta); // Update player state (the animation, etc.)
    }

    @Override
    public void show(){

    }

    @Override
    public void render(float delta){
        update(delta); // updates the player and the camera

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
