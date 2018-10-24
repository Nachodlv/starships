package edu.austral.starship.base.gameobjects.rigid_bodies;

import edu.austral.starship.base.engines.Visitor;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;

public class BulletType implements Bullet {
    private int damage;
    private float angularVelocity;
    private Vector2 direction;
    private Vector2 position;
    private float velocity;
    private boolean active;
    private Shape shape;

    public BulletType(int damage, float angularVelocity, Vector2 direction, Vector2 position, float velocity, Shape shape) {
        this.damage = damage;
        this.angularVelocity = angularVelocity;
        this.direction = direction;
        this.position = position;
        this.velocity = velocity;
        this.shape = shape;

        this.active = true;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public float getAngularVelocity() {
        return angularVelocity;
    }

    @Override
    public Vector2 getDirection() {
        return direction;
    }

    @Override
    public Vector2 setNextDirection() {
        direction = direction.rotate(angularVelocity);
        return direction;
    }

    @Override
    public Vector2 getNextPosition() {
        return position.add(direction.multiply(velocity));
    }

    @Override
    public float getVelocity() {
        return velocity;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void accepts(Visitor visitor) {
        visitor.acceptsBullet(this);
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public void collisionedWith(GameObjectCollisionable collisionable) {
        collisionable.collisionedWithBullet(this);
    }

    @Override
    public void collisionedWithAsteroid(Asteroid asteroid) {
        // ignores collision with asteroid
    }

    @Override
    public void collisionedWithBullet(Bullet bullet) {
        // ignores collision with bullet
    }
}
