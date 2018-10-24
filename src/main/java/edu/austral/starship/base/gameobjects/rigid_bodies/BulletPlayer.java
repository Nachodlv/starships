package edu.austral.starship.base.gameobjects.rigid_bodies;

import edu.austral.starship.base.engines.Visitor;
import edu.austral.starship.base.player.Player;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;

public class BulletPlayer implements Bullet{
    private BulletType bullet;
    private Player player;
    private boolean active;

    public BulletPlayer(BulletType bullet, Player player) {
        this.bullet = bullet;
        this.player = player;
        this.active = true;
    }

    @Override
    public int getDamage() {
        return bullet.getDamage();
    }

    @Override
    public Shape getShape() {
        return bullet.getShape();
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
        active = false;
    }

    @Override
    public float getAngularVelocity() {
        return bullet.getAngularVelocity();
    }

    @Override
    public Vector2 getDirection() {
        return bullet.getDirection();
    }

    @Override
    public Vector2 setNextDirection() {
        return bullet.setNextDirection();
    }

    @Override
    public Vector2 getNextPosition() {
        return bullet.getNextPosition();
    }

    @Override
    public void setNextPosition(Vector2 newPosition) {
        bullet.setNextPosition(newPosition);
    }

    @Override
    public float getVelocity() {
        return bullet.getVelocity();
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public Vector2 getPosition() {
        return bullet.getPosition();
    }

    @Override
    public void accepts(Visitor visitor) {
        visitor.acceptsBullet(this);
    }
}
