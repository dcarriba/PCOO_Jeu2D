package com.game.model.map.layers.teleport.properties.custom;

import com.badlogic.gdx.maps.MapProperties;
import com.game.model.inputhandler.player.*;
import com.game.model.map.layers.teleport.properties.custom.possiblevalues.PossibleValue;
import com.game.model.map.layers.teleport.properties.custom.possiblevalues.whenkeypressedproperty.ValuePlayerInputKeyDown;
import com.game.model.map.layers.teleport.properties.custom.possiblevalues.whenkeypressedproperty.ValuePlayerInputKeyLeft;
import com.game.model.map.layers.teleport.properties.custom.possiblevalues.whenkeypressedproperty.ValuePlayerInputKeyRight;
import com.game.model.map.layers.teleport.properties.custom.possiblevalues.whenkeypressedproperty.ValuePlayerInputKeyUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WhenKeyPressedProperty {
    private final String name;
    private final List<PossibleValue> possibleValues;

    public WhenKeyPressedProperty() {
        this.name = "whenKeyPressed";
        this.possibleValues = createPossibleValuesList();
    }

    public String getName() {
        return this.name;
    }

    private List<PossibleValue> createPossibleValuesList(){
        List<PossibleValue> possibleValues = new ArrayList<>();
        possibleValues.add(new ValuePlayerInputKeyRight());
        possibleValues.add(new ValuePlayerInputKeyLeft());
        possibleValues.add(new ValuePlayerInputKeyUp());
        possibleValues.add(new ValuePlayerInputKeyDown());
        return possibleValues;
    }

    public PlayerInputKey retrieveAssociatedPlayerInputKey(MapProperties properties){
        for (PossibleValue possibleValue : possibleValues){
            if (Objects.equals(properties.get(this.name), possibleValue.getValueName())) {
                return (PlayerInputKey) possibleValue.getAssociatedObject();
            }
        }
        return null;
    }
}
