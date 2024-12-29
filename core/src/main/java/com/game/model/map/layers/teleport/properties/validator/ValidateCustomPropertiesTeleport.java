package com.game.model.map.layers.teleport.properties.validator;

import com.badlogic.gdx.maps.MapProperties;
import com.game.model.map.layers.teleport.properties.custom.NextWorldMapNameProperty;
import com.game.model.map.layers.teleport.properties.custom.NextWorldMapTileXProperty;
import com.game.model.map.layers.teleport.properties.custom.NextWorldMapTileYProperty;
import com.game.model.map.layers.teleport.properties.custom.WhenKeyPressedProperty;
import com.game.model.map.validator.properties.NotNullPropertiesValidator;
import com.game.model.map.validator.properties.PresencePropertiesValidator;
import com.game.model.map.validator.properties.PropertiesValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * The <code>ValidateCustomPropertiesTeleport</code> class validates the custom properties of a teleport object
 * from the tiledmap's teleport object layer
 */
public class ValidateCustomPropertiesTeleport {
    /** List of custom property names to validate. */
    private final List<String> customPropertyNames = createCustomPropertyNamesList();

    public ValidateCustomPropertiesTeleport() {
    }

    /**
     * Creates the list of the custom property names of a teleport object from the tiledmap's teleport object layer .
     * @return a list of custom property names.
     */
    private List<String> createCustomPropertyNamesList(){
        List<String> customProperties = new ArrayList<>();
        customProperties.add(new NextWorldMapNameProperty().getName());
        customProperties.add(new NextWorldMapTileXProperty().getName());
        customProperties.add(new NextWorldMapTileYProperty().getName());
        customProperties.add(new WhenKeyPressedProperty().getName());
        return customProperties;
    }

    /**
     * Validates whether a specific property exists and has a defined value
     * @param properties the properties of the object from the teleport object layer
     * @param propertyName the name of the property to validate.
     * @return  true if the property exists and has a defined value, false otherwise
     */
    private boolean validateProperty(MapProperties properties, String propertyName){
        PropertiesValidator presence = new PresencePropertiesValidator();
        PropertiesValidator notNull = new NotNullPropertiesValidator();
        presence.setNext(notNull);
        return presence.validate(properties, propertyName);
    }

    /**
     * Validates all custom teleport properties in the list.
     * Checks that each property exists and has a defined value.
     * @param properties the properties of the object from the teleport object layer
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
