package com.game.input.player;

import com.badlogic.gdx.Input;
import com.game.directions.RightDirection;
import com.game.entities.Player;

public class PlayerInputKeyRight extends PlayerInputKey {

    public PlayerInputKeyRight(){
        super(Input.Keys.D);
    }

    @Override
    public void action(Player player) {
        player.getEntityAnimation().setWalkAnimation(new RightDirection(), player.getSpriteSheet());

        // if the Tile the player will move to next is not blocked (i.e. it is not an obstacle)
        if (player.getWorldMap().isTileNotBlocked(player.getPositionX()/player.getWorldMap().getTileWidth() + 1, player.getPositionY()/player.getWorldMap().getTileWidth())) {
            // the player will move to the next Tile
            player.setTargetX(player.getTargetX() + player.getWorldMap().getTileWidth());
            player.setIsMoving(true);
        }
    }
}
