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
        player.setTargetY(player.getTargetY() + player.getWorldMap().getTileHeight());
        player.setIsMoving(true);
        player.getEntityAnimation().setWalkAnimation(new UpDirection(), player.getSpriteSheet());
    }
}
