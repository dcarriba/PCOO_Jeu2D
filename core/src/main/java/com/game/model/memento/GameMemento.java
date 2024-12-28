package com.game.model.memento;

import com.game.model.entities.Player;
import com.game.model.map.WorldMap;

import java.io.Serializable;

/** The <code>GameMemento</code> class stores the state of the game. */
 public class GameMemento implements Serializable {
    private final Player player;
    private final WorldMap worldMap;

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
