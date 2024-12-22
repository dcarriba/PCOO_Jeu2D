package com.game.controller.inputhandler.player;

import com.badlogic.gdx.Input;
import com.game.model.directions.RightDirection;
import com.game.model.entities.Player;

public class PlayerInputKeyRight extends PlayerInputKey {

    public PlayerInputKeyRight(){
        super(Input.Keys.D);
    }

    private void moveRight(Player player){
        player.getEntityAnimation().setWalkAnimation(new RightDirection(), player.getSpriteSheet());

        // if the Tile the player will move to next is not blocked (i.e. it is not an obstacle)
        if (player.getWorldMap().isTileNotBlocked(player.getTileX()+1, player.getTileY())){
            // the player will move to the next Tile
            player.setTargetX(player.getTargetX() + player.getWorldMap().getTileWidth());
            player.setIsMoving(true);
        }
    }

    @Override
    public void action(Player player){
        if (player.isNotMoving()){
            moveRight(player);
        }
    }
}
