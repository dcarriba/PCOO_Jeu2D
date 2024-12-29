package com.game.model.directions;

import com.game.model.entities.Player;

/** Represents the "up" direction in the game. */
public class UpDirection extends Direction {

    /**
     * Retrieves the sprite sheet row corresponding to the "up" direction.
     * @return the row index (3) for the "up" direction in the sprite sheet
     */
    @Override
    public int getSpriteSheetRow() {
        return 3;
    }

    /**
     * Executes the attack action for the player in the "up" direction.
     * If there is an enemy above the player, it will be killed.
     * @param player the player performing the attack
     */
    @Override
    public void attack(Player player) {
        if (player.getWorldMap().isTileBlockedByEnemy(player.getTileX(), player.getTileY() - 1)) {
            player.getWorldMap().killEnemy(player.getWorldMap().getEnemy(player.getTileX(), player.getTileY() - 1));
        }
    }
}
