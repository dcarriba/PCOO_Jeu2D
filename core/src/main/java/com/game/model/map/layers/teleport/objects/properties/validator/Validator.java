package com.game.model.map.layers.teleport.objects.properties.validator;

import com.badlogic.gdx.maps.MapProperties;

public interface Validator {
    void setNext(Validator next);
    boolean validate(MapProperties properties, String name);
}
