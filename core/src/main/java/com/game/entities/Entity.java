package com.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Entity {
    private float positionX; // current X position
    private float positionY; // current Y position
    private float targetX;  // Target position (center of the next tile) (X axis)
    private float targetY; // Target position (center of the next tile) (Y axis)
    private final Texture spriteSheet; // Sprite Sheet containing the Sprite of the Entity
    private final int frameWidth; // width of the frame containing one sprite in the sheet
    private final int frameHeight; // heigt of the frame containing one sprite in the sheet
    private final int characterRow; // row of the sheet containing the first sprite of the Entity
    private final int characterCol; // column of the sheet containing the first sprite of the Entity
    private Animation<TextureRegion> walkAnimation; // contains all sprites needed for the walking animation
    private float walkAnimationState; // number representing the current used sprite in the walking animation
    private boolean isMoving; // if the Entity is currrently moving or not
    private float moveSpeed;  // Movement speed in units per second
    private final float moveSpeedDefault; // Default Movement speed
    private final float tileWidth; // Tile width of the map
    private final float tileHeight; // Tile height of the map

    public Entity(float positionX, float positionY, String spriteSheet,
                  int frameWidth, int frameHeight, int characterRow, int characterCol) {
        this.tileWidth = 16f;
        this.tileHeight = 16f;
        this.positionX = positionX * tileWidth;
        this.positionY = positionY * tileHeight;
        // Set the initial target to be the center of the current tile
        this.targetX = Math.round(getPositionX() / tileWidth) * tileWidth;
        this.targetY = Math.round(getPositionY() / tileHeight) * tileHeight;
        this.spriteSheet = new Texture(spriteSheet);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.characterRow = characterRow;
        this.characterCol = characterCol;
        setWalkAnimation(0);
        this.walkAnimationState = 0;
        this.isMoving = false;
        this.moveSpeedDefault = 32f;
        this.moveSpeed = this.moveSpeedDefault;
    }

    // Getters
    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public float getTargetX() {
        return targetX;
    }

    public float getTargetY() {
        return targetY;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public boolean getIsMoving() {
        return isMoving;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public float getMoveSpeedDefault() {
        return moveSpeedDefault;
    }

    public float getTileWidth() {
        return tileWidth;
    }

    public float getTileHeight() {
        return tileHeight;
    }

    // Setters
    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public void setTargetX(float targetX) {
        this.targetX = targetX;
    }

    public void setTargetY(float targetY) {
        this.targetY = targetY;
    }

    public void setIsMoving(boolean bool){
        isMoving = bool;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    private void setWalkAnimation(int row){
        TextureRegion[][] temp = TextureRegion.split(this.spriteSheet, this.frameWidth, this.frameHeight);
        TextureRegion[] walkFrames = new TextureRegion[4];
        System.arraycopy(temp[this.characterRow + row], this.characterCol, walkFrames, 0, 3);
        walkFrames[3] = walkFrames[1];
        walkAnimation = new Animation<>(0.25f, walkFrames);
    }

    // Update walk frames based on the direction
    public void updateAnimation(int row) {
        setWalkAnimation(row);
    }

    private void moveTowardsTarget(float deltaTime){
        // Smooth transition to target position
        if (isMoving) {
            float moveStepX = targetX - getPositionX();
            float moveStepY = targetY - getPositionY();

            float distance = (float) Math.sqrt(moveStepX * moveStepX + moveStepY * moveStepY);
            float moveSpeedX = (moveStepX / distance) * moveSpeed;
            float moveSpeedY = (moveStepY / distance) * moveSpeed;

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

    public void update(float deltaTime){
        moveTowardsTarget(deltaTime);
        walkAnimationState += deltaTime;
    }

    public void draw(SpriteBatch batch) {
        if (isMoving) {
            TextureRegion currentFrame = walkAnimation.getKeyFrame(walkAnimationState, true);
            batch.draw(currentFrame, getPositionX(), getPositionY(), 16, 16);
        } else {
            // Draw the player in a "standing" position if not moving
            walkAnimationState = 1;
            TextureRegion standingFrame = walkAnimation.getKeyFrame(walkAnimationState, false);
            batch.draw(standingFrame, getPositionX(), getPositionY(), 16, 16);
        }
    }

    public void dispose(){
        spriteSheet.dispose();
    }
}
