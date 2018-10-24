package edu.austral.starship.base.player.controls;

import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;

import java.util.Map;

public class Controls {
    private Map<Integer, KeyFunctions> keys;

    public Controls(Map<Integer, KeyFunctions> keys) {
        this.keys = keys;
    }

    public boolean hasKey(Integer key) {
        return keys.containsKey(key);
    }

    public void keyPressed(Integer key, Ship ship) {
        keys.get(key).execute(ship);
    }
}
