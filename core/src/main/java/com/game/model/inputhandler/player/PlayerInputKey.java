package com.game.model.inputhandler.player;

import com.game.model.entities.Player;

/**
 * The abstract <code>PlayerInputKey</code> class represents an abstract input action for the player.
 * It defines a key and the corresponding action that will be executed when that key is pressed.
 */
public abstract class PlayerInputKey {
    /** The key associated with the input action. */
    private int key;

    /**
     * Constructs a PlayerInputKey with the specified key.
     * @param key The key that will trigger the input action. This is typically a key code from
     *            a key press event (e.g., <code>Input.Keys.W</code> for the 'W' key).
     */
    public PlayerInputKey(int key) {
        this.key = key;
    }

    /**
     * Gets the key associated with this input action.
     * @return The key that is mapped to this action (e.g., <code>Input.Keys.W</code>).
     */
    public int getKey() {
        return key;
    }

    /**
     * Sets a new key for this input action.
     * @param key The key to assign to this action.
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * Defines the action to be executed when the associated key is pressed.
     * @param player The Player that the input action will affect.
     */
    public abstract void action(Player player);
}
