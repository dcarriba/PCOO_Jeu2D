package com.game.controller.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.game.model.inputhandler.player.PlayerInputKey;
import com.game.model.map.layers.teleport.properties.custom.NextWorldMapNameProperty;
import com.game.model.map.layers.teleport.properties.custom.NextWorldMapTileXProperty;
import com.game.model.map.layers.teleport.properties.custom.NextWorldMapTileYProperty;
import com.game.model.map.layers.teleport.properties.custom.WhenKeyPressedProperty;
import com.game.model.map.layers.teleport.properties.validator.ValidateCustomPropertiesTeleport;
import com.game.model.screens.PlayScreen;

/**
 * The <code>WorldMapController</code> class is responsible for handling the logic related to teleportation
 * and updating the worldMap.
 */
public class WorldMapController {
    /** The play screen associated with this world map controller */
    private final PlayScreen playScreen;

    /**
     * Constructor to initialize the WorldMapController.
     * @param playScreen The PlayScreen where the world map and player are located.
     */
    public WorldMapController(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

    /**
     * Controls the teleportation logic by checking if the player is standing on a teleport object
     * and processes the teleportation if all conditions are verified.
     */
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
                        // Check if all the required custom properties exist and have a defined value
                        if (new ValidateCustomPropertiesTeleport().validate(properties)){
                            PlayerInputKey playerInputKey = new WhenKeyPressedProperty().retrieveAssociatedPlayerInputKey(properties);
                            if (playerInputKey != null){
                                if (playScreen.getPlayer().isNotMoving() && Gdx.input.isKeyPressed(playerInputKey.getKey())){
                                    // if they exist, and the player moves to the correct direction, we update the worldMap (i.e. the player is teleported to the new map)
                                    playScreen.getWorldMap().updateWorldMap("worlds/"+properties.get(new NextWorldMapNameProperty().getName()), 16f, 16f);
                                    playScreen.getPlayer().setOnTile((int) properties.get(new NextWorldMapTileXProperty().getName()), (int) properties.get(new NextWorldMapTileYProperty().getName()));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
