package edu.austral.starship.base.player.controls;

import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;

public class TurnRight implements KeyFunctions {
    @Override
    public void execute(Ship ship) {
        ship.turnRight();
    }
}
