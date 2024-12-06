package com.game.entities;

import com.game.graphics.SpriteSheet;
import com.game.graphics.WorldMap;
import com.game.input.player.PlayerInputHandler;

/** The <code>Player</code> class represents a player and extends the {@link Entity} class */
public class Player extends Entity {

    /**
     * Constructor to create a Player
     * @param positionX   Current X position
     * @param positionY   Current Y position
     * @param spriteSheet Sprite sheet containing the sprite of the player
     * @param worldMap    The Tiled Map the entity will be rendered on
     */
    public Player(float positionX, float positionY, SpriteSheet spriteSheet, WorldMap worldMap) {
        super(positionX, positionY, spriteSheet, worldMap);
    }

    /** Handles the user's keyboard input */
    private void handleInput() {
        if (!getIsMoving()) {
            new PlayerInputHandler().handleInput(this);
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        super.update(deltaTime);
    }
}
