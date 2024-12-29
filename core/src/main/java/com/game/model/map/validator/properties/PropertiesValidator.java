package com.game.model.map.validator.properties;

import com.badlogic.gdx.maps.MapProperties;

/** The <code>PropertiesValidator</code> interface defines the structure for validating MapProperties from a TiledMap. */
public interface PropertiesValidator {

    /**
     * Sets the next validator in the chain.
     * @param next The next validator to be set.
     */
    void setNext(PropertiesValidator next);

    /**
     * Validates a specific property.
     * @param properties The map properties to check.
     * @param name The name of the property to validate.
     * @return True if the property is valid, false otherwise.
     */
    boolean validate(MapProperties properties, String name);
}
