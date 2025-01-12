package com.game.model.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.model.screens.PlayScreen;

/**
 * The <code>Hud</code> class represents the HUD.
 * It displays information like the number of enemies killed.
 */
public class Hud {
    /** The Stage to manage the HUD UI elements */
    private Stage stage;
    /** The PlayScreen to access the game data */
    private final PlayScreen playScreen;
    /** The label that displays the number of enemies killed */
    private Label nbEnemiesKilledLabel;

    /**
     * Constructor to initialize the HUD.
     * @param playScreen The current PlayScreen of the game.
     */
    public Hud(PlayScreen playScreen){
        this.playScreen = playScreen;
        createStage();
    }

    private void createStage(){
        Viewport viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        this.stage = new Stage(viewport, this.playScreen.getBatch());

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        BitmapFont bitmapFont = new BitmapFont();
        bitmapFont.getData().setScale(1.5f);
        int nbEnemiesKilled = this.playScreen.getWorldMap().getKilledEnemies().size();
        // Create label for the current number of killed enemies.
        this.nbEnemiesKilledLabel = new Label("Enemies Killed :" + nbEnemiesKilled, new Label.LabelStyle(bitmapFont, Color.WHITE));

        // Add the label to the table
        table.add(this.nbEnemiesKilledLabel).padTop(10);

        this.stage.addActor(table); // adds the table to the stage
    }

    /** Updates the HUD by refreshing the number of killed enemies. */
    public void update(){
        int nbEnemiesKilled = playScreen.getWorldMap().getKilledEnemies().size();
        nbEnemiesKilledLabel.setText("Enemies Killed : " + nbEnemiesKilled);
    }

    /** Called when resizing the screen, recreates the stage managing the UI elements */
    public void resize(){
        dispose();
        createStage();
    }

    public Stage getStage() {
        return stage;
    }

    public void dispose(){
        stage.dispose();
    }
}
