package com.game.model.map.layers.teleport.properties.custom;

/**
 * The <code>NextWorldMapNameProperty</code> class contains the name of a custom property of a teleport object
 * from the TiledMap's teleport object layer.
 */
public class NextWorldMapNameProperty {
    /** The name of the custom property. */
    private final String name;

    /** Constructor that initializes the name of the custom property. */
    public NextWorldMapNameProperty() {
        name = "nextWorldMapName";
    }

    public String getName() {
        return name;
    }
}
