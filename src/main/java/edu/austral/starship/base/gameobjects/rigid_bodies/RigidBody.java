package edu.austral.starship.base.gameobjects.rigid_bodies;

import edu.austral.starship.base.gameobjects.Drawable;
import edu.austral.starship.base.vector.Vector2;

public interface RigidBody extends Drawable {

    public float getAngularVelocity();

    public Vector2 getDirection();

    public Vector2 setNextDirection();

    public Vector2 getNextPosition();

    public void setNextPosition(Vector2 newPosition);

    public float getVelocity();
}