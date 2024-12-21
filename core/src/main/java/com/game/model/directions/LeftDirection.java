package com.game.model.directions;

/**
 * Represents the "left" direction in the game.
 * <p>
 * This class implements the {@link Direction} interface and provides the specific
 * sprite sheet row index for the "left" direction. This index is used to correctly
 * animate the character when moving left.
 * </p>
 */
public class LeftDirection implements Direction {

    /**
     * Retrieves the sprite sheet row corresponding to the "left" direction.
     * <p>
     * In this case, the row index for the "left" direction is 1, which corresponds
     * to the frames for moving left in the sprite sheet.
     * </p>
     *
     * @return the row index (1) for the "left" direction in the sprite sheet
     */
    @Override
    public int getSpriteSheetRow() {
        return 1;
    }
}
