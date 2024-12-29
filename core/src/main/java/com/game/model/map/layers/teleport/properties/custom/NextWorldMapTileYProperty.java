package com.game.model.map.layers.teleport.properties.custom;

/**
 * The <code>NextWorldMapTileYProperty</code> class contains the name of a custom property of a teleport object
 * from the TiledMap's teleport object layer.
 */
public class NextWorldMapTileYProperty {
    /** The name of the custom property. */
    private final String name;

    /** Constructor that initializes the name of the custom property. */
    public NextWorldMapTileYProperty() {
        name = "nextWorldMapTileY";
    }

    public String getName() {
        return name;
    }
}
