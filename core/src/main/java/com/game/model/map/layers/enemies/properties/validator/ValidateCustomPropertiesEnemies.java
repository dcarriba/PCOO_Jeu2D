package com.game.model.map.layers.enemies.properties.validator;

import com.badlogic.gdx.maps.MapProperties;
import com.game.model.map.layers.enemies.properties.custom.SpriteSheetImageName;
import com.game.model.map.validator.properties.NotNullPropertiesValidator;
import com.game.model.map.validator.properties.PresencePropertiesValidator;
import com.game.model.map.validator.properties.PropertiesValidator;

import java.util.ArrayList;
import java.util.List;

public class ValidateCustomPropertiesEnemies {
    private final List<String> customPropertyNames = createCustomPropertyNamesList();

    public ValidateCustomPropertiesEnemies() {
    }

    private List<String> createCustomPropertyNamesList(){
        List<String> customProperties = new ArrayList<>();
        customProperties.add(new SpriteSheetImageName().getName());
        return customProperties;
    }

    /**
     * Checks if a property exist and has a defined value amongst the properties of the object from
     * the enemies object layer.
     * @param properties the properties of the object from the enemies object layer
     * @param propertyName the name of the property
     * @return  if property given as an argument (propertyName) is present and has a defined value
     *          amongst the properties of the object from the enemies object layer.
     */
    private boolean validateProperty(MapProperties properties, String propertyName){
        PropertiesValidator presence = new PresencePropertiesValidator();
        PropertiesValidator notNull = new NotNullPropertiesValidator();
        presence.setNext(notNull);
        return presence.validate(properties, propertyName);
    }

    /**
     * Checks if all properties in the customPropertyNames List exist and have a defined value amongst the properties of
     * the object from the enemies object layer.
     * @param properties the properties of the object from the enemies object layer
     * @return if all properties in the customPropertyNames List are present and have a defined value.
     */
    public boolean validate(MapProperties properties){
        boolean result = true;
        for (String propertyName : customPropertyNames){
            result = result && validateProperty(properties, propertyName);
        }
        return result;
    }
}
