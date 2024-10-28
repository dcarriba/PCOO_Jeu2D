package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.main.Mygame;
import com.game.scenes.Hud;

public class PlayScreen implements Screen {
    private final Mygame game;
//    private final Texture texture;
    private final OrthographicCamera gamecam;
    private final Viewport gamePort;
    private final Hud hud;

    public PlayScreen(Mygame game){
        this.game = game;
//        texture = new Texture("libgdx.png");
        this.gamecam = new OrthographicCamera();
//        gamePort = new ScreenViewport(gamecam);
        this.gamePort = new FitViewport(game.getV_WIDTH(), game.getV_HEIGHT(), gamecam);
        this.hud = new Hud(game);
    }

    @Override
    public void show(){

    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        game.batch.setProjectionMatrix(gamecam.combined);
//        game.batch.begin();
//        game.batch.draw(texture, 0, 0);
//        game.batch.end();
        game.getBatch().setProjectionMatrix(hud.getStage().getCamera().combined);
        hud.getStage().draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
