package edu.austral.starship.base.gameobjects.rigid_bodies.weapon;

import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;
import edu.austral.starship.base.vector.Vector2;

import java.util.Map;

public interface Weapon {
    Map<Bullet, Vector2> shoot();
}
