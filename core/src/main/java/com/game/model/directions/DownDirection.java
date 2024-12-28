package com.game.model.directions;

import com.game.model.entities.Player;

/**
 * Represents the "down" direction in the game.
 */
public class DownDirection extends Direction {

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

    @Override
    public void attack(Player player){
        if (player.getWorldMap().isTileBlockedByEnemy(player.getTileX(), player.getTileY() + 1)) {
            player.getWorldMap().killEnemy(player.getWorldMap().getEnemy(player.getTileX(), player.getTileY() + 1));
        }
    }
}
