package com.game.model.inputhandler.player;

import com.badlogic.gdx.Input;
import com.game.model.directions.UpDirection;
import com.game.model.entities.Player;

/** The <code>PlayerInputKeyUp</code> class represents the input key for moving the <code>Player</code> upwards. */
public class PlayerInputKeyUp extends PlayerInputKey {

    /**
     * Constructor to initialize the up movement input key.
     * The up movement action is bound to the "W" key.
     */
    public PlayerInputKeyUp(){
        super(Input.Keys.W);
    }

    /**
     * Moves the player up if the tile above the player is not blocked.
     * It also updates the player's animation to reflect the up movement direction.
     * @param player The player who will be moved up.
     */
    private void moveUp(Player player){
        player.getEntityAnimation().setWalkAnimation(new UpDirection(), player.getSpriteSheet());

        // if the tile above the player is not blocked
        if (player.getWorldMap().isTileNotBlocked(player.getTileX(), player.getTileY()-1)){
            // the player will move up
            player.setTargetY(player.getTargetY() + player.getWorldMap().getTileHeight());
            player.setIsMoving(true);
        }
    }

    /**
     * Executes the up movement action.
     * @param player The player who is moving upwards.
     */
    @Override
    public void action(Player player) {
        if (player.isNotMoving()){
            moveUp(player);
        }
    }
}
