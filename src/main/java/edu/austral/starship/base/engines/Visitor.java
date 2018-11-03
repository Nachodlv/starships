package edu.austral.starship.base.engines;

import edu.austral.starship.base.gameobjects.hue.Text;
import edu.austral.starship.base.gameobjects.animations.Explosion;
import edu.austral.starship.base.gameobjects.rigid_bodies.Asteroid;
import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;
import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;

public interface Visitor {
    default void acceptsAsteroid(Asteroid asteroid){}
    default void acceptsBullet(Bullet bullet){}
    void acceptsShip(Ship ship);
    default void acceptsExplosion(Explosion explosion){}
    default void acceptsText(Text text){}
}
