package com.game.input.player;

import com.badlogic.gdx.Input;
import com.game.directions.LeftDirection;
import com.game.entities.Player;

public class PlayerInputKeyLeft extends PlayerInputKey {

    public PlayerInputKeyLeft(){
        super(Input.Keys.A);
    }

    @Override
    public void action(Player player) {
        player.setTargetX(player.getTargetX() - player.getWorldMap().getTileWidth());
        player.setIsMoving(true);
        player.getEntityAnimation().setWalkAnimation(new LeftDirection(), player.getSpriteSheet());
    }
}
