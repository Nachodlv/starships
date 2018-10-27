package edu.austral.starship.base.gameobjects.rigid_bodies;

import edu.austral.starship.base.engines.Visitor;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;

public class Asteroid implements RigidBody, GameObjectCollisionable{

    private boolean active;
    private float angle;
    private int damage;
    private Vector2 direction;
    private Vector2 initialPosition;
    private int scoreValue;
    private Shape shape;
    private float size;
    private float velocity;
    private int life;
    private int height;
    private int width;

    public Asteroid(float angle, int damage, Vector2 direction, Vector2 initialPosition, int scoreValue,
                    float size, float velocity, Shape shape, int life) {
        this.angle = angle;
        this.damage = damage;
        this.direction = direction;
        this.initialPosition = initialPosition;
        this.scoreValue = scoreValue;
        this.size = size;
        this.velocity = velocity;
        this.active = true;
        this.shape = shape;
        this.life = life;
    }

    @Override
    public float getAngle() {
        return angle;
    }

    @Override
    public Vector2 getDirection() {
        return initialPosition;
    }

    @Override
    public Vector2 getNextPosition() {
        return initialPosition.add(direction.multiply(velocity));
    }

    @Override
    public void setNextPosition(Vector2 newPosition) {
        initialPosition = newPosition;
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
        return initialPosition;
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

    @Override
    public void collisionedWithShip(Ship ship) {
        active = false;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
