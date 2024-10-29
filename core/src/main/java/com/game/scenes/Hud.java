package com.game.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.main.Mygame;

public class Hud {
    private final Stage stage;

    public Hud(Mygame game){
        Integer worldTimer = 300;
//        float timeCount = 0;
        Integer score = 0;
        Viewport viewport = new FitViewport(game.getV_WIDTH(), game.getV_HEIGHT(), new OrthographicCamera());
        this.stage = new Stage(viewport, game.getBatch());

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
}