package edu.austral.starship.base.engines;

import edu.austral.starship.base.gameobjects.GameObject;
import edu.austral.starship.base.gameobjects.rigid_bodies.Asteroid;
import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;
import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;
import edu.austral.starship.base.levels.Stage;

import java.util.List;

public class DeleteEngine implements Engine {

    private Stage stage;

    @Override
    public void execute(Stage stage) {
        this.stage = stage;
        List<GameObject> gameObjects = stage.getGameObjects();
        for(int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).accepts(this);
        }
    }

    @Override
    public void acceptsAsteroid(Asteroid asteroid) {
        if(!asteroid.isActive()) stage.deleteGameObject(asteroid);

    }

    @Override
    public void acceptsBullet(Bullet bullet) {
        if(!bullet.isActive()) stage.deleteGameObject(bullet);
    }

    @Override
    public void acceptsShip(Ship ship) {
        if(!ship.isActive()) {
            stage.deleteGameObject(ship);
        }
    }

}
