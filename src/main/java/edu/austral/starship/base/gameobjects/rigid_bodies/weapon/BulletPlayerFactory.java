package edu.austral.starship.base.gameobjects.rigid_bodies.weapon;

import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;
import edu.austral.starship.base.gameobjects.rigid_bodies.BulletPlayer;
import edu.austral.starship.base.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BulletPlayerFactory implements BulletFactory {
    private Map<Offset, BulletType> bulletTypes;
    private Player player;

    public BulletPlayerFactory(Map<Offset, BulletType> bulletTypes) {
        this.bulletTypes = bulletTypes;
    }

    public BulletPlayerFactory(Map<Offset, BulletType> bulletTypes, Player player) {
        this.bulletTypes = bulletTypes;
        this.player = player;
    }

    @Override
    public List<Bullet> createBullets() {
        List<Bullet> bullets = new ArrayList<>();
        for(Map.Entry<Offset, BulletType> entry: bulletTypes.entrySet()) {
            bullets.add(new BulletPlayer(entry.getValue(), player, player.getShip().getInitialDirection(),
                    entry.getKey().getAngle(), entry.getKey().getPosition()));
        }
        return bullets;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
