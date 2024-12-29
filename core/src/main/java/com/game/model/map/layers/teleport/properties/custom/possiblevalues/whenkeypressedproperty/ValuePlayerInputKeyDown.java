package com.game.model.map.layers.teleport.properties.custom.possiblevalues.whenkeypressedproperty;

import com.game.model.inputhandler.player.PlayerInputKeyDown;
import com.game.model.map.layers.teleport.properties.custom.possiblevalues.PossibleValue;

/**
 * The <code>ValuePlayerInputKeyDown</code> class represents a possible value for the "whenKeyPressed" property of a teleport object
 * from the TiledMap's teleport object layer.
 */
public class ValuePlayerInputKeyDown extends PossibleValue {

    /**
     * Constructor to initialize the value "PlayerInputKeyDown" with its associated object.
     * The name of the value is set as "PlayerInputKeyDown" and the associated object is an instance of <code>PlayerInputKeyDown</code>.
     */
    public ValuePlayerInputKeyDown() {
        super("PlayerInputKeyDown", new PlayerInputKeyDown());
    }
}
