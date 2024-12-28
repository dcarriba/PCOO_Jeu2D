package com.game.model.directions;

import com.game.model.entities.Player;

import java.io.Serializable;

public abstract class Direction implements Serializable {

    /**
     * Retrieves the sprite sheet row corresponding to this direction.
     * This row is used to select the appropriate frames for the animation.
     *
     * @return the row index in the sprite sheet that represents this direction
     */
    public abstract int getSpriteSheetRow();
    public abstract void attack(Player player);
}
