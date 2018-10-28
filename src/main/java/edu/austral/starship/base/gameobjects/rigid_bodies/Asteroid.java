package edu.austral.starship.base.gameobjects.rigid_bodies;

import edu.austral.starship.base.engines.Visitor;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Asteroid implements RigidBody, GameObjectCollisionable{

    private boolean active;
    private float angle;
    private int damage;
    private Vector2 position;
    private Vector2 initialDirection;
    private int scoreValue;
    private Shape shape;
    private float velocity;
    private int life;
    private int height;
    private int width;

    public Asteroid(float angle, int damage, Vector2 position, Vector2 initialDirection, int scoreValue,
                    int size, float velocity, int life) {
        this.angle = angle;
        this.damage = damage;
        this.position = position;
        this.initialDirection = initialDirection;
        this.scoreValue = scoreValue;
        this.velocity = velocity;
        this.active = true;
        this.height = size;
        this.width = size;
        this.shape = new Rectangle2D.Float(position.getX(), position.getY(), width, height);
        this.life = life;
    }

    @Override
    public float getAngle() {
        return angle;
    }

    @Override
    public Vector2 getDirection() {
        return initialDirection.rotate(angle);
    }

    @Override
    public Vector2 getNextPosition() {
        return position.add(getDirection().multiply(velocity));
    }

    @Override
    public void setNextPosition(Vector2 newPosition) {
        shape = new Rectangle2D.Float(newPosition.getX(), newPosition.getY(), width, height);
        position = newPosition;
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
    public void setActive(boolean newStatus) {
        active = newStatus;
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
