package edu.austral.starship.base.gameobjects.rigid_bodies;

import edu.austral.starship.base.engines.Visitor;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;

public class Asteroid implements RigidBody, GameObjectCollisionable{

    private boolean active;
    private float angularVelocity;
    private int damage;
    private Vector2 direction;
    private Vector2 position;
    private int scoreValue;
    private Shape shape;
    private float size;
    private float velocity;
    private int life;

    public Asteroid(float angularVelocity, int damage, Vector2 direction, Vector2 position, int scoreValue,
                    float size, float velocity, Shape shape, int life) {
        this.angularVelocity = angularVelocity;
        this.damage = damage;
        this.direction = direction;
        this.position = position;
        this.scoreValue = scoreValue;
        this.size = size;
        this.velocity = velocity;
        this.active = true;
        this.shape = shape;
        this.life = life;
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
        visitor.acceptsAsteroid(this);
    }

    public int getDamage() {
        return damage;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public Shape getShape() {
        return shape;
    }

    public float getSize() {
        return size;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
        if(life <= 0) active = false;
    }

    @Override
    public void collisionedWith(GameObjectCollisionable collisionable) {
        collisionable.collisionedWithAsteroid(this);
    }

    @Override
    public void collisionedWithAsteroid(Asteroid asteroid) {
      // Ignores the collisions with other asteroids
    }

    @Override
    public void collisionedWithBullet(Bullet bullet) {
        // Ignores the collisions with bullets
    }
}
