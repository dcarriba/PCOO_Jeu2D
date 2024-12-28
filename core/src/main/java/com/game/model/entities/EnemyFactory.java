package com.game.model.entities;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.game.model.map.WorldMap;
import com.game.model.map.layers.enemies.properties.validator.ValidateCustomPropertiesEnemies;

import java.util.ArrayList;
import java.util.List;

public class EnemyFactory {
    /** List to store all created enemies */
    private final List<Enemy> enemies;
    /** Map where the enemies are placed */
    private final WorldMap worldMap;

    /**
     * Constructor to initialize the EnemyFactory
     * @param worldMap The WorldMap where the enemies will be created on
     */
    public EnemyFactory(WorldMap worldMap) {
        this.worldMap = worldMap;
        this.enemies = new ArrayList<>();
        this.createEnemies();
    }

    /**
     * Creates an enemy at a specific tile position
     * @param tileX X coordinate of the tile where the enemy will be placed
     * @param tileY Y coordinate of the tile where the enemy will be placed
     */
    private void createEnemy(int tileX, int tileY, String spriteSheetPath) {
        SpriteSheet spriteSheet = new SpriteSheet(spriteSheetPath, 16, 16, 0, 3);
        Enemy enemy = new Enemy(tileX, tileY, spriteSheet, this.worldMap);
        this.enemies.add(enemy);
    }

    /** Creates the enemies based on the given locations in the "enemies" object layer of the TiledMap */
    private void createEnemies(){
        MapLayer enemiesLayer = worldMap.getTiledMap().getLayers().get("enemies");  // Object layer for teleporters
        if (enemiesLayer != null) {
            // Iterate over all objects in the teleport layer
            for (MapObject object : enemiesLayer.getObjects()) {
                // Check if the object is a rectangle
                if (object instanceof RectangleMapObject) {
                    RectangleMapObject rectangleObject = (RectangleMapObject) object;
                    Rectangle rect = rectangleObject.getRectangle();
                    MapProperties properties = rectangleObject.getProperties();
                    // Check if all the required custom properties exist and have a defined value
                    if (new ValidateCustomPropertiesEnemies().validate(properties)){
                    createEnemy(
                        (int) (rect.getX()/worldMap.getTileWidth()),
                        (int) ((-1)*(rect.getY()/worldMap.getTileHeight() - this.worldMap.getTiledMap().getProperties().get("height", Integer.class) + 1)),
                        "tilesets/characterstiles/" + properties.get("spriteSheetImageName")
                    );
                    }
                }
            }
        }
    }

    /**
     * Get all created enemies
     * @return List of all enemies created by the factory
     */
    public List<Enemy> getEnemies() {
        return enemies;
    }
}
