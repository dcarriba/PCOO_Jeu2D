package com.game.model.directions;

import com.game.model.entities.Player;

/** Represents the "right" direction in the game. */
public class RightDirection extends Direction {

    /**
     * Retrieves the sprite sheet row corresponding to the "right" direction.
     * @return the row index (2) for the "right" direction in the sprite sheet
     */
    @Override
    public int getSpriteSheetRow() {
        return 2;
    }

    /**
     * Executes the attack action for the player in the "right" direction.
     * If there is an enemy to the right of the player, it will be killed.
     * @param player the player performing the attack
     */
    @Override
    public void attack(Player player) {
        if (player.getWorldMap().isTileBlockedByEnemy(player.getTileX() + 1, player.getTileY())) {
            player.getWorldMap().killEnemy(player.getWorldMap().getEnemy(player.getTileX() + 1, player.getTileY()));
        }
    }
}
