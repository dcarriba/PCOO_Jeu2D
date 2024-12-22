package com.game.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.controller.PlayerController;
import com.game.controller.WorldMapController;
import com.game.model.entities.Player;
import com.game.model.graphics.SpriteSheet;
import com.game.model.graphics.WorldMap;
import com.game.model.scenes.Hud;
import com.game.model.screens.PlayScreen;
import com.game.view.scenes.HudView;
import com.game.view.screens.PlayScreenView;
import com.game.model.settings.Settings;

/** The <code>Mygame</code> is the main game class. It implements {@link com.badlogic.gdx.ApplicationListener} */
public class Mygame implements ApplicationListener {
    // models
    private SpriteBatch batch;
    private final Settings settings;
    private WorldMap worldMap;
    private PlayScreen playScreen;
    private Player player;
    private Hud hud;
    // views
    private PlayScreenView playScreenView;
    private HudView hudView;
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
        this.worldMap = new WorldMap("worlds/city.tmx", 16, 16);
        SpriteSheet spriteSheet = new SpriteSheet("tilesets/characterstiles/char1_1.png", 16, 16, 0, 0);
        this.player = new Player(45, 46, spriteSheet, this.worldMap);
        this.playScreen = new PlayScreen(this.batch, this.worldMap, this.player, this.settings);
        this.hud = new Hud(this.batch, this.settings);
        this.playerController = new PlayerController(this.player);
        this.worldMapController = new WorldMapController(this.playScreen);
        this.playScreenView = new PlayScreenView(this.playScreen);
        this.hudView = new HudView(this.playScreen, this.hud);
    }

    public void update(){
        playerController.control();
        worldMapController.control();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        playScreenView.render();
        hudView.render();
        this.update();
    }

    @Override
    public void resize(int width, int height) {
        playScreen.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        this.batch.dispose();
        this.player.dispose();
        this.worldMap.dispose();
        this.hud.dispose();
    }
}
