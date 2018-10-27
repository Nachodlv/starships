package edu.austral.starship.base.levels;

import edu.austral.starship.base.gameobjects.rigid_bodies.BulletType;
import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;
import edu.austral.starship.base.gameobjects.rigid_bodies.weapon.BulletFactoryImpl;
import edu.austral.starship.base.gameobjects.rigid_bodies.weapon.Weapon;
import edu.austral.starship.base.gameobjects.rigid_bodies.weapon.WeaponImpl;
import edu.austral.starship.base.player.Player;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

public class GameObjectFactory {

    static Ship createShip(Weapon weapon) {
        return new Ship(Vector2.vector(0, 1), Vector2.vector(100, 100),
                10, weapon, 50, 50);
    }

    static Weapon createWeapon(Player player) {
        Map<BulletType, Vector2> bullets = new HashMap<>();
        Shape bulletShape = new Rectangle2D.Float(10, 10, 10, 10);
        bullets.put(new BulletType(10, 10, Vector2.vector(1,0), 10, bulletShape), Vector2.vector(1, 0));

        BulletFactoryImpl bulletFactory = new BulletFactoryImpl(bullets, player);
        return new WeaponImpl(bulletFactory, 10);
    }
}
