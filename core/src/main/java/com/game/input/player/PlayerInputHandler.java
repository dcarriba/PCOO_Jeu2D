package com.game.input.player;

import com.badlogic.gdx.Gdx;
import com.game.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerInputHandler {

    public PlayerInputHandler(){
    }

    public void handleInput(Player player){
        List<PlayerInputKey> inputKeyList = createInputKeyList();
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

    /** Creates a List containing all input keys */
    private List<PlayerInputKey> createInputKeyList() {
        List<PlayerInputKey> inputKeyList = new ArrayList<>();
        inputKeyList.add(new PlayerInputKeyUp());
        inputKeyList.add(new PlayerInputKeyDown());
        inputKeyList.add(new PlayerInputKeyRight());
        inputKeyList.add(new PlayerInputKeyLeft());
        return inputKeyList;
    }
}
