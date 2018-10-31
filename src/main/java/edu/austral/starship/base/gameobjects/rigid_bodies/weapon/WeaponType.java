package edu.austral.starship.base.gameobjects.rigid_bodies.weapon;

import edu.austral.starship.base.levels.gameObjectFactory.WeaponFactory;
import edu.austral.starship.base.player.Player;

import java.util.Random;



public enum WeaponType {
    SUPER_SHOOT(WeaponFactory::superShootWeapon, "Super shoot weapon", 60),
    FAST(WeaponFactory::fastWeapon, "Fast weapon", 15),
    RANDOM(WeaponFactory::randomWeapon, "RNG weapon", new Random().nextInt(70) + 20);

    interface Factory {
        BulletPlayerFactory getBulletFactory();
    }

    private Factory factory;
    String name;
    int cadency;

    WeaponType(Factory factory, String name, int cadency) {
        this.factory = factory;
        this.name = name;
        this.cadency = cadency;
    }

    public Weapon getWeapon(Player player) {
        BulletPlayerFactory bulletFactory = factory.getBulletFactory();
        bulletFactory.setPlayer(player);
        return new WeaponImpl(bulletFactory, cadency);
    }

    public String getName() {
        return name;
    }
}
