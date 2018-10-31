package edu.austral.starship.base.gameobjects.rigid_bodies;

import edu.austral.starship.base.engines.Visitor;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Random;

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

    public Asteroid(float angle, Vector2 position, Vector2 initialDirection, float velocity) {
        this.angle = angle;
        this.position = position;
        this.initialDirection = initialDirection;
        this.velocity = velocity;

        this.active = true;
        shape = new Rectangle2D.Float(0 - width/2F, 0 - height/2F, width, height);
        int size = new Random().nextInt(70) + 30;
        this.height = size;
        this.width = size;
        this.scoreValue = size;
        this.damage = size / 10;
        this.life = damage;
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
        Shape shape1 = new Rectangle2D.Float(0 - getWidth()/2F, 0 - getHeight()/2F, getWidth(), getHeight());
        AffineTransform tx = new AffineTransform();
        tx.translate(newPosition.getX() - width/2F, newPosition.getY() - height/2F);
        tx.rotate(angle);
        shape = tx.createTransformedShape(shape1);
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
