package edu.austral.starship.base.player;

import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;
import edu.austral.starship.base.player.controls.*;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Player {
    Controls controls;
    double score;
    Ship ship;

    public Player(Controls controls) {
        score = 0;
        this.controls = controls;
    }

    public Player() {
        score = 0;
        Map<Integer, KeyFunctions> keys = new HashMap<>();
        keys.put(KeyEvent.VK_UP, new Accelerate());
        keys.put(KeyEvent.VK_DOWN, new Stop());
        keys.put(KeyEvent.VK_LEFT, new TurnLeft());
        keys.put(KeyEvent.VK_RIGHT, new TurnRight());
        keys.put(KeyEvent.VK_SPACE, new Shoot());
        this.controls = new Controls(keys);
    }

    public boolean hasKey(Integer keyPressed) {
        return controls.hasKey(keyPressed);
    }

    public void keyPressed(Integer keyPressed) {
        controls.keyPressed(keyPressed, ship);
    }

    public void addScore(double scoreToAdd) {
        score += scoreToAdd;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
