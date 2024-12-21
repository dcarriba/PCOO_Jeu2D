package com.game.model.directions;

/**
 * The <code>Direction</code> interface represents a direction in the game for managing animations and movements.
 * Each direction (such as "up", "down", "left", "right") corresponds to a specific
 * row in the sprite sheet used for character animations.
 * <p>
 * Implementing classes will define the row index of the sprite sheet that corresponds
 * to the given direction for efficient animation handling.
 * </p>
 */
public interface Direction {

    /**
     * Retrieves the sprite sheet row corresponding to this direction.
     * This row is used to select the appropriate frames for the animation.
     *
     * @return the row index in the sprite sheet that represents this direction
     */
    int getSpriteSheetRow();
}
