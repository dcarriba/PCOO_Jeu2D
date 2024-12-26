package com.game.model.map.layers.teleport.objects.properties.validator;

import com.badlogic.gdx.maps.MapProperties;

public class NotNullValidator implements Validator{
    private Validator next;

    @Override
    public void setNext(Validator next) {
        this.next = next;
    }

    @Override
    public boolean validate(MapProperties properties, String propertyName) {
        if (properties.get(propertyName) != null){
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
