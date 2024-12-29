package com.game.model.map.layers.teleport.properties.custom;

/**
 * The <code>NextWorldMapTileXProperty</code> class contains the name of a custom property of a teleport object
 * from the TiledMap's teleport object layer.
 */
public class NextWorldMapTileXProperty {
    /** The name of the custom property. */
    private final String name;

    /** Constructor that initializes the name of the custom property. */
    public NextWorldMapTileXProperty() {
        name = "nextWorldMapTileX";
    }

    public String getName() {
        return name;
    }
}
