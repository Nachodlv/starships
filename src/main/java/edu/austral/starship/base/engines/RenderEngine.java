package edu.austral.starship.base.engines;

import edu.austral.starship.base.gameobjects.GameObject;
import edu.austral.starship.base.gameobjects.rigid_bodies.Asteroid;
import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;
import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;
import edu.austral.starship.base.levels.Stage;
import edu.austral.starship.base.vector.Vector2;

import java.util.List;

public class RenderEngine implements Engine {
    private Stage stage;

    @Override
    public void execute(Stage stage) {
        this.stage = stage;
        List<GameObject> gameObjects = stage.getGameObjects();
        for (GameObject gameObject: gameObjects) {
            gameObject.accepts(this);
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
        Vector2 position = ship.getPosition();
        stage.getpGraphics().rect(position.getX(), position.getY(), 10, 10);
    }
}
