package edu.austral.starship.base.gameobjects.rigid_bodies;

import edu.austral.starship.base.collision.Collisionable;

public interface GameObjectCollisionable extends Collisionable<GameObjectCollisionable> {
    @Override
    void collisionedWith(GameObjectCollisionable collisionable);

    default void collisionedWithAsteroid(Asteroid asteroid){}

    default void collisionedWithBullet(Bullet bullet){}

    default void collisionedWithShip(Ship ship){}
}
