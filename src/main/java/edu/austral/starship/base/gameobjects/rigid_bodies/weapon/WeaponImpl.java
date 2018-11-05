package edu.austral.starship.base.gameobjects.rigid_bodies.weapon;

import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WeaponImpl implements Weapon{
    private BulletFactory bulletFactory;
    private int cadency;
    private int lastShoot;

    public WeaponImpl(BulletFactory bulletFactory, int cadency) {
        this.bulletFactory = bulletFactory;
        this.cadency = cadency;
        this.lastShoot = cadency;
    }

    @Override
    public List<Bullet> shoot() {
        return bulletFactory.createBullets();
    }

    @Override
    public boolean canShoot() {
        if (lastShoot >= cadency) {
            lastShoot = 0;
            return true;
        }else {
            lastShoot++;
            return false;
        }
    }
}
