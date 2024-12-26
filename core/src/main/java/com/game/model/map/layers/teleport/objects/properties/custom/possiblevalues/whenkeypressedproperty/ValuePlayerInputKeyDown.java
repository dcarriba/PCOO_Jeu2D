package com.game.model.map.layers.teleport.objects.properties.custom.possiblevalues.whenkeypressedproperty;

import com.game.model.inputhandler.player.PlayerInputKeyDown;
import com.game.model.map.layers.teleport.objects.properties.custom.possiblevalues.PossibleValue;

public class ValuePlayerInputKeyDown extends PossibleValue {

    public ValuePlayerInputKeyDown() {
        super("PlayerInputKeyDown", new PlayerInputKeyDown());
    }
}
