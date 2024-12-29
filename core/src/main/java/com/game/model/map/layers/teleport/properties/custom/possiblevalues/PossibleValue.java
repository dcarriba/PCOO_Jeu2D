package com.game.model.map.layers.teleport.properties.custom.possiblevalues;

/**
 * The <code>PossibleValue</code> class represents a possible value associated with a custom property.
 * This class serves as a base class for different types of possible values.
 */
public abstract class PossibleValue {
    /** The name of the value. */
    private final String valueName;
    /** The associated object (e.g., a PlayerInputKey) corresponding to this value. */
    private final Object associatedObject;

    /**
     * Constructor to initialize the possible value with a name and associated object.
     * @param valueName the name of the value.
     * @param associatedObject the object associated with this value.
     */
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
