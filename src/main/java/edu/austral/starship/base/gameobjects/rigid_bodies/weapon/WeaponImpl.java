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
        this.lastShoot = 0;
    }

    @Override
    public List<Bullet> shoot() {
        if (lastShoot >= cadency) {
            lastShoot = 0;
            return bulletFactory.createBullets();
        }else {
            lastShoot++;
            return new ArrayList<>();
        }
    }
}
