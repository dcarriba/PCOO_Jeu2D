package com.game.model.map.layers.teleport.properties.custom.possiblevalues;

public abstract class PossibleValue {
    private final String valueName;
    private final Object associatedObject;

    public PossibleValue(String valueName, Object associatedObject) {
        this.valueName = valueName;
        this.associatedObject = associatedObject;
    }

    public String getValueName() {
        return valueName;
    }

    public Object getAssociatedObject() {
        return associatedObject;
    }
}
