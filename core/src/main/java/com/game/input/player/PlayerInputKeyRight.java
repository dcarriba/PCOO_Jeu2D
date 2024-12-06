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
        player.setTargetX(player.getTargetX() + player.getWorldMap().getTileWidth());
        player.setIsMoving(true);
        player.getEntityAnimation().setWalkAnimation(new RightDirection(), player.getSpriteSheet());
    }
}
