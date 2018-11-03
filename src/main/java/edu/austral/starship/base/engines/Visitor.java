package edu.austral.starship.base.engines;

import edu.austral.starship.base.gameobjects.hue.Text;
import edu.austral.starship.base.gameobjects.animations.Explosion;
import edu.austral.starship.base.gameobjects.rigid_bodies.Asteroid;
import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;
import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;

public interface Visitor {
    void acceptsAsteroid(Asteroid asteroid);
    void acceptsBullet(Bullet bullet);
    void acceptsShip(Ship ship);
    void acceptsExplosion(Explosion explosion);
    void acceptsText(Text text);
}
