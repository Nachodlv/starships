package edu.austral.starship.base.levels;

import edu.austral.starship.base.gameobjects.rigid_bodies.BulletType;
import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;
import edu.austral.starship.base.gameobjects.rigid_bodies.weapon.BulletFactoryImpl;
import edu.austral.starship.base.gameobjects.rigid_bodies.weapon.Weapon;
import edu.austral.starship.base.gameobjects.rigid_bodies.weapon.WeaponImpl;
import edu.austral.starship.base.player.Player;
import edu.austral.starship.base.vector.Vector2;

import java.util.ArrayList;
import java.util.List;

public class GameObjectFactory {

    static Ship createShip(Weapon weapon) {
        return new Ship(Vector2.vector(0, -1), Vector2.vector(100, 100),
                10, weapon, 25, 25, 0.05F, 5);
    }

    static Weapon createWeapon(Player player) {
        List<BulletType> bullets = new ArrayList<>();
        bullets.add(new BulletType(1, 15, 10, 10, Vector2.vector(0, -1)
                , 0, Vector2.vector(0, 0)));
        bullets.add(new BulletType(1, 15, 10, 10, Vector2.vector(0, -1)
                , 0, Vector2.vector(-15, 0)));
        bullets.add(new BulletType(1, 15, 10, 10, Vector2.vector(0, -1)
                , 0, Vector2.vector(15, 0)));


        BulletFactoryImpl bulletFactory = new BulletFactoryImpl(bullets, player);
        return new WeaponImpl(bulletFactory, 5);
    }
}
