package com.game.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.screens.PlayScreen;

/** {@link com.badlogic.gdx.Game} implementation shared by all platforms. */
public class Mygame extends Game {
    private final int V_WIDTH = 1280 ;
    private final int V_HEIGHT = 720;
    private SpriteBatch batch; //public?
//    private Texture image;


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
//        image = new Texture("libgdx.png");
        this.setScreen(new PlayScreen(this));
    }


    @Override
    public void render() {
//        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
//        batch.begin();
//        batch.draw(image, 140, 210);
//        batch.end();
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
//        image.dispose();
    }
}
