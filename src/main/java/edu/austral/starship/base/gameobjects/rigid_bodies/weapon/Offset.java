package edu.austral.starship.base.gameobjects.rigid_bodies.weapon;

import edu.austral.starship.base.vector.Vector2;

public class Offset {
    private float angle;
    private Vector2 position;

    public Offset(float angle, Vector2 position) {
        this.angle = angle;
        this.position = position;
    }

    public float getAngle() {
        return angle;
    }

    public Vector2 getPosition() {
        return position;
    }
}
