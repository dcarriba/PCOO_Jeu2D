package com.game.model.map.validator.properties;

import com.badlogic.gdx.maps.MapProperties;

/** The <code>PresencePropertiesValidator</code> class validates if a specified property exists in the given map properties. */
public class PresencePropertiesValidator implements PropertiesValidator {
    /** The next validator in the chain, if any. */
    private PropertiesValidator next;

    @Override
    public void setNext(PropertiesValidator next) {
        this.next = next;
    }

    @Override
    public boolean validate(MapProperties properties, String propertyName) {
        if (properties.containsKey(propertyName)){
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
