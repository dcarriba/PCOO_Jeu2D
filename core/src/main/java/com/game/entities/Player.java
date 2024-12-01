package com.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.game.directions.*;
import com.game.graphics.SpriteSheet;

import java.util.HashMap;
import java.util.Map;

/** The <code>Player</code> class represents a player and extends the {@link Entity} class */
public class Player extends Entity {

    /**
     * Constructor to create a Player
     * @param positionX Current X position
     * @param positionY Current Y position
     * @param spriteSheet Sprite sheet containing the sprite of the player
     */
    public Player(float positionX, float positionY, SpriteSheet spriteSheet) {
        super(positionX, positionY, spriteSheet);
    }

    /** Handles the user's keyboard input */
    private void handleInput() {
        if (!getIsMoving()) {
            Map<Integer, Runnable> keyActions = createKeyActions();

            for (Map.Entry<Integer, Runnable> entry : keyActions.entrySet()) {
                if (Gdx.input.isKeyPressed(entry.getKey())) {
                    entry.getValue().run();
                    break;
                }
            }

            // Handle movement speed
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                setMoveSpeed(getMoveSpeedDefault() * 2);
            } else {
                setMoveSpeed(getMoveSpeedDefault());
            }
        }
    }

    /** Creates a Map containing all Key inputs and their associated action */
    private Map<Integer, Runnable> createKeyActions() {
        Map<Integer, Runnable> keyActions = new HashMap<>();
        keyActions.put(Input.Keys.W, () -> {
            setTargetY(getTargetY() + getTileHeight());
            setIsMoving(true);
            setWalkAnimation(new UpDirection());
        });

        keyActions.put(Input.Keys.S, () -> {
            setTargetY(getTargetY() - getTileHeight());
            setIsMoving(true);
            setWalkAnimation(new DownDirection());
        });

        keyActions.put(Input.Keys.A, () -> {
            setTargetX(getTargetX() - getTileWidth());
            setIsMoving(true);
            setWalkAnimation(new LeftDirection());
        });

        keyActions.put(Input.Keys.D, () -> {
            setTargetX(getTargetX() + getTileWidth());
            setIsMoving(true);
            setWalkAnimation(new RightDirection());
        });
        return keyActions;
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        super.update(deltaTime);
    }
}
