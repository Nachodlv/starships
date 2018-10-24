package edu.austral.starship.base.gameobjects.rigid_bodies;

import edu.austral.starship.base.collision.Collisionable;

public interface GameObjectCollisionable extends Collisionable<GameObjectCollisionable> {
    @Override
    void collisionedWith(GameObjectCollisionable collisionable);

    void collisionedWithAsteroid(Asteroid asteroid);

    void collisionedWithBullet(Bullet bullet);
}
