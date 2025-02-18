package com.game.model.entities;

import com.game.model.map.WorldMap;

/** The <code>Player</code> class represents a player and extends the {@link Entity} class */
public class Player extends Entity {

    /**
     * Constructor to create a Player
     * @param tileX X coordinate of the tile on the TiledMap where the player will be placed on
     * @param tileY Y coordinate of the tile on the TiledMap where the player will be placed on
     * @param spriteSheet Sprite sheet containing the sprite of the player
     * @param worldMap World Map where the player is on
     */
    public Player(int tileX, int tileY, SpriteSheet spriteSheet, WorldMap worldMap) {
        super(tileX, tileY, spriteSheet, worldMap);
    }
}
