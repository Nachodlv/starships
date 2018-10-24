package edu.austral.starship.base.gameobjects.rigid_bodies.weapon;

import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;
import edu.austral.starship.base.vector.Vector2;

import java.util.HashMap;
import java.util.Map;

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
    public Map<Bullet, Vector2> shoot() {
        if (lastShoot >= cadency) {
            lastShoot = 0;
            return bulletFactory.createBullets();
        }else {
            lastShoot++;
            return new HashMap<>();
        }
    }
}
