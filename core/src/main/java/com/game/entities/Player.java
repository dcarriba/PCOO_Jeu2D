package com.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Player extends Entity{

    public Player(float positionX, float positionY, String spriteSheet,
                  int frameWidth, int frameHeight, int characterRow, int characterCol) {
        super(positionX, positionY, spriteSheet, frameWidth, frameHeight, characterRow, characterCol);
    }

    private void handleInput(){
        if (!getIsMoving()) {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                setTargetY(getTargetY() + getTileHeight()); // Move up (one tile)
                setIsMoving(true);
                updateAnimation(3);  // Up animation (row + 3)
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                setTargetY(getTargetY() - getTileHeight()); // Move down (one tile)
                setIsMoving(true);
                updateAnimation(0);  // Down animation (row)
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                setTargetX(getTargetX() - getTileWidth()); // Move left (one tile)
                setIsMoving(true);
                updateAnimation(1);  // Left animation (row + 1)
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                setTargetX(getTargetX() + getTileWidth()); // Move right (one tile)
                setIsMoving(true);
                updateAnimation(2);  // Right animation (row + 2)
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
