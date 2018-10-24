package edu.austral.starship.base.gameobjects.rigid_bodies;

public interface Bullet extends RigidBody, GameObjectCollisionable{
    int getDamage();
}
