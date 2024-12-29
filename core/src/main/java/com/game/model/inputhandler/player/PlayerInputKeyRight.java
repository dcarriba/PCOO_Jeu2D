package com.game.model.inputhandler.player;

import com.badlogic.gdx.Input;
import com.game.model.directions.RightDirection;
import com.game.model.entities.Player;

/** The <code>PlayerInputKeyRight</code> class represents the input key for moving the <code>Player</code> to the right. */
public class PlayerInputKeyRight extends PlayerInputKey {

    /**
     * Constructor to initialize the right movement input key.
     * The right movement action is bound to the "D" key.
     */
    public PlayerInputKeyRight(){
        super(Input.Keys.D);
    }

    /**
     * Moves the player to the right if the tile to the right of the player is not blocked.
     * It also updates the player's animation to reflect the right movement direction.
     * @param player The player who will be moved right.
     */
    private void moveRight(Player player){
        player.getEntityAnimation().setWalkAnimation(new RightDirection(), player.getSpriteSheet());

        // if the tile to the right of the player is not blocked
        if (player.getWorldMap().isTileNotBlocked(player.getTileX()+1, player.getTileY())){
            // the player will move to the right
            player.setTargetX(player.getTargetX() + player.getWorldMap().getTileWidth());
            player.setIsMoving(true);
        }
    }

    /**
     * Executes the right movement action.
     * @param player The player who is moving to the right.
     */
    @Override
    public void action(Player player){
        if (player.isNotMoving()){
            moveRight(player);
        }
    }
}
