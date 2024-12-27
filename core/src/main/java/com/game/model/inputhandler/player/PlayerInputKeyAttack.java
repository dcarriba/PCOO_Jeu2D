package com.game.model.inputhandler.player;

import com.badlogic.gdx.Input;
import com.game.model.directions.Direction;
import com.game.model.entities.Player;

public class PlayerInputKeyAttack extends PlayerInputKey {

    public PlayerInputKeyAttack() {
        super(Input.Keys.SPACE);
    }

    @Override
    public void action(Player player) {
        Direction currentDirection = player.getEntityAnimation().getCurrentFacingDirection();
        currentDirection.attack(player);
    }
}
