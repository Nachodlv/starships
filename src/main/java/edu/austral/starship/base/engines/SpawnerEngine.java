package edu.austral.starship.base.engines;

import edu.austral.starship.base.gameobjects.GameObject;
import edu.austral.starship.base.gameobjects.rigid_bodies.Asteroid;
import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;
import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;
import edu.austral.starship.base.levels.Stage;
import edu.austral.starship.base.vector.Vector2;

import java.util.List;
import java.util.Map;

public class SpawnerEngine implements Engine {
    private Stage stage;

    @Override
    public void execute(Stage stage) {
        this.stage = stage;
        List<GameObject> gameObjects = stage.getGameObjects();
        for (int i = 0; i < gameObjects.size(); i ++) {
            gameObjects.get(i).accepts(this);
        }
    }

    @Override
    public void acceptsAsteroid(Asteroid asteroid) {

    }

    @Override
    public void acceptsBullet(Bullet bullet) {

    }

    @Override
    public void acceptsShip(Ship ship) {
        if(ship.isShootTriggered()) {
            ship.shoot(false);
            List<Bullet> bullets = ship.getWeapon().shoot();
            Vector2 shipPosition = ship.getNextPosition();
            for (Bullet bullet : bullets) {
                float x = shipPosition.getX();
                float y = shipPosition.getY() ;

                float angleOffset = ship.getAngle() + bullet.getAngle();
                if(angleOffset >= 2 * Math.PI) angleOffset -= 2 * Math.PI;

                bullet.setDirection(angleOffset);
                Vector2 offset = Vector2.vector(0, -ship.getHeight()).add(bullet.getPosition())
                        .rotate(angleOffset);
                bullet.setNextPosition(Vector2.vector(x, y).add(offset));
                stage.addGameObject(bullet);
            }
        }
    }
}
