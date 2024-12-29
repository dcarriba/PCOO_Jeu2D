package com.game.model.memento;

import com.game.model.entities.Player;
import com.game.model.map.WorldMap;

import java.io.Serializable;

/** The <code>GameMemento</code> class stores the state of the game. */
public class GameMemento implements Serializable {
    /** The player */
    private final Player player;
    /** The world map */
    private final WorldMap worldMap;

    /**
     * Creates a memento with the current game state.
     * @param player The current player.
     * @param worldMap The current world map.
     */
    public GameMemento(Player player, WorldMap worldMap) {
        this.player = player;
        this.worldMap = worldMap;
    }

    public Player getPlayer() {
        return player;
    }

    public WorldMap getWorldMap() {
        return worldMap;
    }
}
