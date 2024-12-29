package com.game.model.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.model.screens.PlayScreen;
import com.game.model.settings.Settings;

/**
 * The <code>Hud</code> class represents the HUD.
 * It displays information like the number of enemies killed.
 */
public class Hud {
    /** The Stage to manage the HUD UI elements */
    private final Stage stage;
    /** The PlayScreen to access the game data */
    private final PlayScreen playScreen;
    /** The label that displays the number of enemies killed */
    private final Label nbEnemiesKilledLabel;

    /**
     * Constructor to initialize the HUD.
     * @param playScreen The current PlayScreen of the game.
     * @param settings The game Settings containing screen size information.
     */
    public Hud(PlayScreen playScreen, Settings settings){
        this.playScreen = playScreen;
        Viewport viewport = new FitViewport(settings.getWidth(), settings.getHeight(), new OrthographicCamera());
        this.stage = new Stage(viewport, this.playScreen.getBatch());

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        // Create labels for "Enemies Killed" and the current number of killed enemies.
        Label userLabel = new Label("Enemies Killed :", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        int nbEnemiesKilled = this.playScreen.getWorldMap().getKilledEnemies().size();
        this.nbEnemiesKilledLabel = new Label(Integer.toString(nbEnemiesKilled), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        // Add the labels to the table
        table.add(userLabel).expandX().padTop(10);
        table.row();
        table.add(this.nbEnemiesKilledLabel).expandX().padTop(10);

        this.stage.addActor(table); // adds the table to the stage
    }

    /** Updates the HUD by refreshing the number of enemies killed. */
    public void update(){
        int nbEnemiesKilled = playScreen.getWorldMap().getKilledEnemies().size();
        nbEnemiesKilledLabel.setText(Integer.toString(nbEnemiesKilled));
    }

    public Stage getStage() {
        return stage;
    }

    public void dispose(){
        stage.dispose();
    }
}
