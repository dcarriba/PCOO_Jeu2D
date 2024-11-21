package com.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The abstract <code>Entity</code> class represents an entity
 */
public abstract class Entity {
    /**
     * Current X position
     */
    private float positionX;
    /**
     * Current Y position
     */
    private float positionY;
    /**
     * Position the entity is going to move to next (i.e. center of the next tile) (X axis)
     */
    private float targetX;
    /**
     * Position the entity is going to move to next (i.e. center of the next tile) (Y axis)
     */
    private float targetY;
    /**
     * Sprite sheet containing the sprite of the entity
     */
    private final Texture spriteSheet;
    /**
     * Width of the frame containing one sprite in the sheet
     */
    private final int frameWidth;
    /**
     * Heigt of the frame containing one sprite in the sheet
     */
    private final int frameHeight;
    /**
     * Row of the sheet containing the first sprite of the entity
     */
    private final int characterRow;
    /**
     * Column of the sheet containing the first sprite of the entity
     */
    private final int characterCol;
    /**
     * Contains all sprites needed for the walking animation
     */
    private Animation<TextureRegion> walkAnimation;
    /**
     * Number representing the current used sprite in the walking animation
     */
    private float walkAnimationState;
    /**
     * Boolean representing if the entity is currrently moving or not
     */
    private boolean isMoving;
    /**
     * Movement speed in units per second
     */
    private float moveSpeed;
    /**
     * Default movement speed
     */
    private final float moveSpeedDefault;
    /**
     * Width of one tile in the map
     */
    private final float tileWidth;
    /**
     * Height of one tile in the map
     */
    private final float tileHeight;


    /**
     * Constructor to create an Entity
     * @param positionX Current X position
     * @param positionY Current Y position
     * @param spriteSheet Sprite sheet containing the sprite of the entity
     * @param frameWidth Width of the frame containing one sprite in the sheet
     * @param frameHeight Heigth of the frame containing one sprite in the sheet
     * @param characterRow Row of the sheet containing the first sprite of the entity
     * @param characterCol Column of the sheet containing the first sprite of the entity
     */
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
        setWalkAnimation("down");
        this.walkAnimationState = 0;
        this.isMoving = false;
        this.moveSpeedDefault = 32f;
        this.moveSpeed = this.moveSpeedDefault;
    }

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

    /**
     * Sets the walking animation
     * @param direction The direction the entity is facing.
     *                  Must be "down", "left", "right" or "up"
     */
    public void setWalkAnimation(String direction){
        int row = 0;
        switch (direction) {
            case "down":
                // row stays at 0
                break;
            case "left":
                row = 1;
                break;
            case "right":
                row = 2;
                break;
            case "up":
                row = 3;
                break;
            default:
                // exception
                break;
        }
        TextureRegion[][] temp = TextureRegion.split(this.spriteSheet, this.frameWidth, this.frameHeight);
        TextureRegion[] walkFrames = new TextureRegion[4];
        System.arraycopy(temp[this.characterRow + row], this.characterCol, walkFrames, 0, 3);
        walkFrames[3] = walkFrames[1];
        walkAnimation = new Animation<>(0.25f, walkFrames);
    }

    /**
     * Smooth transition to the next tile
     */
    private void moveToNextTile(float deltaTime){
        if (isMoving) {
            float moveStepX = targetX - getPositionX();
            float moveStepY = targetY - getPositionY();

            float distance = (float) Math.sqrt(moveStepX * moveStepX + moveStepY * moveStepY);
            float moveSpeedX = (moveStepX / distance) * moveSpeed;
            float moveSpeedY = (moveStepY / distance) * moveSpeed;

            setPositionX(getPositionX() + moveSpeedX * deltaTime);
            setPositionY(getPositionY() + moveSpeedY * deltaTime);

            // Stop moveToNextTile when the target position is reached
            if (Math.abs(getPositionX() - targetX) < 1f && Math.abs(getPositionY() - targetY) < 1f) {
                setPositionX(targetX);
                setPositionY(targetY);
                setIsMoving(false);

            }
        }
    }

    public void update(float deltaTime){
        moveToNextTile(deltaTime);
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
