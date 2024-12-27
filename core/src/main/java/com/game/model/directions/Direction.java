package com.game.model.directions;

import com.game.model.entities.Player;

public interface Direction {

    /**
     * Retrieves the sprite sheet row corresponding to this direction.
     * This row is used to select the appropriate frames for the animation.
     *
     * @return the row index in the sprite sheet that represents this direction
     */
    int getSpriteSheetRow();
    void attack(Player player);
}
