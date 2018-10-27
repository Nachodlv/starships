package edu.austral.starship.base.engines;

import edu.austral.starship.base.gameobjects.GameObject;
import edu.austral.starship.base.gameobjects.rigid_bodies.Asteroid;
import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;
import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;
import edu.austral.starship.base.levels.Stage;
import edu.austral.starship.base.vector.Vector2;

public class MoveEngine implements Engine {
    Stage stage;

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
        ship.setNextDirection();
        Vector2 nextPosition = ship.getNextPosition();
        ship.setNextPosition(checkBoundaries(nextPosition, ship.getHeight(), ship.getWidth()));
    }

    private Vector2 checkBoundaries(Vector2 position, float height, float width) {
        float x = position.getX();
        float y = position.getY();

        if(x > stage.getWidth() - width) x = stage.getWidth() - width;
        else if (x <= width) x = width;

        if(y > stage.getHeight() - height) y = stage.getHeight() - height;
        else if(y <= height) y = height;
//
        System.out.println("X: " + x + ", Y: " + y );
        return Vector2.vector(x, y);
    }
}
