package edu.austral.starship.base.gameobjects.rigid_bodies.weapon;

import edu.austral.starship.base.levels.GameObjectFactory;

public enum WeaponType {
    SUPER_SHOOT(GameObjectFactory.superShootWeapon()),
    FAST(GameObjectFactory.fastWeapon()),
    RANDOM(GameObjectFactory.randomWeapon());

    private Weapon weapon;

    WeaponType(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
