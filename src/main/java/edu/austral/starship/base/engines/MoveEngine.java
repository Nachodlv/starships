package edu.austral.starship.base.engines;

import edu.austral.starship.base.gameobjects.GameObject;
import edu.austral.starship.base.gameobjects.rigid_bodies.Asteroid;
import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;
import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;
import edu.austral.starship.base.levels.Stage;
import edu.austral.starship.base.vector.Vector2;

public class MoveEngine implements Engine {
    private Stage stage;
    private final float FRICTION_SHIP = 0.01F;

    @Override
    public void execute(Stage stage) {
        this.stage = stage;
        for (GameObject gameObject : stage.getGameObjects()) {
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
        Vector2 nextPosition = ship.getNextPosition();
        ship.setNextPosition(checkBoundaries(nextPosition, ship.getHeight(), ship.getWidth()));

        float newVelocity = ship.getVelocity() - FRICTION_SHIP;
        if(newVelocity <= 0) newVelocity = 0;
        ship.setVelocity(newVelocity);
    }

    private Vector2 checkBoundaries(Vector2 position, float height, float width) {
        float x = position.getX();
        float y = position.getY();

        if(x > stage.getWidth() - width) x = stage.getWidth() - width;
        else if (x <= width) x = width;

        if(y > stage.getHeight() - height) y = stage.getHeight() - height;
        else if(y <= height) y = height;

        return Vector2.vector(x, y);
    }
}
