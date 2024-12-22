package com.game.controller;

import com.badlogic.gdx.Gdx;
import com.game.controller.inputhandler.player.PlayerInputKeyLeft;
import com.game.controller.inputhandler.player.PlayerInputKeyRight;
import com.game.model.screens.PlayScreen;

import java.util.Objects;

public class WorldMapController {
    private final PlayScreen playScreen;

    public WorldMapController(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

    public void control(){
        if (
            Objects.equals(playScreen.getWorldMap().getWorldMapPath(), "worlds/city.tmx")
            && playScreen.getPlayer().getTileX() == 78
            && playScreen.getPlayer().getTileY() == 46
            && playScreen.getPlayer().isNotMoving()
            && Gdx.input.isKeyPressed(new PlayerInputKeyRight().getKey())
        ){
            playScreen.getWorldMap().updateWorldMap("worlds/passage_horizontal.tmx", 16f, 16f);
            playScreen.getPlayer().setOnTile(10, 14);
        }
        else if (
            Objects.equals(playScreen.getWorldMap().getWorldMapPath(), "worlds/passage_horizontal.tmx")
            && playScreen.getPlayer().getTileX() == 10
            && (playScreen.getPlayer().getTileY() == 14 || playScreen.getPlayer().getTileY() == 15)
            && playScreen.getPlayer().isNotMoving()
            && Gdx.input.isKeyPressed(new PlayerInputKeyLeft().getKey())
        ){
            playScreen.getWorldMap().updateWorldMap("worlds/city.tmx", 16f, 16f);
            playScreen.getPlayer().setOnTile(78, 46);
        }
        else if (
            Objects.equals(playScreen.getWorldMap().getWorldMapPath(), "worlds/passage_horizontal.tmx")
            && playScreen.getPlayer().getTileX() == 19
            && (playScreen.getPlayer().getTileY() == 14 || playScreen.getPlayer().getTileY() == 15)
            && playScreen.getPlayer().isNotMoving()
            && Gdx.input.isKeyPressed(new PlayerInputKeyRight().getKey())
        ){
            playScreen.getWorldMap().updateWorldMap("worlds/ocean.tmx", 16f, 16f);
            playScreen.getPlayer().setOnTile(13, 40);
        }
        else if (
            Objects.equals(playScreen.getWorldMap().getWorldMapPath(), "worlds/ocean.tmx")
            && playScreen.getPlayer().getTileX() == 13
            && playScreen.getPlayer().getTileY() == 40
            && playScreen.getPlayer().isNotMoving()
            && Gdx.input.isKeyPressed(new PlayerInputKeyLeft().getKey())
        ){
            playScreen.getWorldMap().updateWorldMap("worlds/passage_horizontal.tmx", 16f, 16f);
            playScreen.getPlayer().setOnTile(19, 14);
        }
    }
}
