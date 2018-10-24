package edu.austral.starship.base.engines;

import edu.austral.starship.base.gameobjects.rigid_bodies.Asteroid;
import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;

public interface Visitor {
    public void acceptsAsteroid(Asteroid asteroid);
    public void acceptsBullet(Bullet bullet);
}
