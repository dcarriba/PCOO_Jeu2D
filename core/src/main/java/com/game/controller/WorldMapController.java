package com.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.game.controller.inputhandler.player.*;
import com.game.model.screens.PlayScreen;

import java.util.Objects;

public class WorldMapController {
    private final PlayScreen playScreen;

    public WorldMapController(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

    public void control(){
        // Access the 'teleport' object layer
        MapLayer teleportLayer = playScreen.getWorldMap().getTiledMap().getLayers().get("teleport");  // Object layer for teleporters
        if (teleportLayer != null) {
            // Iterate over all objects in the teleport layer
            for (MapObject object : teleportLayer.getObjects()) {
                // Check if the object is a rectangle
                if (object instanceof RectangleMapObject) {
                    RectangleMapObject rectangleObject = (RectangleMapObject) object;
                    Rectangle rect = rectangleObject.getRectangle();
                    // Check if the players coordinates are inside the object's rectangle
                    if (rect.contains(playScreen.getPlayer().getPositionX() + playScreen.getWorldMap().getTileWidth()/2, playScreen.getPlayer().getPositionY() + playScreen.getWorldMap().getTileWidth()/2)) {
                        // The player is standing on a teleporter object, so we retrieve its custom properties
                        MapProperties properties = rectangleObject.getProperties();
                        System.out.println("on teleporter!!!!");
                        // Check if the "nextWorldMapName", "nextWorldMapTileX", "nextWorldMapTileY" properties exist and have a defined value
                        if (
                            properties.containsKey("nextWorldMapName") && properties.get("nextWorldMapName") != null
                            && properties.containsKey("nextWorldMapTileX") && properties.get("nextWorldMapTileX") != null
                            && properties.containsKey("nextWorldMapTileY") && properties.get("nextWorldMapTileY") != null
                            && properties.containsKey("whenKeyPressed") && properties.get("whenKeyPressed") != null
                        ){
                            System.out.println("Next world map path: " + properties.get("nextWorldMapName"));
                            System.out.println("nextWorldMapTileX: " + (int) properties.get("nextWorldMapTileX"));
                            System.out.println("nextWorldMapTileY: " + (int) properties.get("nextWorldMapTileY"));
                            System.out.println("whenKeyPressed: " + properties.get("whenKeyPressed"));
                            PlayerInputKey playerInputKey = null;
                            if (Objects.equals(properties.get("whenKeyPressed"), "PlayerInputKeyRight")){
                                playerInputKey = new PlayerInputKeyRight();
                            } else if (Objects.equals(properties.get("whenKeyPressed"), "PlayerInputKeyLeft")){
                                playerInputKey = new PlayerInputKeyLeft();
                            } else if (Objects.equals(properties.get("whenKeyPressed"), "PlayerInputKeyDown")){
                                playerInputKey = new PlayerInputKeyDown();
                            } else if (Objects.equals(properties.get("whenKeyPressed"), "PlayerInputKeyUp")){
                                playerInputKey = new PlayerInputKeyUp();
                            }
                            if (playerInputKey != null){
                                if (playScreen.getPlayer().isNotMoving() && Gdx.input.isKeyPressed(playerInputKey.getKey())){
                                    // if they exist we can update the worldMap (i.e. the player is teleported to the new map)
                                    playScreen.getWorldMap().updateWorldMap("worlds/"+properties.get("nextWorldMapName"), 16f, 16f);
                                    playScreen.getPlayer().setOnTile((int) properties.get("nextWorldMapTileX"), (int) properties.get("nextWorldMapTileY"));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
