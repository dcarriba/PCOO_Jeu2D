package com.game.model.map.layers.teleport.properties.custom.possiblevalues.whenkeypressedproperty;

import com.game.model.inputhandler.player.PlayerInputKeyRight;
import com.game.model.map.layers.teleport.properties.custom.possiblevalues.PossibleValue;

/**
 * The <code>ValuePlayerInputKeyRight</code> class represents a possible value for the "whenKeyPressed" property of a teleport object
 * from the TiledMap's teleport object layer.
 */
public class ValuePlayerInputKeyRight extends PossibleValue {

    /**
     * Constructor to initialize the value "PlayerInputKeyRight" with its associated object.
     * The name of the value is set as "PlayerInputKeyRight" and the associated object is an instance of <code>PlayerInputKeyRight</code>.
     */
    public ValuePlayerInputKeyRight() {
        super("PlayerInputKeyRight", new PlayerInputKeyRight());
    }
}
