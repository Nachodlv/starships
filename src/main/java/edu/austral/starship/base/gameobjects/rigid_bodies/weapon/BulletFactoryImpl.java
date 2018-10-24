package edu.austral.starship.base.gameobjects.rigid_bodies.weapon;

import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;
import edu.austral.starship.base.gameobjects.rigid_bodies.BulletPlayer;
import edu.austral.starship.base.gameobjects.rigid_bodies.BulletType;
import edu.austral.starship.base.player.Player;
import edu.austral.starship.base.vector.Vector2;

import java.util.HashMap;
import java.util.Map;

public class BulletFactoryImpl implements BulletFactory {
    private Map<BulletType, Vector2> bulletTypes;
    private Player player;

    public BulletFactoryImpl(Map<BulletType, Vector2> bulletTypes, Player player) {
        this.bulletTypes = bulletTypes;
        this.player = player;
    }

    @Override
    public Map<Bullet, Vector2> createBullets() {
        Map<Bullet, Vector2> bullets = new HashMap<>();
        for (Map.Entry<BulletType, Vector2> entry : bulletTypes.entrySet()) {
            bullets.put(new BulletPlayer(entry.getKey(), player), entry.getValue());
        }
        return bullets;
    }
}
