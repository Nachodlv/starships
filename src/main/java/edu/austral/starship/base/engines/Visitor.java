package edu.austral.starship.base.engines;

import edu.austral.starship.base.gameobjects.HUE.Text;
import edu.austral.starship.base.gameobjects.animations.Explosion;
import edu.austral.starship.base.gameobjects.rigid_bodies.Asteroid;
import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;
import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;

public interface Visitor {
    public void acceptsAsteroid(Asteroid asteroid);
    public void acceptsBullet(Bullet bullet);
    public void acceptsShip(Ship ship);
    public void acceptsExplosion(Explosion explosion);
    public void acceptsText(Text text);
}
