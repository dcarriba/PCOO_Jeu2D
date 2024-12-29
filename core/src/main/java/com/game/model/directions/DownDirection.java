package com.game.model.directions;

import com.game.model.entities.Player;

/** Represents the "down" direction in the game. */
public class DownDirection extends Direction {

    /**
     * Retrieves the sprite sheet row corresponding to the "down" direction.
     * @return the row index (0) for the "down" direction in the sprite sheet
     */
    @Override
    public int getSpriteSheetRow() {
        return 0;
    }

    /**
     * Executes the attack action for the player in the "down" direction.
     * If there is an enemy below the player, it will be killed.
     * @param player the player performing the attack
     */
    @Override
    public void attack(Player player){
        if (player.getWorldMap().isTileBlockedByEnemy(player.getTileX(), player.getTileY() + 1)) {
            player.getWorldMap().killEnemy(player.getWorldMap().getEnemy(player.getTileX(), player.getTileY() + 1));
        }
    }
}
