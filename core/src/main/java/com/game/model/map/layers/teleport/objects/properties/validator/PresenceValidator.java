package com.game.model.map.layers.teleport.objects.properties.validator;

import com.badlogic.gdx.maps.MapProperties;

public class PresenceValidator implements Validator{
    private Validator next;

    @Override
    public void setNext(Validator next) {
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
