package com.game.graphics;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/** The <code>WorldMap</code> class represents a Tiled Map and its attributes (notably its renderer) */
public class WorldMap {
    /** Width of one tile in the map */
    private final float tileWidth;
    /** Height of one tile in the map */
    private final float tileHeight;
    /** Path to the Tiled Map */
    private final String worldMapPath;
    /** Renderer for the Tiled Map */
    private final OrthogonalTiledMapRenderer renderer;

    /**
     * Constructor to create a Worldmap
     * @param worldMapPath path to the TiledMap
     * @param tileHeight height of one tile
     * @param tileWidth width of one tile
     */
    public WorldMap(String worldMapPath, float tileHeight, float tileWidth) {
        this.worldMapPath = worldMapPath;
        TmxMapLoader maploader = new TmxMapLoader();
        TiledMap map = maploader.load(this.worldMapPath);
        this.renderer = new OrthogonalTiledMapRenderer(map);
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

    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }

    public void dispose(){
        renderer.dispose();
    }
}
