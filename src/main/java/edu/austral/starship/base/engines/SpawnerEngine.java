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
            Map<Bullet, Vector2> bullets = ship.getWeapon().shoot();
            Vector2 shipPosition = ship.getNextPosition();
            for (Map.Entry<Bullet, Vector2> entry : bullets.entrySet()) {
                float x = shipPosition.getX() + entry.getValue().getX() + ship.getWidth();
                float y = shipPosition.getY() + entry.getValue().getY();
                entry.getKey().setNextPosition(Vector2.vector(x, y));
                stage.addGameObject(entry.getKey());
                System.out.println("SPAWNED BULLET");
            }
        }
    }
}
