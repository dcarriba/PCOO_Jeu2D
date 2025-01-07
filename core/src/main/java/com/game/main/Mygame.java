package com.game.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.controller.menu.MenuScreenController;
import com.game.controller.player.PlayerController;
import com.game.controller.map.WorldMapController;
import com.game.model.activescreen.ActiveScreen;
import com.game.model.entities.Player;
import com.game.model.entities.SpriteSheet;
import com.game.model.map.WorldMap;
import com.game.model.memento.GameMemento;
import com.game.model.scenes.Hud;
import com.game.model.screens.MenuScreen;
import com.game.model.screens.PlayScreen;
import com.game.view.scenes.HudView;
import com.game.view.screens.MenuScreenView;
import com.game.view.screens.PlayScreenView;
import com.game.model.settings.Settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** The <code>Mygame</code> is the main game class. It implements {@link com.badlogic.gdx.ApplicationListener}. */
public class Mygame implements ApplicationListener {
    // MODELS
    /** SpriteBatch where the player and the enemies are drawn on */
    private SpriteBatch batch;
    /** Basic game settings */
    private final Settings settings;
    /** The worldMap with the current tiledMap */
    private WorldMap worldMap;
    /** The playScreen containing all entities as well as the worldMap */
    private PlayScreen playScreen;
    /** The player */
    private Player player;
    /** The hud displayed on top of the playScreen */
    private Hud hud;
    /** The main menu shown when the game is launched */
    private MenuScreen menuScreen;
    /** Stores the current active screen and its view */
    private ActiveScreen activeScreen;
    // VIEWS
    /** The view for the playScreen */
    private PlayScreenView playScreenView;
    /** The view for the hud */
    private HudView hudView;
    /** the view for the menuScreen */
    private MenuScreenView menuScreenView;
    // CONTROLLERS
    /** handles all input relative to the player */
    private PlayerController playerController;
    /** handles which tiledMap the player is currently on, and changes it when needed */
    private WorldMapController worldMapController;

    /** Constructor to create the Game */
    public Mygame(){
        super();
        this.settings = new Settings();
    }

    public Settings getSettings() {
        return this.settings;
    }

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.menuScreen = new MenuScreen(this);
        this.menuScreenView = new MenuScreenView(this.menuScreen);
        this.activeScreen = new ActiveScreen(this.menuScreen, this.menuScreenView);
        MenuScreenController menuScreenController = new MenuScreenController(this.menuScreen);
        menuScreenController.createMenuScreenButtonsListeners();
    }

    /** Starts a new game */
    public void startNewGame() {
        this.batch = new SpriteBatch();
        this.worldMap = new WorldMap("worlds/city.tmx", 16, 16);
        SpriteSheet spriteSheet = new SpriteSheet("tilesets/characterstiles/char1_1.png", 16, 16, 0, 0);
        this.player = new Player(45, 46, spriteSheet, this.worldMap);
        this.playScreen = new PlayScreen(this.batch, this.worldMap, this.player, this.settings);
        this.hud = new Hud(this.playScreen, this.settings);
        this.playerController = new PlayerController(this.player);
        this.worldMapController = new WorldMapController(this.playScreen);
        this.playScreenView = new PlayScreenView(this.playScreen);
        this.hudView = new HudView(this.playScreen, this.hud);
        setPlayScreen();
    }

    /** Loads the last game save. If there is none, it will start a new game */
    public void loadGame() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("game_save.ser"))) {
            GameMemento memento = (GameMemento) ois.readObject();
            this.batch = new SpriteBatch();
            this.player = memento.getPlayer();
            this.worldMap = memento.getWorldMap();
            this.playScreen = new PlayScreen(this.batch, this.worldMap, this.player, this.settings);
            this.hud = new Hud(this.playScreen, this.settings);
            this.playerController = new PlayerController(this.player);
            this.worldMapController = new WorldMapController(this.playScreen);
            this.playScreenView = new PlayScreenView(this.playScreen);
            this.hudView = new HudView(this.playScreen, this.hud);
            setPlayScreen();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during game loading: " + e.getMessage());
            startNewGame();  // Fallback to a new game if loading fails
        }
    }

    /** Checks if a save file of the game exists or not */
    public boolean isSaveAvailable() {
        File saveFile = new File("game_save.ser");
        return saveFile.exists();
    }

    /** Saves the current game in the game_save.ser file */
    private void saveGame() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("game_save.ser"))) {
            GameMemento memento = createMemento();
            oos.writeObject(memento);
        } catch (IOException e) {
            System.err.println("Error during game saving: " + e.getMessage());
        }
    }

    /** Creates a Memento (needed to save the game) */
    private GameMemento createMemento() {
        return new GameMemento(player, worldMap);
    }

    /** Transitions from the initial menu screen to the playScreen */
    private void setPlayScreen() {
        removeMenuScreen();
        activeScreen = new ActiveScreen(playScreen, playScreenView);
        playScreen.updateViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
    }

    /** Removes the initial menu screen by disposing it and by removing all its references  */
    private void removeMenuScreen(){
        menuScreen.dispose();
        menuScreen = null;
        menuScreenView = null;
        activeScreen = null;
    }

    /** Checks if the current active screen is the playScreen */
    private boolean isPlayScreenActive(){
        return playScreen != null && activeScreen.getScreen() == playScreen;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        activeScreen.getScreenView().render();
        if (isPlayScreenActive()) {
            // the hud is only rendered on top of the playScreen
            hudView.render();
            // the player and worldMap are only controlled when the playScreen is active
            playerController.control();
            worldMapController.control();
        }
    }

    @Override
    public void resize(int width, int height) {
        activeScreen.getScreen().updateViewport(width, height, false);
    }

    @Override
    public void pause() {
        if (isPlayScreenActive()) saveGame();
    }

    @Override
    public void resume() {
        if (isSaveAvailable() && isPlayScreenActive()) loadGame();
    }

    @Override
    public void dispose() {
        if (menuScreen != null) menuScreen.dispose();
        if (batch != null) batch.dispose();
        if (player != null) player.dispose();
        if (worldMap != null) worldMap.dispose();
        if (hud != null) hud.dispose();
    }
}
