package edu.austral.starship.base.levels.GameObjectFactory;

import edu.austral.starship.base.gameobjects.rigid_bodies.weapon.BulletPlayerFactory;
import edu.austral.starship.base.gameobjects.rigid_bodies.weapon.BulletType;
import edu.austral.starship.base.gameobjects.rigid_bodies.weapon.Offset;
import edu.austral.starship.base.vector.Vector2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WeaponFactory {

    public static BulletPlayerFactory superShootWeapon() {
        Map<Offset, BulletType> bullets = new HashMap<>();
        BulletType bulletType = new BulletType(10, 3, 50, 50);

        bullets.put(new Offset(0, Vector2.vector(0, 0)), bulletType);

        return new BulletPlayerFactory(bullets);
    }


    public static BulletPlayerFactory fastWeapon() {
        Map<Offset, BulletType> bullets = new HashMap<>();
        BulletType bulletType = new BulletType(1, 6, 10, 10);

        bullets.put(new Offset(0, Vector2.vector(0, 0)), bulletType);
        bullets.put(new Offset(0.5F, Vector2.vector(15, 0)), bulletType);
        bullets.put(new Offset((float)Math.PI * 2 - 0.5F, Vector2.vector(-15, 0)), bulletType);

        return new BulletPlayerFactory(bullets);
    }

    public static BulletPlayerFactory randomWeapon() {
        Map<Offset, BulletType> bullets = new HashMap<>();
        Random random = new Random();
        int bulletQuantity = random.nextInt(10) + 1;
        BulletType bulletType = new BulletType(random.nextInt(5) + 1, random.nextInt(10), random.nextInt(50), random.nextInt(50));

        for (int i = 0; i < bulletQuantity; i++) {
            bullets.put(new Offset(random.nextInt(5), Vector2.vector(random.nextInt(30) - 15, random.nextInt(30) - 15)), bulletType);
        }

        return new BulletPlayerFactory(bullets);
    }
}
