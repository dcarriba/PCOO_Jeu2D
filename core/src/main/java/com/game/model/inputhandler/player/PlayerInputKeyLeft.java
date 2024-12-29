package com.game.model.inputhandler.player;

import com.badlogic.gdx.Input;
import com.game.model.directions.LeftDirection;
import com.game.model.entities.Player;

/** The <code>PlayerInputKeyLeft</code> class represents the input key for moving the <code>Player</code> to the left. */
public class PlayerInputKeyLeft extends PlayerInputKey {

    /**
     * Constructor to initialize the left movement input key.
     * The left movement action is bound to the "A" key.
     */
    public PlayerInputKeyLeft(){
        super(Input.Keys.A);
    }

    /**
     * Moves the player to the left if the tile to the left of the player is not blocked.
     * It also updates the player's animation to reflect the left movement direction.
     * @param player The player who will be moved left.
     */
    private void moveLeft(Player player){
        player.getEntityAnimation().setWalkAnimation(new LeftDirection(), player.getSpriteSheet());

        // if the tile to the left of the player is not blocked
        if (player.getWorldMap().isTileNotBlocked(player.getTileX()-1, player.getTileY())){
            // the player will move to the left
            player.setTargetX(player.getTargetX() - player.getWorldMap().getTileWidth());
            player.setIsMoving(true);
        }
    }

    /**
     * Executes the left movement action.
     * @param player The player who is moving to the left.
     */
    @Override
    public void action(Player player){
        if (player.isNotMoving()) {
            moveLeft(player);
        }
    }
}
