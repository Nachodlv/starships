package edu.austral.starship.base.gameobjects.rigid_bodies;

import edu.austral.starship.base.Color;
import edu.austral.starship.base.engines.Visitor;
import edu.austral.starship.base.gameobjects.HUE.Valuable;
import edu.austral.starship.base.gameobjects.rigid_bodies.weapon.Weapon;
import edu.austral.starship.base.vector.Vector2;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Ship implements RigidBody, GameObjectCollisionable, Valuable {

    private Shape shape;
    private float angle;
    private Vector2 initialDirection;
    private Vector2 position;
    private float velocity;
    private boolean active;
    private int life;
    private boolean shootTriggered;
    private Weapon weapon;
    private int width;
    private int height;
    private float speed;
    private float maxVelocity;
    private Color color;

    public Ship(Vector2 initialDirection, Vector2 position, int life, Weapon weapon, int width, int height,
                float speed, float maxVelocity, Color color) {
        this.angle = 0;
        this.initialDirection = initialDirection;
        this.position = position;
        this.velocity = 0;
        this.life = life;
        this.weapon = weapon;
        this.active = true;
        this.shootTriggered = false;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.maxVelocity = maxVelocity;
        this.color = color;

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
        setLife(life - asteroid.getDamage());
    }

    @Override
    public void collisionedWithBullet(Bullet bullet) {

    }

    @Override
    public void collisionedWithShip(Ship ship) {
        // ignores collisions with another ship
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
        Shape newShape = new Rectangle2D.Float(0 - width/2F, 0 - height/2F, width, height);
        AffineTransform tx = new AffineTransform();
        tx.translate(newPosition.getX() - width/2F, newPosition.getY() - height/2F);
        tx.rotate(angle);
        shape = tx.createTransformedShape(newShape);
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
        visitor.acceptsShip(this);
    }

    public boolean isShootTriggered() {
        return shootTriggered;
    }

    public void shoot(boolean shootTriggered) {
        this.shootTriggered = shootTriggered;
    }

    public void turnLeft(){
        angle -= speed;
        if(angle <= 0) angle = (float) (2 * Math.PI);
    }

    public void turnRight() {
        angle += speed;
        if(angle >= 2 * Math.PI) angle = 0;
    }

    public void accelerate() {
        velocity += speed;
        if(velocity >= maxVelocity) velocity = maxVelocity;
    }

    public void stop() {
        velocity -= speed;
        if(velocity <= 0) velocity = 0;
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

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public Vector2 getInitialDirection() {
        return initialDirection;
    }

    public void setLife(int life) {
        this.life = life;
        if(life <= 0) {
            this.life = 0;
            active = false;
        }
    }

    public int getLife() {
        return life;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String getValue() {
        return String.valueOf(life);
    }
}
