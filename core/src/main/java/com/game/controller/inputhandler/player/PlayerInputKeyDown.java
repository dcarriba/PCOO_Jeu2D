package com.game.controller.inputhandler.player;

import com.badlogic.gdx.Input;
import com.game.model.directions.DownDirection;
import com.game.model.entities.Player;

public class PlayerInputKeyDown extends PlayerInputKey {

    public PlayerInputKeyDown(){
        super(Input.Keys.S);
    }

    private void moveDown(Player player){
        player.getEntityAnimation().setWalkAnimation(new DownDirection(), player.getSpriteSheet());

        // if the Tile the player will move to next is not blocked (i.e. it is not an obstacle)
        if (player.getWorldMap().isTileNotBlocked(player.getPositionX()/player.getWorldMap().getTileWidth(), player.getPositionY()/player.getWorldMap().getTileWidth() - 1)) {
            // the player will move to the next Tile
            player.setTargetY(player.getTargetY() - player.getWorldMap().getTileHeight());
            player.setIsMoving(true);
        }
    }

    @Override
    public void action(Player player) {
        if (player.isNotMoving()){
            moveDown(player);
        }
    }
}
