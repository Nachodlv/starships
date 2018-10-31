package edu.austral.starship.base.gameobjects.rigid_bodies.weapon;

public class BulletType{
    private int damage;
    private float velocity;
    private int height;
    private int width;

    public BulletType(int damage, float velocity, int height, int width) {
        this.damage = damage;
        this.velocity = velocity;
        this.height = height;
        this.width = width;
    }

    public int getDamage() {
        return damage;
    }

    public float getVelocity() {
        return velocity;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
