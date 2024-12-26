package com.game.model.inputhandler.player;

import com.badlogic.gdx.Input;
import com.game.model.directions.UpDirection;
import com.game.model.entities.Player;

public class PlayerInputKeyUp extends PlayerInputKey {

    public PlayerInputKeyUp(){
        super(Input.Keys.W);
    }

    private void moveUp(Player player){
        player.getEntityAnimation().setWalkAnimation(new UpDirection(), player.getSpriteSheet());

        // if the Tile the player will move to next is not blocked (i.e. it is not an obstacle)
        if (player.getWorldMap().isTileNotBlocked(player.getTileX(), player.getTileY()-1)){
            // the player will move to the next Tile
            player.setTargetY(player.getTargetY() + player.getWorldMap().getTileHeight());
            player.setIsMoving(true);
        }
    }

    @Override
    public void action(Player player) {
        if (player.isNotMoving()){
            moveUp(player);
        }
    }
}
