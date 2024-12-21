package com.game.model.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.model.settings.Settings;

public class Hud {
    private final Stage stage;

    public Hud(SpriteBatch batch, Settings settings){
        Integer worldTimer = 300;
//        float timeCount = 0;
        Integer score = 0;
//        Viewport viewport = new FitViewport(game.getWidth(), game.getHeight(), new OrthographicCamera());
        Viewport viewport = new ExtendViewport(settings.getWidth(), settings.getHeight(), new OrthographicCamera());
        this.stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Label countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label worldLabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label userLabel = new Label("Daniel", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(userLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();

       this.stage.addActor(table);
    }

    public Stage getStage() {
        return stage;
    }

    public void dispose(){
        stage.dispose();
    }
}
