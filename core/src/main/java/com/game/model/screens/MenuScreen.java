package com.game.model.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.main.Mygame;

/**
 * The <code>MenuScreen</code> class represents the main menu of the game.
 * It manages the UI elements like buttons and labels for starting a new game and loading a saved game.
 */
public class MenuScreen implements Screen {
    /** The main game instance */
    private final Mygame game;
    /** The viewport for the screen */
    private final Viewport viewport;
    /** The stage for managing all UI elements */
    private final Stage stage;
    /** Label to show a message when no saved game is available */
    private Label noSaveGameLabel;
    /** The button for starting a new game */
    private TextButton newGameButton;
    /** The button for loading the last saved game */
    private TextButton loadGameButton;

    /**
     * Constructor to initialize the menu screen.
     * @param game The main game instance.
     */
    public MenuScreen(Mygame game) {
        this.game = game;
        this.viewport = new FitViewport(this.game.getSettings().getWidth() / 2f, this.game.getSettings().getWidth() / 2f);
        this.stage = new Stage(this.viewport);
        createUI();
    }

    /**
     * Creates the user interface components for the menu screen.
     * This includes buttons for starting a new game and loading a saved game,
     * and a label to show a message when no saved game is available.
     */
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

    /**
     * Creates a simple button with the specified text.
     * @param text The text to be displayed on the button.
     * @return The created TextButton.
     */
    private TextButton createSimpleButton(String text) {
        // Create a simple button with a solid color as background (no skin)
        Texture texture = new Texture("screen/button_background.jpg");
        SpriteDrawable drawable = new SpriteDrawable(new Sprite(texture));

        // Create a BitmapFont and scale it
        BitmapFont font = new BitmapFont();
        font.getData().setScale(2f);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = drawable;
        style.font = font;
        style.fontColor = Color.BLACK;

        return new TextButton(text, style);
    }

    /** Shows the message indicating that no saved game is available. */
    public void showNoSaveMessage() {
        noSaveGameLabel.setVisible(true);  // Show the "No saved game" message
    }

    @Override
    public void resize(int screenWidth, int screenHeight){
        viewport.update(screenWidth, screenHeight);
    }

    public Stage getStage() {
        return stage;
    }

    public TextButton getNewGameButton() {
        return newGameButton;
    }

    public TextButton getLoadGameButton() {
        return loadGameButton;
    }

    public Mygame getGame() {
        return game;
    }

    public void dispose() {
        if (stage != null) stage.dispose();
    }

}
