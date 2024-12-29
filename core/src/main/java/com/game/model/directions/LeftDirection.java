package com.game.model.directions;

import com.game.model.entities.Player;

/** Represents the "left" direction in the game. */
public class LeftDirection extends Direction {

    /**
     * Retrieves the sprite sheet row corresponding to the "left" direction.
     * @return the row index (1) for the "left" direction in the sprite sheet
     */
    @Override
    public int getSpriteSheetRow() {
        return 1;
    }

    /**
     * Executes the attack action for the player in the "left" direction.
     * If there is an enemy to the left of the player, it will be killed.
     * @param player the player performing the attack
     */
    @Override
    public void attack(Player player) {
        if (player.getWorldMap().isTileBlockedByEnemy(player.getTileX() - 1, player.getTileY())) {
            player.getWorldMap().killEnemy(player.getWorldMap().getEnemy(player.getTileX() - 1, player.getTileY()));
        }
    }
}
