package com.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Player extends Entity{
    private float moveSpeed = 32.0f;  // Movement speed in units per second
    private float targetX, targetY;  // Target position (center of the next tile)
    private final float tileWidth = 16f;    // Tile width
    private final float tileHeight = 16f;   // Tile height



    public Player(float positionX, float positionY, String spriteSheet, int frameWidth, int frameHeight, int characterRow, int characterCol) {
        super(positionX, positionY, spriteSheet, frameWidth, frameHeight, characterRow, characterCol);
        // Set the initial target to be the center of the current tile
        targetX = Math.round(getPositionX() / tileWidth) * tileWidth;
        targetY = Math.round(getPositionY() / tileHeight) * tileHeight;
    }

    private void handleInput(){
        if (!getIsMoving()) {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                targetY += tileHeight;  // Move up (one tile)
                setIsMoving(true);
                updateAnimation(3);  // Up animation (row + 3)
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                targetY -= tileHeight;  // Move down (one tile)
                setIsMoving(true);
                updateAnimation(0);  // Down animation (row)
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                targetX -= tileWidth;   // Move left (one tile)
                setIsMoving(true);
                updateAnimation(1);  // Left animation (row + 1)
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                targetX += tileWidth;   // Move right (one tile)
                setIsMoving(true);
                updateAnimation(2);  // Right animation (row + 2)
            }

            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
                moveSpeed = 64f;
            } else {
                moveSpeed = 32f;
            }
        }
    }

    private void moveTowardsTarget(float deltaTime){
        // Smooth transition to target position
        if (getIsMoving()) {
            float moveStepX = targetX - getPositionX();
            float moveStepY = targetY - getPositionY();

            float distance = (float) Math.sqrt(moveStepX * moveStepX + moveStepY * moveStepY);
            float moveSpeedX = moveStepX / distance * moveSpeed;
            float moveSpeedY = moveStepY / distance * moveSpeed;

            setPositionX(getPositionX() + moveSpeedX * deltaTime);
            setPositionY(getPositionY() + moveSpeedY * deltaTime);

            // Stop moveTowardsTarget when the target position is reached
            if (Math.abs(getPositionX() - targetX) < 1f && Math.abs(getPositionY() - targetY) < 1f) {
                setPositionX(targetX);
                setPositionY(targetY);
                setIsMoving(false);

            }
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        moveTowardsTarget(deltaTime);
        super.update(deltaTime);
    }


}
