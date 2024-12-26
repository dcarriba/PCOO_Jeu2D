package com.game.model.map.layers.teleport.objects.properties.custom.possiblevalues.whenkeypressedproperty;

import com.game.model.inputhandler.player.PlayerInputKeyRight;
import com.game.model.map.layers.teleport.objects.properties.custom.possiblevalues.PossibleValue;

public class ValuePlayerInputKeyRight extends PossibleValue {

    public ValuePlayerInputKeyRight() {
        super("PlayerInputKeyRight", new PlayerInputKeyRight());
    }
}
