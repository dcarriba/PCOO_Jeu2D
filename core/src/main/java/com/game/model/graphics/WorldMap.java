package com.game.model.graphics;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/** The <code>WorldMap</code> class represents a Tiled Map and its attributes (notably its renderer) */
public class WorldMap {
    /** Width of one tile in the tiledMap */
    private float tileWidth;
    /** Height of one tile in the tiledMap */
    private float tileHeight;
    /** Path to the Tiled Map */
    private String worldMapPath;
    /** The TiledMap */
    private TiledMap tiledMap;
    /** Renderer for the Tiled Map */
    private OrthogonalTiledMapRenderer renderer;

    /**
     * Constructor to create a Worldmap
     * @param worldMapPath path to the TiledMap
     * @param tileHeight height of one tile
     * @param tileWidth width of one tile
     */
    public WorldMap(String worldMapPath, float tileHeight, float tileWidth) {
        this.worldMapPath = worldMapPath;
        TmxMapLoader maploader = new TmxMapLoader();
        this.tiledMap = maploader.load(this.worldMapPath);
        this.renderer = new OrthogonalTiledMapRenderer(this.tiledMap);
        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;
    }

    public float getTileWidth() {
        return tileWidth;
    }

    public float getTileHeight() {
        return tileHeight;
    }

    public String getWorldMapPath() {
        return worldMapPath;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }

    /**
     * Used to check if a tile isn't blocked (i.e. it is not an obstacle and an entity can walk on it)
     * @param tileX the X coordinate of the tile on the TiledMap
     * @param tileY the Y coordinate of the tile on the TiledMap
     * @return true if the tile isn't blocked (i.e. it is not an obstacle and an entity can walk on it)
     */
    public boolean isTileNotBlocked(int tileX, int tileY){
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get("obstacles");
        if (layer != null){
            TiledMapTileLayer.Cell cell = layer.getCell(tileX, tiledMap.getProperties().get("height", Integer.class)-1-tileY);
            // If the tile is null, the tile is not occupied, and thus not blocked
            return cell == null || cell.getTile() == null;
        }
        return true;
    }

    /**
     * Updates the WorldMap <br>
     * Disposes the previous TiledMap and loads the new one.
     * @param worldMapPath path to the TiledMap
     * @param tileHeight height of one tile
     * @param tileWidth width of one tile
     */
    public void updateWorldMap(String worldMapPath, float tileHeight, float tileWidth){
        this.dispose();
        this.worldMapPath = worldMapPath;
        TmxMapLoader maploader = new TmxMapLoader();
        this.tiledMap = maploader.load(this.worldMapPath);
        this.renderer = new OrthogonalTiledMapRenderer(this.tiledMap);
        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;
    }

    public void dispose(){
        tiledMap.dispose();
        renderer.dispose();
    }
}
