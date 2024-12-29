package com.game.model.inputhandler.player;

import com.badlogic.gdx.Input;
import com.game.model.directions.DownDirection;
import com.game.model.entities.Player;

/** The <code>PlayerInputKeyDown</code> class represents the input key for moving the <code>Player</code> downwards. */
public class PlayerInputKeyDown extends PlayerInputKey {

    /**
     * Constructor to initialize the down movement input key.
     * The down movement action is bound to the "S" key.
     */
    public PlayerInputKeyDown(){
        super(Input.Keys.S);
    }

    /**
     * Moves the player down if the tile below the player is not blocked.
     * It also updates the player's animation to reflect the down movement direction.
     * @param player The player who will be moved down.
     */
    private void moveDown(Player player){
        player.getEntityAnimation().setWalkAnimation(new DownDirection(), player.getSpriteSheet());

        // if the tile below the player is not blocked
        if (player.getWorldMap().isTileNotBlocked(player.getTileX(), player.getTileY()+1)){
            // the player will move down
            player.setTargetY(player.getTargetY() - player.getWorldMap().getTileHeight());
            player.setIsMoving(true);
        }
    }

    /**
     * Executes the down movement action.
     * @param player The player who is moving downwards.
     */
    @Override
    public void action(Player player){
        if (player.isNotMoving()){
            moveDown(player);
        }
    }
}
