package com.game.model.map.layers.teleport.objects.properties.custom.possiblevalues.whenkeypressedproperty;

import com.game.model.inputhandler.player.PlayerInputKeyLeft;
import com.game.model.map.layers.teleport.objects.properties.custom.possiblevalues.PossibleValue;

public class ValuePlayerInputKeyLeft extends PossibleValue {

    public ValuePlayerInputKeyLeft() {
        super("PlayerInputKeyLeft", new PlayerInputKeyLeft());
    }
}
