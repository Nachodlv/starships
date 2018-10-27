package edu.austral.starship.base.gameobjects.rigid_bodies.weapon;

import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;
import edu.austral.starship.base.gameobjects.rigid_bodies.BulletPlayer;
import edu.austral.starship.base.gameobjects.rigid_bodies.BulletType;
import edu.austral.starship.base.player.Player;

import java.util.ArrayList;
import java.util.List;

public class BulletFactoryImpl implements BulletFactory {
    private List<BulletType> bulletTypes;
    private Player player;

    public BulletFactoryImpl(List<BulletType> bulletTypes, Player player) {
        this.bulletTypes = bulletTypes;
        this.player = player;
    }

    @Override
    public List<Bullet> createBullets() {
        List<Bullet> bullets = new ArrayList<>();
        for (BulletType bulletType : bulletTypes) {
            bullets.add(new BulletPlayer(bulletType, player, player.getShip().getInitialDirection()));
        }
        return bullets;
    }
}
