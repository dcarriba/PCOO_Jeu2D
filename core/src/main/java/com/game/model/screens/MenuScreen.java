package com.game.model.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.game.main.Mygame;

public class MenuScreen implements Screen {
    private final Mygame game;
    private final Stage stage;
    private Label noSaveGameLabel;
    private TextButton newGameButton;
    private TextButton loadGameButton;

    public MenuScreen(Mygame game) {
        this.game = game;
        this.stage = new Stage(new FitViewport((float) this.game.getSettings().getWidth() / 2, (float) this.game.getSettings().getWidth() / 2));
        createUI();
    }

    private void createUI() {
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Create buttons
        newGameButton = createSimpleButton("New Game");
        loadGameButton = createSimpleButton("Load Game");

        // Set up the table layout
        table.add(newGameButton).fillX().uniform().padBottom(20);
        table.row().pad(10, 10, 10, 10);
        table.add(loadGameButton).fillX().uniform();

        // Label for showing "No saved game" message
        noSaveGameLabel = new Label("No saved game found!", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        noSaveGameLabel.setVisible(false); // Hide by default
        table.row().pad(10, 10, 10, 10);
        table.add(noSaveGameLabel).center().colspan(2).padBottom(20);
    }

    private TextButton createSimpleButton(String text) {
        // Create a simple button with a solid color as background (no skin)
        Texture texture = new Texture("screen/button_background.jpg");
        SpriteDrawable drawable = new SpriteDrawable(new com.badlogic.gdx.graphics.g2d.Sprite(texture));

        // Create a BitmapFont and scale it
        BitmapFont font = new BitmapFont();
        font.getData().setScale(2f);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = drawable;
        style.font = font;
        style.fontColor = Color.BLACK;

        return new TextButton(text, style);
    }

    /** Method to show message when there is no save game available */
    public void showNoSaveMessage() {
        noSaveGameLabel.setVisible(true);  // Show the "No saved game" message
    }

    public Stage getStage() {
        return this.stage;
    }

    public TextButton getNewGameButton() {
        return newGameButton;
    }

    public TextButton getLoadGameButton() {
        return loadGameButton;
    }

    public void dispose() {
        if (stage != null) stage.dispose();
    }

    public Mygame getGame() {
        return game;
    }
}
