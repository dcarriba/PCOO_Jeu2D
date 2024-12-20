package com.game.input.player;

import com.badlogic.gdx.Input;
import com.game.directions.UpDirection;
import com.game.entities.Player;

public class PlayerInputKeyUp extends PlayerInputKey {

    public PlayerInputKeyUp(){
        super(Input.Keys.W);
    }

    @Override
    public void action(Player player) {
        player.getEntityAnimation().setWalkAnimation(new UpDirection(), player.getSpriteSheet());

        // if the Tile the player will move to next is not blocked (i.e. it is not an obstacle)
        if (player.getWorldMap().isTileNotBlocked(player.getPositionX()/player.getWorldMap().getTileWidth(), player.getPositionY()/player.getWorldMap().getTileWidth() + 1)){
            // the player will move to the next Tile
            player.setTargetY(player.getTargetY() + player.getWorldMap().getTileHeight());
            player.setIsMoving(true);
        }
    }
}
