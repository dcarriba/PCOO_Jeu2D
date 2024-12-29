package com.game.model.map.validator.properties;

import com.badlogic.gdx.maps.MapProperties;

import java.util.Objects;

/** The <code>NotNullPropertiesValidator</code> class ensures that a specified property in the map properties is not null or empty. */
public class NotNullPropertiesValidator implements PropertiesValidator {
    /** The next validator in the chain, if any. */
    private PropertiesValidator next;

    @Override
    public void setNext(PropertiesValidator next) {
        this.next = next;
    }

    @Override
    public boolean validate(MapProperties properties, String propertyName) {
        if (properties.get(propertyName) != null && !Objects.equals(properties.get(propertyName), "")){
            if (next != null){
                return next.validate(properties, propertyName);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
