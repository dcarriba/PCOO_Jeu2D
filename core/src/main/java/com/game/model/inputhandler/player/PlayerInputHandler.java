package com.game.model.inputhandler.player;

import com.badlogic.gdx.Gdx;
import com.game.model.entities.Player;

import java.util.ArrayList;
import java.util.List;

/** The <code>PlayerInputHandler</code> class is responsible for processing user input related to the <code>Player</code>. */
public class PlayerInputHandler {
    private final Player player;
    private final List<PlayerInputKey> inputKeyList;

    /** Constructor to initialize the input handler. */
    public PlayerInputHandler(Player player){
        this.player = player;
        this.inputKeyList = createInputKeyList();
    }

    /** Handles the player's input, it checks which keys are pressed and triggers the corresponding actions. */
    public void handleInput(){
        for (PlayerInputKey inputKey : inputKeyList) {
            if (Gdx.input.isKeyPressed(inputKey.getKey())) {
                inputKey.action(player);
                break;
            }
        }
        PlayerInputKeySprint inputKeySprint = new PlayerInputKeySprint();
        if (Gdx.input.isKeyPressed(inputKeySprint.getKey())) {
            inputKeySprint.action(player);
        } else {
            inputKeySprint.revertAction(player);
        }
    }

    /**
     * Creates a list of input keys that the player can use.
     * @return A <code>List</code> of <code>PlayerInputKey</code> objects.
     */
    private List<PlayerInputKey> createInputKeyList() {
        List<PlayerInputKey> inputKeyList = new ArrayList<>();
        inputKeyList.add(new PlayerInputKeyUp());
        inputKeyList.add(new PlayerInputKeyDown());
        inputKeyList.add(new PlayerInputKeyRight());
        inputKeyList.add(new PlayerInputKeyLeft());
        return inputKeyList;
    }
}
