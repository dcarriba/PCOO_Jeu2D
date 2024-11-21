package com.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * The <code>Player</code> class represents a player and extends the {@link Entity} class
 */
public class Player extends Entity{

    /**
     * Constructor to create a Player
     * @param positionX Current X position
     * @param positionY Current Y position
     * @param spriteSheet Sprite sheet containing the sprite of the player
     * @param frameWidth Width of the frame containing one sprite in the sheet
     * @param frameHeight Heigth of the frame containing one sprite in the sheet
     * @param characterRow Row of the sheet containing the first sprite of the player
     * @param characterCol Column of the sheet containing the first sprite of the player
     */
    public Player(float positionX, float positionY, String spriteSheet,
                  int frameWidth, int frameHeight, int characterRow, int characterCol) {
        super(positionX, positionY, spriteSheet, frameWidth, frameHeight, characterRow, characterCol);
    }

    /**
     * Handles the user's keyboard input
     */
    private void handleInput(){
        if (!getIsMoving()) {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                setTargetY(getTargetY() + getTileHeight()); // Move up (one tile)
                setIsMoving(true);
                setWalkAnimation("up");
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                setTargetY(getTargetY() - getTileHeight()); // Move down (one tile)
                setIsMoving(true);
                setWalkAnimation("down");
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                setTargetX(getTargetX() - getTileWidth()); // Move left (one tile)
                setIsMoving(true);
                setWalkAnimation("left");
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                setTargetX(getTargetX() + getTileWidth()); // Move right (one tile)
                setIsMoving(true);
                setWalkAnimation("right");
            }

            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
                setMoveSpeed(getMoveSpeedDefault()*2);
            } else {
                setMoveSpeed(getMoveSpeedDefault());
            }
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        super.update(deltaTime);
    }

}
