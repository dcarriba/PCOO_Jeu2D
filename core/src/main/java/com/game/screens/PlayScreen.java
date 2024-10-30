package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.main.Mygame;
import com.game.scenes.Hud;

public class PlayScreen implements Screen {
    private final Mygame game;
    private final OrthographicCamera gamecam;
    private final Viewport viewport;
    private final Hud hud;
    private final OrthogonalTiledMapRenderer renderer;

    public PlayScreen(Mygame game){
        this.game = game;
        this.gamecam = new OrthographicCamera();
        int viewportWidth = 160;
        int viewportHeight = 320;
        this.viewport = new ExtendViewport(viewportWidth, viewportHeight, this.gamecam);
        this.hud = new Hud(this.game);
        TmxMapLoader maploader = new TmxMapLoader();
        TiledMap map = maploader.load("worlds/world1.tmx");
        this.renderer = new OrthogonalTiledMapRenderer(map);
        this.gamecam.position.set(viewportWidth /2f, viewportHeight /2f, 0);
    }

    public void handleInput(float delta){
        int speed = 32;
        if (Gdx.input.isTouched()){ // for testing purposes only (to see the whole world map)
            gamecam.position.x +=100*delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            gamecam.position.y += speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            gamecam.position.y -= speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            gamecam.position.x += speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            gamecam.position.x -= speed * delta;
        }
    }

    public void update(float delta){
        handleInput(delta);
        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void show(){

    }

    @Override
    public void render(float delta){
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        game.getBatch().setProjectionMatrix(hud.getStage().getCamera().combined);
        hud.getStage().draw();
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

    }
}
