package com.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Entity {
    private float positionX;
    private float positionY;
    private final Texture spriteSheet;
    private final int frameWidth;
    private final int frameHeight;
    private final int characterRow;
    private final int characterCol;
    private TextureRegion[] walkFrames;
    private Animation<TextureRegion> walkAnimation;
    private float walkAnimationState;
    private boolean isMoving = false;

    public Entity(float positionX, float positionY, String spriteSheet, int frameWidth, int frameHeight, int characterRow, int characterCol) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.spriteSheet = new Texture(spriteSheet);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.characterRow = characterRow;
        this.characterCol = characterCol;
        setAnimation(0);
        this.walkAnimationState = 0;

    }

    private void setAnimation(int row){
        TextureRegion[][] temp = TextureRegion.split(this.spriteSheet, this.frameWidth, this.frameHeight);
        this.walkFrames = new TextureRegion[4];
        System.arraycopy(temp[this.characterRow + row], this.characterCol, walkFrames, 0, 3);
        walkFrames[3] = walkFrames[1];
        walkAnimation = new Animation<>(0.25f, walkFrames);
    }
    // Update walk frames based on the direction
    public void updateAnimation(int row) {
        setAnimation(row);
    }

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public boolean getIsMoving() {
        return isMoving;
    }

    public void setIsMoving(boolean bool){
        isMoving = bool;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public void update(float deltaTime){
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
