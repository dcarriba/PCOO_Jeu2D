package com.game.model.directions;

import com.game.model.entities.Player;

/**
 * Represents the "up" direction in the game.
 * <p>
 * This class implements the {@link Direction} interface and provides the specific
 * sprite sheet row index for the "up" direction. This index is used to correctly
 * animate the character when moving up.
 * </p>
 */
public class UpDirection implements Direction {

    /**
     * Retrieves the sprite sheet row corresponding to the "up" direction.
     * <p>
     * In this case, the row index for the "up" direction is 3, which corresponds
     * to the frames for moving up in the sprite sheet.
     * </p>
     *
     * @return the row index (3) for the "up" direction in the sprite sheet
     */
    @Override
    public int getSpriteSheetRow() {
        return 3;
    }

    @Override
    public void attack(Player player) {
        if (player.getWorldMap().isTileBlockedByEnemy(player.getTileX(), player.getTileY() - 1)) {
            player.getWorldMap().killEnemy(player.getWorldMap().getEnemy(player.getTileX(), player.getTileY() - 1));
        }
    }
}
