package edu.austral.starship.base.player.controls;

import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;

import java.util.HashMap;
import java.util.Map;

public class Controls {
    private Map<Integer, KeyFunctions> keys;

    public Controls(){
        this.keys = new HashMap<>();
    }

    public boolean hasKey(Integer key) {
        return keys.containsKey(key);
    }

    public void keyPressed(Integer key, Ship ship) {
        keys.get(key).execute(ship);
    }

    public void addKey(Integer key, KeyFunctions keyFunctions) {
        keys.put(key, keyFunctions);
    }
}
