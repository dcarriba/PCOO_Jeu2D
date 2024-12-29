package com.game.model.inputhandler.player;

import com.badlogic.gdx.Input;
import com.game.model.entities.Player;

/** The <code>PlayerInputKeySprint</code> class represents the input key for making the <code>Player</code> sprint. */
public class PlayerInputKeySprint extends PlayerInputKey {

    /**
     * Constructor to initialize the sprint input key.
     * The sprint action is bound to the "SHIFT_LEFT" key.
     */
    public PlayerInputKeySprint(){
        super(Input.Keys.SHIFT_LEFT);
    }

    /**
     * Increases the player's movement speed to double the default speed.
     * @param player The player whose movement speed will be increased.
     */
    @Override
    public void action(Player player) {
        player.setMoveSpeed(player.getMoveSpeedDefault()*2);
    }

    /**
     * Resets the player's movement speed back to the default speed.
     * @param player The player whose movement speed will be reset.
     */
    public void revertAction(Player player){
        player.setMoveSpeed(player.getMoveSpeedDefault());
    }
}
