package com.game.directions;

/**
 * Represents the "down" direction in the game.
 * <p>
 * This class implements the {@link Direction} interface and provides the specific
 * sprite sheet row index for the "down" direction. This index is used to correctly
 * animate the character when moving down.
 * </p>
 */
public class DownDirection implements Direction {

    /**
     * Retrieves the sprite sheet row corresponding to the "down" direction.
     * <p>
     * In this case, the row index for the "down" direction is 0, which corresponds
     * to the frames for moving down in the sprite sheet.
     * </p>
     *
     * @return the row index (0) for the "down" direction in the sprite sheet
     */
    @Override
    public int getSpriteSheetRow() {
        return 0;
    }
}
