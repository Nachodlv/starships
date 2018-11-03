package edu.austral.starship.base.player;

import edu.austral.starship.base.gameobjects.hue.Valuable;
import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;
import edu.austral.starship.base.player.controls.Controls;
import edu.austral.starship.base.player.controls.KeyFunctions;

public class Player implements Valuable {
    private Controls controls;
    private double score;
    private Ship ship;
    private PlayerNumber playerNumber;

    public Player(Controls controls, PlayerNumber playerNumber) {
        score = 0;
        this.playerNumber = playerNumber;
        this.controls = controls;
    }

    public Player(PlayerNumber playerNumber) {
        score = 0;
        this.playerNumber = playerNumber;
        this.controls = new Controls();
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

    public Ship getShip() {
        return ship;
    }

    public PlayerNumber getPlayerNumber() {
        return playerNumber;
    }

    @Override
    public String getValue() {
        return String.valueOf(score);
    }

    public double getScore() {
        return score;
    }

    public void resetScore() {
        score = 0;
    }

    public void addKeyToController(Integer key, KeyFunctions keyFunctions) {
        controls.addKey(key, keyFunctions);
    }
}
