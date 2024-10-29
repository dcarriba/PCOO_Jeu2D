package com.game.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.screens.PlayScreen;

/** {@link com.badlogic.gdx.Game} implementation shared by all platforms. */
public class Mygame extends Game {
    private final int V_WIDTH;
    private final int V_HEIGHT;
    private SpriteBatch batch;

    public Mygame(){
        super();
        this.V_WIDTH = 1280;
        this.V_HEIGHT = 640;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }

    public int getV_WIDTH(){
        return this.V_WIDTH;
    }

    public int getV_HEIGHT(){
        return this.V_HEIGHT;
    }

    @Override
    public void create() {
        this.setBatch(new SpriteBatch());
        this.setScreen(new PlayScreen(this));
    }


    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
