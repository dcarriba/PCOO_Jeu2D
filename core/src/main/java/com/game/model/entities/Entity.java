package com.game.model.entities;

import com.badlogic.gdx.Gdx;
import com.game.model.map.WorldMap;

import java.io.Serializable;

/** The abstract <code>Entity</code> class represents an entity */
public abstract class Entity implements Serializable {
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
    /** The World Map the entity will be rendered on */
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
     * @param tileX X coordinate of the tile on the TiledMap where the entity will be placed on
     * @param tileY Y coordinate of the tile on the TiledMap where the entity will be placed on
     * @param spriteSheet Sprite sheet containing the sprite of the entity
     * @param worldMap World Map where the entity is on
     */
    public Entity(int tileX, int tileY, SpriteSheet spriteSheet, WorldMap worldMap) {
        this.worldMap = worldMap;
        this.positionX = (float) tileX * this.worldMap.getTileWidth();
        this.positionY = (float) (this.worldMap.getTiledMap().getProperties().get("height", Integer.class)-1-tileY) * this.worldMap.getTileHeight();
        // Set the initial target to be the center of the current tile
        this.targetX = Math.round(this.positionX / this.worldMap.getTileWidth()) * this.worldMap.getTileWidth();
        this.targetY = Math.round(this.positionY / this.worldMap.getTileHeight()) * this.worldMap.getTileHeight();
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

    public boolean isNotMoving() {
        return !isMoving;
    }

    public boolean isMoving(){
        return isMoving;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public float getMoveSpeedDefault() {
        return moveSpeedDefault;
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

    /** @return the X coordinate of the tile on the TiledMap where the entity is placed on */
    public int getTileX() {
        return (int) (positionX/worldMap.getTileWidth());
    }

    /** @return the Y coordinate of the tile on the TiledMap where the entity is placed on */
    public int getTileY() {
        return (int) ((-1)*(positionY/worldMap.getTileHeight() - this.worldMap.getTiledMap().getProperties().get("height", Integer.class) + 1));
    }

    /**
     * Sets the entity on the tile with the X coordinate
     * @param tileX X coordinate of the tile on the TiledMap where the entity will be placed on
     */
    public void setTileX(int tileX){
        this.positionX = (float) tileX * this.worldMap.getTileWidth();
        this.targetX = Math.round(this.positionX / this.worldMap.getTileWidth()) * this.worldMap.getTileWidth();
    }

    /**
     * Sets the entity on the tile with the Y coordinate
     * @param tileY X coordinate of the tile on the TiledMap where the entity will be placed on
     */
    public void setTileY(int tileY){
        this.positionY = (float) (this.worldMap.getTiledMap().getProperties().get("height", Integer.class)-1-tileY) * this.worldMap.getTileHeight();
        this.targetY = Math.round(this.positionY / this.worldMap.getTileHeight()) * this.worldMap.getTileHeight();
    }

    /**
     * Sets the entity on the tile with the X and Y coordinates
     * @param tileX X coordinate of the tile on the TiledMap where the entity will be placed on
     * @param tileY X coordinate of the tile on the TiledMap where the entity will be placed on
     */
    public void setOnTile(int tileX, int tileY){
        setTileX(tileX);
        setTileY(tileY);
    }

    /** Smooth transition to the next tile */
    private void moveToNextTile() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        if (isMoving) {
            float moveStepX = targetX - positionX;
            float moveStepY = targetY - positionY;

            float distance = (float) Math.sqrt(moveStepX * moveStepX + moveStepY * moveStepY);
            float moveSpeedX = (moveStepX / distance) * moveSpeed;
            float moveSpeedY = (moveStepY / distance) * moveSpeed;

            positionX += moveSpeedX * deltaTime;
            positionY += moveSpeedY * deltaTime;

            // Stop when the player is close enough to the target position
            if (distance < 1f) {
                positionX = targetX;
                positionY = targetY;
                isMoving = false;
            }
        }
    }

    /** Updates the entity's movement and animation */
    public void update() {
        if (isMoving) {
            entityAnimation.nextWalkAnimationState();
            entityAnimation.updateCurrentFrame();
            moveToNextTile();
        } else {
            entityAnimation.setStandingFrame();
        }
    }

    public void dispose() {
        if (spriteSheet != null) spriteSheet.dispose();
    }
}
