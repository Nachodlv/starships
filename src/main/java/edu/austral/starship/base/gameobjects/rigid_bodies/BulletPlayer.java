package edu.austral.starship.base.gameobjects.rigid_bodies;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.engines.Visitor;
import edu.austral.starship.base.gameobjects.rigid_bodies.weapon.BulletType;
import edu.austral.starship.base.player.Player;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class BulletPlayer implements Bullet{
    private BulletType bullet;
    private Player player;
    private boolean active;
    private float angle;
    private Vector2 position;
    private Shape shape;
    private float velocity;
    private Vector2 initialDirection;


    public BulletPlayer(BulletType bullet, Player player, Vector2 initialDirection, float angle, Vector2 position) {
        this.bullet = bullet;
        this.player = player;
        this.active = true;
        this.velocity = bullet.getVelocity();
        this.angle = angle;
        this.position = position;
        this.shape = new Rectangle2D.Float();
        this.initialDirection = initialDirection;
    }

    @Override
    public int getDamage() {
        return bullet.getDamage();
    }

    @Override
    public void setDirection(float angle) {
        this.angle = angle;
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
        int life = asteroid.getLife() - getDamage();
        asteroid.setLife(life);
        if(life <= 0) player.addScore(asteroid.getScoreValue());
        active = false;
    }

    @Override
    public void collisionedWithBullet(Bullet bullet) {
        // ignores the collisions with other bullets
    }

    @Override
    public void collisionedWithShip(Ship ship) {
        if(!ship.equals(player.getShip())) {
            active = false;
            int life = ship.getLife() - getDamage();
            ship.setLife(life);
            if(life <= 0) player.addScore(CustomGameFramework.SCORE_KILL_SHIP);
        }
    }

    @Override
    public int getWidth() {
        return bullet.getWidth();
    }

    @Override
    public int getHeight() {
        return bullet.getHeight();
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
        Shape newShape = new Rectangle2D.Float(0 - getWidth()/2F, 0 - getHeight()/2F, getWidth(), getHeight());
        AffineTransform tx = new AffineTransform();
        tx.translate(newPosition.getX() - getWidth()/2F, newPosition.getY() - getHeight()/2F);
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
        visitor.acceptsBullet(this);
    }
}
