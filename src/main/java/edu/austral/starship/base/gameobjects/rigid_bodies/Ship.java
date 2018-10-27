package edu.austral.starship.base.gameobjects.rigid_bodies;

import edu.austral.starship.base.engines.Visitor;
import edu.austral.starship.base.gameobjects.rigid_bodies.weapon.Weapon;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Ship implements RigidBody, GameObjectCollisionable {

    private Shape shape;
    private float angularVelocity;
    private Vector2 direction;
    private Vector2 position;
    private float velocity;
    private boolean active;
    private int life;
    private boolean shootTriggered;
    private Weapon weapon;
    private int width;
    private int height;

    public Ship(Vector2 direction, Vector2 position,int life,
                Weapon weapon, int width, int height) {
        this.angularVelocity = 0;
        this.direction = direction;
        this.position = position;
        this.velocity = 0;
        this.life = life;
        this.weapon = weapon;
        this.active = true;
        this.shootTriggered = false;
        this.width = width;
        this.height = height;

        this.shape = new Rectangle2D.Float(position.getX(), position.getY(), width, height);
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public void collisionedWith(GameObjectCollisionable collisionable) {
        collisionable.collisionedWithShip(this);
    }

    @Override
    public void collisionedWithAsteroid(Asteroid asteroid) {
        life -= asteroid.getDamage();
        if(life <= 0) active = false;
    }

    @Override
    public void collisionedWithBullet(Bullet bullet) {
        life -= bullet.getDamage();
        if(life <= 0) active = false;
    }

    @Override
    public void collisionedWithShip(Ship ship) {
        // ignores collisions with another ship
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
    public void accepts(Visitor visitor) {
        visitor.acceptsShip(this);
    }

    public boolean isShootTriggered() {
        return shootTriggered;
    }

    public void shoot(boolean shootTriggered) {
        this.shootTriggered = shootTriggered;
    }

    public void turnLeft(float speed){
        angularVelocity -= angularVelocity * speed;
    }

    public void turnRight(float speed) {
        angularVelocity += angularVelocity * speed;
    }

    public void accelerate(float speed) {
        velocity += speed;
    }

    public void stop(float speed) {
        velocity -= speed;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
