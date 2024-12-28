package com.game.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.controller.menu.MenuScreenController;
import com.game.controller.player.PlayerController;
import com.game.controller.map.WorldMapController;
import com.game.model.entities.Player;
import com.game.model.entities.SpriteSheet;
import com.game.model.map.WorldMap;
import com.game.model.memento.GameMemento;
import com.game.model.scenes.Hud;
import com.game.model.screens.MenuScreen;
import com.game.model.screens.PlayScreen;
import com.game.model.screens.Screen;
import com.game.view.scenes.HudView;
import com.game.view.screens.MenuScreenView;
import com.game.view.screens.PlayScreenView;
import com.game.model.settings.Settings;

import java.io.*;

/** The <code>Mygame</code> is the main game class. It implements {@link com.badlogic.gdx.ApplicationListener} */
public class Mygame implements ApplicationListener {
    // models
    private SpriteBatch batch;
    private final Settings settings;
    private WorldMap worldMap;
    private PlayScreen playScreen;
    private Player player;
    private Hud hud;
    private MenuScreen menuScreen;
    private Screen activeScreen;
    // views
    private PlayScreenView playScreenView;
    private HudView hudView;
    private MenuScreenView menuScreenView;
    // controllers
    private PlayerController playerController;
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
        this.activeScreen = this.menuScreen;
        MenuScreenController menuScreenController = new MenuScreenController(this.menuScreen);
        menuScreenController.createMenuScreenButtonsListeners();
    }

    public void startNewGame() {
        this.batch = new SpriteBatch();
        this.worldMap = new WorldMap("worlds/city.tmx", 16, 16);
        SpriteSheet spriteSheet = new SpriteSheet("tilesets/characterstiles/char1_1.png", 16, 16, 0, 0);
        this.player = new Player(45, 46, spriteSheet, this.worldMap);
        this.playScreen = new PlayScreen(this.batch, this.worldMap, this.player, this.settings);
        this.hud = new Hud(this.batch, this.settings);
        this.playerController = new PlayerController(this.player);
        this.worldMapController = new WorldMapController(this.playScreen);
        this.playScreenView = new PlayScreenView(this.playScreen);
        this.hudView = new HudView(this.playScreen, this.hud);
        setPlayScreen();
    }

    public void loadGame() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("game_save.ser"))) {
            GameMemento memento = (GameMemento) ois.readObject();
            this.batch = new SpriteBatch();
            this.player = memento.getPlayer();
            this.worldMap = memento.getWorldMap();
            this.playScreen = new PlayScreen(this.batch, this.worldMap, this.player, this.settings);
            this.hud = new Hud(this.batch, this.settings);
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

    public boolean isSaveAvailable() {
        File saveFile = new File("game_save.ser");
        return saveFile.exists();
    }

    private void saveGame() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("game_save.ser"))) {
            GameMemento memento = createMemento();
            oos.writeObject(memento);
        } catch (IOException e) {
            System.err.println("Error during game saving: " + e.getMessage());
        }
    }

    private GameMemento createMemento() {
        return new GameMemento(player, worldMap);
    }

    private void setPlayScreen() {
        removeMenuScreen();
        activeScreen = playScreen;
        playScreen.getViewport().update(settings.getWidth(), settings.getHeight(), false);
    }

    private void removeMenuScreen(){
        menuScreen.dispose();
        menuScreen = null;
        menuScreenView = null;
        activeScreen = null;
    }

    public void update(){
        playerController.control();
        worldMapController.control();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (activeScreen == menuScreen) menuScreenView.render();
        if (activeScreen == playScreen) {
            playScreenView.render();
            hudView.render();
            this.update();
        }
    }

    @Override
    public void resize(int width, int height) {
        if (activeScreen == menuScreen) menuScreen.getStage().getViewport().update(width, height, false);
        if (activeScreen == playScreen) playScreen.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {
        if (activeScreen == playScreen) saveGame();
    }

    @Override
    public void resume() {
        if (isSaveAvailable() && activeScreen == playScreen) loadGame();
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
