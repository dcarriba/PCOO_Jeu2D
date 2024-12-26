package com.game.model.inputhandler.player;

import com.badlogic.gdx.Input;
import com.game.model.directions.LeftDirection;
import com.game.model.entities.Player;

public class PlayerInputKeyLeft extends PlayerInputKey {

    public PlayerInputKeyLeft(){
        super(Input.Keys.A);
    }

    private void moveLeft(Player player){
        player.getEntityAnimation().setWalkAnimation(new LeftDirection(), player.getSpriteSheet());

        // if the Tile the player will move to next is not blocked (i.e. it is not an obstacle)
        if (player.getWorldMap().isTileNotBlocked(player.getTileX()-1, player.getTileY())){
            // the player will move to the next Tile
            player.setTargetX(player.getTargetX() - player.getWorldMap().getTileWidth());
            player.setIsMoving(true);
        }
    }

    @Override
    public void action(Player player){
        if (player.isNotMoving()) {
            moveLeft(player);
        }
    }
}
