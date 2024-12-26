package com.game.model.entities;

import com.game.model.map.WorldMap;

public class Enemy extends Entity {

    /**
     * Constructor to create an Enemy
     *
     * @param tileX X coordinate of the tile on the TiledMap where the enemy will be placed on
     * @param tileY Y coordinate of the tile on the TiledMap where the enemy will be placed on
     * @param spriteSheet Sprite sheet containing the sprite of the enemy
     * @param worldMap World Map where the enemy is on
     */
    public Enemy(int tileX, int tileY, SpriteSheet spriteSheet, WorldMap worldMap) {
        super(tileX, tileY, spriteSheet, worldMap);
    }

}
