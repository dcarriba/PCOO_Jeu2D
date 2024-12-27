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

public class ValidateCustomPropertiesTeleport {
    private final List<String> customPropertyNames = createCustomPropertyNamesList();

    public ValidateCustomPropertiesTeleport() {
    }

    private List<String> createCustomPropertyNamesList(){
        List<String> customProperties = new ArrayList<>();
        customProperties.add(new NextWorldMapNameProperty().getName());
        customProperties.add(new NextWorldMapTileXProperty().getName());
        customProperties.add(new NextWorldMapTileYProperty().getName());
        customProperties.add(new WhenKeyPressedProperty().getName());
        return customProperties;
    }

    /**
     * Checks if a property exist and has a defined value amongst the properties of the object from
     * the teleport object layer.
     * @param properties the properties of the object from the teleport object layer
     * @param propertyName the name of the property
     * @return  if property given as an argument (propertyName) is present and has a defined value
     *          amongst the properties of the object from the teleport object layer.
     */
    private boolean validateProperty(MapProperties properties, String propertyName){
        PropertiesValidator presence = new PresencePropertiesValidator();
        PropertiesValidator notNull = new NotNullPropertiesValidator();
        presence.setNext(notNull);
        return presence.validate(properties, propertyName);
    }

    /**
     * Checks if all properties in the customPropertyNames List exist and have a defined value amongst the properties of
     * the object from the teleport object layer.
     * @param properties the properties of the object from the teleport object layer
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
