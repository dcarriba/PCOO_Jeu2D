package com.game.input.player;

import com.badlogic.gdx.Input;
import com.game.entities.Player;

public class PlayerInputKeySprint extends PlayerInputKey {

    public PlayerInputKeySprint(){
        super(Input.Keys.SHIFT_LEFT);
    }

    @Override
    public void action(Player player) {
        player.setMoveSpeed(player.getMoveSpeedDefault() * 2);
    }

    public void revertAction(Player player){
        player.setMoveSpeed(player.getMoveSpeedDefault());
    }
}
