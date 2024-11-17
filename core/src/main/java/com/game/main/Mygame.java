package com.game.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.screens.PlayScreen;

/** {@link com.badlogic.gdx.Game} implementation shared by all platforms. */
public class Mygame extends Game {
    private final Settings settings;
    private SpriteBatch batch;

    public Mygame(){
        super();
        this.settings = new Settings();
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }

    public Settings getSettings() {
        return settings;
    }

    @Override
    public void create() {
        this.setBatch(new SpriteBatch());
        this.setScreen(new PlayScreen(this));
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
