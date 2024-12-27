package com.game.model.map.validator.properties;

import com.badlogic.gdx.maps.MapProperties;

public interface PropertiesValidator {
    void setNext(PropertiesValidator next);
    boolean validate(MapProperties properties, String name);
}
