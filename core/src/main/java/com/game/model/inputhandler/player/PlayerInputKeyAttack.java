package com.game.model.inputhandler.player;

import com.badlogic.gdx.Input;
import com.game.model.directions.Direction;
import com.game.model.entities.Player;

/**
 * The <code>PlayerInputKeyAttack</code> class represents the attack input key for the <code>Player</code>.
 * It is responsible for triggering the player's attack action when the corresponding key is pressed.
 */
public class PlayerInputKeyAttack extends PlayerInputKey {

    /**
     * Constructor to initialize the attack input key.
     * The attack action is bound to the space key.
     */
    public PlayerInputKeyAttack() {
        super(Input.Keys.SPACE);
    }

    /**
     * Executes the attack action when the space key is pressed.
     * The player's current facing direction determines the attack direction.
     * @param player The player performing the attack
     */
    @Override
    public void action(Player player) {
        Direction currentDirection = player.getEntityAnimation().getCurrentFacingDirection();
        currentDirection.attack(player);
    }
}
