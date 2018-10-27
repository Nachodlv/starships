package edu.austral.starship.base.gameobjects.rigid_bodies;

import edu.austral.starship.base.engines.Visitor;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BulletType implements Bullet {
    private int damage;
    private float angle;
    private Vector2 initialDirection;
    private Vector2 position;
    private float velocity;
    private boolean active;
    private Shape shape;
    private int height;
    private int width;

    public BulletType(int damage, float velocity, int height, int width, Vector2 initialDirection,
                      float offSetAngle, Vector2 offSetPosition) {
        this.damage = damage;
        this.velocity = velocity;
        this.position = Vector2.vector(0, 0);
        this.initialDirection = initialDirection;
        this.active = true;
        this.height = height;
        this.width = width;
        this.shape = new Rectangle2D.Float();
        this.angle = offSetAngle;
        this.position = offSetPosition;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDirection(float angle) {
        this.angle = angle;
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

    @Override
    public void collisionedWithShip(Ship ship) {
        // ignores collisions with ship
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
