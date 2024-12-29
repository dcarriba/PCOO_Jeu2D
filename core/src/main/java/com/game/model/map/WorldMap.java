package com.game.model.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.game.model.entities.Enemy;
import com.game.model.entities.EnemyFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** The <code>WorldMap</code> class represents the world map having a tiled map, its renderer, etc. as attributes */
public class WorldMap implements Serializable {
    /** Width of one tile in the tiledMap */
    private float tileWidth;
    /** Height of one tile in the tiledMap */
    private float tileHeight;
    /** Path to the Tiled Map */
    private String tiledMapPath;
    /** The TiledMap */
    private transient TiledMap tiledMap;
    /** Renderer for the Tiled Map */
    private transient OrthogonalTiledMapRenderer renderer;
    /** The enemies present on the WorldMap */
    private List<Enemy> enemies;
    /** Contains all killed enemies */
    private final Set<String> killedEnemies;

    /**
     * Constructor to create a Worldmap
     * @param tiledMapPath path to the TiledMap
     * @param tileHeight height of one tile
     * @param tileWidth width of one tile
     */
    public WorldMap(String tiledMapPath, float tileHeight, float tileWidth) {
        this.tiledMapPath = tiledMapPath;
        TmxMapLoader maploader = new TmxMapLoader();
        this.tiledMap = maploader.load(this.tiledMapPath);
        this.renderer = new OrthogonalTiledMapRenderer(this.tiledMap);
        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;
        this.enemies = new EnemyFactory(this).getEnemies();
        this.killedEnemies = new HashSet<>();
    }

    public float getTileWidth() {
        return tileWidth;
    }

    public float getTileHeight() {
        return tileHeight;
    }

    public String getTiledMapPath() {
        return tiledMapPath;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Get a specific enemy based on tile position
     * @param tileX X coordinate of the tile where the enemy is located
     * @param tileY Y coordinate of the tile where the enemy is located
     * @return The enemy located at the given tile coordinates, or null if no enemy is found
     */
    public Enemy getEnemy(int tileX, int tileY) {
        for (Enemy enemy : enemies) {
            if (enemy.getTileX() == tileX && enemy.getTileY() == tileY) {
                return enemy;  // Return the enemy found at the specified tile coordinates
            }
        }
        return null;  // Return null if no enemy is found at the given coordinates
    }

    public Set<String> getKilledEnemies() {
        return killedEnemies;
    }

    /**
     * Marks an enemy as killed and removes it from the WorldMap's enemy list.
     * Also records the enemy's position in the killedEnemies set.
     * @param enemy The enemy to be killed
     */
    public void killEnemy(Enemy enemy){
        enemies.remove(enemy);
        killedEnemies.add(tiledMapPath + enemy.getTileX() + "," + enemy.getTileY());
        enemy.dispose();
    }

    /**
     * Checks if an enemy has already been killed
     * @param enemy The enemy to check
     * @return true if the enemy has been killed, false otherwise
     */
    public boolean hasEnemyBeenKilled(Enemy enemy) {
        return killedEnemies.contains(tiledMapPath + enemy.getTileX() + "," + enemy.getTileY());
    }

    /**
     * Checks if a tile isn't blocked (i.e. it is not an obstacle or enemy)
     * @param tileX the X coordinate of the tile on the TiledMap
     * @param tileY the Y coordinate of the tile on the TiledMap
     * @return true if the tile isn't blocked (i.e. it is not an obstacle or enemy)
     */
    public boolean isTileNotBlocked(int tileX, int tileY){
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get("obstacles");
        if (layer != null){
            TiledMapTileLayer.Cell cell = layer.getCell(tileX, tiledMap.getProperties().get("height", Integer.class)-1-tileY);
            // If the tile is null, the tile is not occupied, and thus not blocked
            return (cell == null || cell.getTile() == null) && !isTileBlockedByEnemy(tileX, tileY);
        }
        return !isTileBlockedByEnemy(tileX, tileY);
    }

    /**
     * Checks if a tile is blocked by an enemy.
     * @param tileX the X coordinate of the tile on the TiledMap
     * @param tileY the Y coordinate of the tile on the TiledMap
     * @return true if an enemy is occupying the tile, false otherwise
     */
    public boolean isTileBlockedByEnemy(int tileX, int tileY) {
        // Check if any enemy is at the given tile coordinates
        for (Enemy enemy : this.enemies) {
            if (enemy.getTileX() == tileX && enemy.getTileY() == tileY) {
                return true; // This tile is occupied by an enemy
            }
        }
        return false; // Tile is not blocked by any enemy
    }

    /**
     * Updates the WorldMap by disposing the previous TiledMap and loading the new one.
     * @param tiledMapPath the path to the new TiledMap
     * @param tileHeight height of one tile in the new map
     * @param tileWidth width of one tile in the new map
     */
    public void updateWorldMap(String tiledMapPath, float tileHeight, float tileWidth){
        this.dispose();
        this.tiledMapPath = tiledMapPath;
        TmxMapLoader maploader = new TmxMapLoader();
        this.tiledMap = maploader.load(this.tiledMapPath);
        this.renderer = new OrthogonalTiledMapRenderer(this.tiledMap);
        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;
        this.enemies = new EnemyFactory(this).getEnemies();
        this.enemies.removeIf(this::hasEnemyBeenKilled); // Removes any previously killed enemies
    }

    /**
     * Custom deserialization to initialize transient fields after the object is deserialized
     * @param ois The ObjectInputStream
     */
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();  // Deserialize the non-transient fields
        TmxMapLoader maploader = new TmxMapLoader();
        this.tiledMap = maploader.load(this.tiledMapPath); // Reinitialize the TiledMap
        this.renderer = new OrthogonalTiledMapRenderer(this.tiledMap); // Reinitialize the renderer
    }

    public void dispose(){
        if (tiledMap != null) tiledMap.dispose();
        if (renderer != null) renderer.dispose();
        if (enemies != null) for (Enemy enemy : enemies) enemy.dispose();
    }
}
