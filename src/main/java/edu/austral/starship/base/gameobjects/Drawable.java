package edu.austral.starship.base.gameobjects;

import edu.austral.starship.base.vector.Vector2;

public interface Drawable extends GameObject{
    public boolean isActive();
    public Vector2 getPosition();
}
