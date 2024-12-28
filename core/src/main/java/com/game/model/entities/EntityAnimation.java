package com.game.model.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.model.directions.Direction;
import com.game.model.directions.DownDirection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/** The <code>EntityAnimation</code> class represents the sprites of an entity used for the walking animation */
public class EntityAnimation implements Serializable {
    /** Contains all sprites needed for the walking animation */
    private transient Animation<TextureRegion> walkAnimation;
    /** Number representing the current state of the walking animation (i.e. the frame that should be used) */
    private transient float walkAnimationState;
    /** The current used sprite in the walking animation */
    private transient TextureRegion currentFrame;
    /** The direction the entity is facing */
    private Direction currentFacingDirection;
    /** Sprite sheet containing the sprite of the entity */
    private final SpriteSheet spriteSheet;

    /**
     * Constructor to create an EntityAnimation
     * @param spriteSheet Sprite sheet containing the sprite of the entity
     */
    public EntityAnimation(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
        setWalkAnimation(new DownDirection(), this.spriteSheet);
        setStandingFrame();
    }

    /**
     * Sets the walking animation
     * @param direction The direction the entity is facing.
     * @param spriteSheet the spritesheet containing the sprites of the entity.
     */
    public void setWalkAnimation(Direction direction, SpriteSheet spriteSheet) {
        int row = direction.getSpriteSheetRow();
        TextureRegion[][] temp = TextureRegion.split(spriteSheet.getTexture(), spriteSheet.getFrameWidth(), spriteSheet.getFrameHeight());
        TextureRegion[] walkFrames = new TextureRegion[4];
        System.arraycopy(temp[spriteSheet.getCharacterRow() + row], spriteSheet.getCharacterColumn(), walkFrames, 0, 3);
        walkFrames[3] = walkFrames[1];
        walkAnimation = new Animation<>(0.25f, walkFrames);
        currentFacingDirection = direction;
    }

    /** Sets the entity to be standing still */
    public void setStandingFrame(){
        walkAnimationState = 1;
        currentFrame = walkAnimation.getKeyFrame(walkAnimationState, false);
    }

    /**
     * Gets the current frame of the walking animation
     * @return the current frame of the walking animation
     */
    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    /** Updates the current frame */
    public void updateCurrentFrame(){
        currentFrame = walkAnimation.getKeyFrame(walkAnimationState, true);
    }

    /** Increments the walkAnimationState attribute for the next animation frame */
    public void nextWalkAnimationState(){
        walkAnimationState += Gdx.graphics.getDeltaTime();
    }

    public Direction getCurrentFacingDirection() {
        return currentFacingDirection;
    }

    /**
     * Custom deserialization to initialize transient fields after the object is deserialized
     * @param ois The ObjectInputStream
     */
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();  // Deserialize the non-transient fields
        setWalkAnimation(currentFacingDirection, spriteSheet); // Default direction is DownDirection
        setStandingFrame();
    }
}
