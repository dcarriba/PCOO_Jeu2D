package com.game.input.player;

import com.game.entities.Player;

public abstract class PlayerInputKey {
    private int key;

    public PlayerInputKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public abstract void action(Player player);
}
