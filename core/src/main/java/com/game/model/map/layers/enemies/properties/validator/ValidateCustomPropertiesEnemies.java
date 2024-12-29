package com.game.model.map.layers.enemies.properties.validator;

import com.badlogic.gdx.maps.MapProperties;
import com.game.model.map.layers.enemies.properties.custom.SpriteSheetImageName;
import com.game.model.map.validator.properties.NotNullPropertiesValidator;
import com.game.model.map.validator.properties.PresencePropertiesValidator;
import com.game.model.map.validator.properties.PropertiesValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * The <code>ValidateCustomPropertiesEnemies</code> class validates the custom properties of an enemies object
 * from the tiledmap's enemies object layer.
 */
public class ValidateCustomPropertiesEnemies {
    /** List of custom property names to validate. */
    private final List<String> customPropertyNames = createCustomPropertyNamesList();

    public ValidateCustomPropertiesEnemies() {
    }

    /**
     * Creates the list of custom property names for an enemies object from the tiledmap's enemies object layer.
     * @return a list of custom property names.
     */
    private List<String> createCustomPropertyNamesList(){
        List<String> customProperties = new ArrayList<>();
        customProperties.add(new SpriteSheetImageName().getName());
        return customProperties;
    }

    /**
     * Validates whether a specific property exists and has a defined value.
     * @param properties the properties of the object from the enemies object layer.
     * @param propertyName the name of the property to validate.
     * @return true if the property exists and has a defined value, false otherwise.
     */
    private boolean validateProperty(MapProperties properties, String propertyName){
        PropertiesValidator presence = new PresencePropertiesValidator();
        PropertiesValidator notNull = new NotNullPropertiesValidator();
        presence.setNext(notNull);
        return presence.validate(properties, propertyName);
    }

    /**
     * Validates all custom enemy properties in the list.
     * Checks that each property exists and has a defined value.
     * @param properties the properties of the object from the enemies object layer.
     * @return true if all properties are present and valid, false otherwise.
     */
    public boolean validate(MapProperties properties){
        boolean result = true;
        for (String propertyName : customPropertyNames){
            result = result && validateProperty(properties, propertyName);
        }
        return result;
    }
}
