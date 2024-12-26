package com.game.model.map.layers.teleport.objects.properties.custom.possiblevalues.whenkeypressedproperty;

import com.game.model.inputhandler.player.PlayerInputKeyUp;
import com.game.model.map.layers.teleport.objects.properties.custom.possiblevalues.PossibleValue;

public class ValuePlayerInputKeyUp extends PossibleValue {

    public ValuePlayerInputKeyUp() {
        super("PlayerInputKeyUp", new PlayerInputKeyUp());
    }
}
