package com.game.model.map.layers.teleport.properties.custom;

import com.badlogic.gdx.maps.MapProperties;
import com.game.model.inputhandler.player.PlayerInputKey;
import com.game.model.map.layers.teleport.properties.custom.possiblevalues.PossibleValue;
import com.game.model.map.layers.teleport.properties.custom.possiblevalues.whenkeypressedproperty.ValuePlayerInputKeyDown;
import com.game.model.map.layers.teleport.properties.custom.possiblevalues.whenkeypressedproperty.ValuePlayerInputKeyLeft;
import com.game.model.map.layers.teleport.properties.custom.possiblevalues.whenkeypressedproperty.ValuePlayerInputKeyRight;
import com.game.model.map.layers.teleport.properties.custom.possiblevalues.whenkeypressedproperty.ValuePlayerInputKeyUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WhenKeyPressedProperty {
    /** The name of the custom property. */
    private final String name;
    /** List of possible values associated with the "whenKeyPressed" property. */
    private final List<PossibleValue> possibleValues;

    /** Constructor that initializes the property name and possible values. */
    public WhenKeyPressedProperty() {
        this.name = "whenKeyPressed";
        this.possibleValues = createPossibleValuesList();
    }

    public String getName() {
        return this.name;
    }

    /**
     * Creates the list of possible values for the "whenKeyPressed" property.
     * These values correspond to different key inputs (e.g., Up, Down, Left, Right).
     * @return a list of possible values associated with the "whenKeyPressed" property.
     */
    private List<PossibleValue> createPossibleValuesList(){
        List<PossibleValue> possibleValues = new ArrayList<>();
        possibleValues.add(new ValuePlayerInputKeyRight());
        possibleValues.add(new ValuePlayerInputKeyLeft());
        possibleValues.add(new ValuePlayerInputKeyUp());
        possibleValues.add(new ValuePlayerInputKeyDown());
        return possibleValues;
    }

    /**
     * Retrieves the associated PlayerInputKey based on the value in the provided MapProperties.
     * @param properties the properties of the teleport object from the tiledmap.
     * @return  the corresponding PlayerInputKey associated to the value of the "whenKeyPressed" property,
     *          or null if no associated PlayerInputKey is found.
     */
    public PlayerInputKey retrieveAssociatedPlayerInputKey(MapProperties properties){
        for (PossibleValue possibleValue : possibleValues){
            if (Objects.equals(properties.get(this.name), possibleValue.getValueName())) {
                return (PlayerInputKey) possibleValue.getAssociatedObject();
            }
        }
        return null;
    }
}
