package com.game.model.map.validator.properties;

import com.badlogic.gdx.maps.MapProperties;

public class PresencePropertiesValidator implements PropertiesValidator {
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
