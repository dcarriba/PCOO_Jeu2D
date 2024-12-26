package com.game.model.entities;

import com.game.model.map.WorldMap;
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
        this.createMultipleEnemies();
    }

    /**
     * Creates an enemy at a specific tile position
     * @param tileX X coordinate of the tile where the enemy will be placed
     * @param tileY Y coordinate of the tile where the enemy will be placed
     */
    private void createEnemy(int tileX, int tileY) {
        SpriteSheet spriteSheet = new SpriteSheet("tilesets/characterstiles/orcs_1.png", 16, 16, 0, 3);
        Enemy enemy = new Enemy(tileX, tileY, spriteSheet, this.worldMap);
        this.enemies.add(enemy);
    }

    /**
     * Creates multiple enemies in predefined positions (for example, hardcoded or random)
     * This can be expanded to dynamically place enemies.
     */
    private void createMultipleEnemies() {
        createEnemy(44, 45);
        createEnemy(50, 53);
        createEnemy(55, 60);
        createEnemy(60, 63);
    }

    /**
     * Get all created enemies
     * @return List of all enemies created by the factory
     */
    public List<Enemy> getEnemies() {
        return enemies;
    }
}
