package com.game.model.directions;

import com.game.model.entities.Player;

/**
 * Represents the "right" direction in the game.
 * <p>
 * This class implements the {@link Direction} interface and provides the specific
 * sprite sheet row index for the "right" direction. This index is used to correctly
 * animate the character when moving right.
 * </p>
 */
public class RightDirection implements Direction {

    /**
     * Retrieves the sprite sheet row corresponding to the "right" direction.
     * <p>
     * In this case, the row index for the "right" direction is 2, which corresponds
     * to the frames for moving right in the sprite sheet.
     * </p>
     *
     * @return the row index (2) for the "right" direction in the sprite sheet
     */
    @Override
    public int getSpriteSheetRow() {
        return 2;
    }

    @Override
    public void attack(Player player) {
        if (player.getWorldMap().isTileBlockedByEnemy(player.getTileX() + 1, player.getTileY())) {
            player.getWorldMap().killEnemy(player.getWorldMap().getEnemy(player.getTileX() + 1, player.getTileY()));
        }
    }
}
