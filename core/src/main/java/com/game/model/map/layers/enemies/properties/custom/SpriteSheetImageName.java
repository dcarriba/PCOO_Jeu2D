package com.game.model.map.layers.enemies.properties.custom;

/**
 * The <code>SpriteSheetImageName</code> class contains the name of a custom property of an enemies object
 * from the TiledMap's enemies object layer.
 */
public class SpriteSheetImageName {
    /** The name of the custom property. */
    private final String name;

    /** Constructor that initializes the name the custom property */
    public SpriteSheetImageName() {
        name = "spriteSheetImageName";
    }

    public String getName() {
        return name;
    }
}
