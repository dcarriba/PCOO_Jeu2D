package com.game.model.map.layers.teleport.properties.custom.possiblevalues.whenkeypressedproperty;

import com.game.model.inputhandler.player.PlayerInputKeyLeft;
import com.game.model.map.layers.teleport.properties.custom.possiblevalues.PossibleValue;

/**
 * The <code>ValuePlayerInputKeyLeft</code> class represents a possible value for the "whenKeyPressed" property of a teleport object
 * from the TiledMap's teleport object layer.
 */
public class ValuePlayerInputKeyLeft extends PossibleValue {

    /**
     * Constructor to initialize the value "PlayerInputKeyLeft" with its associated object.
     * The name of the value is set as "PlayerInputKeyLeft" and the associated object is an instance of <code>PlayerInputKeyLeft</code>.
     */
    public ValuePlayerInputKeyLeft() {
        super("PlayerInputKeyLeft", new PlayerInputKeyLeft());
    }
}
