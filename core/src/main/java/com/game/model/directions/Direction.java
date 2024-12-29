package com.game.model.directions;

import com.game.model.entities.Player;

import java.io.Serializable;

/**
 * The <code>Direction</code> class represents a direction that the player can face
 * and provides methods to retrieve animation details and perform actions.
 */
public abstract class Direction implements Serializable {

    /**
     * Retrieves the sprite sheet row corresponding to this direction.
     * This row is used to select the appropriate frames for the animation.
     * @return the row index in the sprite sheet that represents this direction
     */
    public abstract int getSpriteSheetRow();

    /**
     * Executes the attack action for the player in this direction.
     * @param player the player performing the attack
     */
    public abstract void attack(Player player);
}
