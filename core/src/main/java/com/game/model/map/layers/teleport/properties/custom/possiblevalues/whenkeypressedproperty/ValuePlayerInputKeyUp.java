package com.game.model.map.layers.teleport.properties.custom.possiblevalues.whenkeypressedproperty;

import com.game.model.inputhandler.player.PlayerInputKeyUp;
import com.game.model.map.layers.teleport.properties.custom.possiblevalues.PossibleValue;

/**
 * The <code>ValuePlayerInputKeyUp</code> class represents a possible value for the "whenKeyPressed" property of a teleport object
 * from the TiledMap's teleport object layer.
 */
public class ValuePlayerInputKeyUp extends PossibleValue {

    /**
     * Constructor to initialize the value "PlayerInputKeyUp" with its associated object.
     * The name of the value is set as "PlayerInputKeyUp" and the associated object is an instance of <code>PlayerInputKeyUp</code>.
     */
    public ValuePlayerInputKeyUp() {
        super("PlayerInputKeyUp", new PlayerInputKeyUp());
    }
}
