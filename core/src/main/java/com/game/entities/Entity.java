package com.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.graphics.EntityAnimation;
import com.game.graphics.SpriteSheet;
import com.game.graphics.WorldMap;

/** The abstract <code>Entity</code> class represents an entity */
public abstract class Entity {
    /** Current X position */
    private float positionX;
    /** Current Y position */
    private float positionY;
    /** Position the entity is going to move to next (i.e. center of the next tile) (X axis) */
    private float targetX;
    /** Position the entity is going to move to next (i.e. center of the next tile) (Y axis) */
    private float targetY;
    /** Sprite sheet containing the sprite of the entity */
    private final SpriteSheet spriteSheet;
    /** The Tiled Map the entity will be rendered on */
    private final WorldMap worldMap;
    /** Used for the walking animation */
    private final EntityAnimation entityAnimation;
    /** Boolean representing if the entity is currently moving or not */
    private boolean isMoving;
    /** Movement speed in units per second */
    private float moveSpeed;
    /** Default movement speed */
    private final float moveSpeedDefault;

    /**
     * Constructor to create an Entity
     * @param positionX Current X position
     * @param positionY Current Y position
     * @param spriteSheet Sprite sheet containing the sprite of the entity
     */
    public Entity(float positionX, float positionY, SpriteSheet spriteSheet, WorldMap worldMap) {
        this.worldMap = worldMap;
        this.positionX = positionX * this.worldMap.getTileWidth();
        this.positionY = positionY * this.worldMap.getTileHeight();
        // Set the initial target to be the center of the current tile
        this.targetX = Math.round(getPositionX() / this.worldMap.getTileWidth()) * this.worldMap.getTileWidth();
        this.targetY = Math.round(getPositionY() / this.worldMap.getTileHeight()) * this.worldMap.getTileHeight();
        this.spriteSheet = spriteSheet;
        this.entityAnimation = new EntityAnimation(this.spriteSheet);
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

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }

    public WorldMap getWorldMap(){
        return worldMap;
    }

    public EntityAnimation getEntityAnimation(){
        return entityAnimation;
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

    public void setIsMoving(boolean bool) {
        isMoving = bool;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    /** Smooth transition to the next tile */
    private void moveToNextTile(float deltaTime) {
        if (isMoving) {
            float moveStepX = targetX - positionX;
            float moveStepY = targetY - positionY;

            float distance = (float) Math.sqrt(moveStepX * moveStepX + moveStepY * moveStepY);
            float moveSpeedX = (moveStepX / distance) * moveSpeed;
            float moveSpeedY = (moveStepY / distance) * moveSpeed;

            positionX += moveSpeedX * deltaTime;
            positionY += moveSpeedY * deltaTime;

            // Stop moveToNextTile when the target position is reached
            if (Math.abs(positionX - targetX) < 1f && Math.abs(positionY - targetY) < 1f) {
                positionX = targetX;
                positionY = targetY;
                isMoving = false;
            }
        }
    }

    public void update(float deltaTime) {
        moveToNextTile(deltaTime);
        entityAnimation.nextWalkAnimationState(deltaTime);
    }

    public void draw(SpriteBatch batch) {
        if (isMoving) {
            entityAnimation.updateCurrentFrame();
        } else {
            entityAnimation.setStandingFrame();
        }
        batch.draw(entityAnimation.getCurrentFrame(), getPositionX(), getPositionY(), getWorldMap().getTileWidth(), getWorldMap().getTileHeight());
    }

    public void dispose() {
        spriteSheet.dispose();
    }
}
