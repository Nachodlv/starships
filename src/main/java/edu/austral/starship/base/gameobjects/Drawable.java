package edu.austral.starship.base.gameobjects;

import edu.austral.starship.base.vector.Vector2;

public interface Drawable extends GameObject{
    boolean isActive();
    Vector2 getPosition();
    void setActive(boolean newStatus);
}
