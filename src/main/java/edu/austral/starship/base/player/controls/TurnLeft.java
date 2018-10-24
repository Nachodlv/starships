package edu.austral.starship.base.player.controls;

import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;
import edu.austral.starship.base.player.controls.KeyFunctions;

public class TurnLeft implements KeyFunctions {

    @Override
    public void execute(Ship ship) {
        ship.turnLeft(1);
    }
}
