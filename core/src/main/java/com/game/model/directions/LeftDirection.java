package com.game.model.directions;

import com.game.model.entities.Player;

/**
 * Represents the "left" direction in the game.
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

    @Override
    public void attack(Player player) {
        if (player.getWorldMap().isTileBlockedByEnemy(player.getTileX() - 1, player.getTileY())) {
            player.getWorldMap().killEnemy(player.getWorldMap().getEnemy(player.getTileX() - 1, player.getTileY()));
        }
    }
}
