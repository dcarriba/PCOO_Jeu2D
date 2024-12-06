package com.game.input.player;

import com.badlogic.gdx.Input;
import com.game.directions.DownDirection;
import com.game.entities.Player;

public class PlayerInputKeyDown extends PlayerInputKey {

    public PlayerInputKeyDown(){
        super(Input.Keys.S);
    }

    @Override
    public void action(Player player) {
        player.setTargetY(player.getTargetY() - player.getWorldMap().getTileHeight());
        player.setIsMoving(true);
        player.getEntityAnimation().setWalkAnimation(new DownDirection(), player.getSpriteSheet());
    }
}
