package edu.austral.starship.base.engines;

import edu.austral.starship.base.gameobjects.GameObject;
import edu.austral.starship.base.gameobjects.HUE.Text;
import edu.austral.starship.base.gameobjects.animations.Explosion;
import edu.austral.starship.base.gameobjects.rigid_bodies.Asteroid;
import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;
import edu.austral.starship.base.gameobjects.rigid_bodies.RigidBody;
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
        moveRigidBody(asteroid);
    }

    @Override
    public void acceptsBullet(Bullet bullet) {
       moveRigidBody(bullet);
    }

    @Override
    public void acceptsShip(Ship ship) {
        Vector2 nextPosition = ship.getNextPosition();
        ship.setNextPosition(checkBoundariesForShip(nextPosition, ship.getHeight(), ship.getWidth()));

        float newVelocity = ship.getVelocity() - FRICTION_SHIP;
        if(newVelocity <= 0) newVelocity = 0;
        ship.setVelocity(newVelocity);
    }

    @Override
    public void acceptsExplosion(Explosion explosion) {
        // explosions do not move
    }

    @Override
    public void acceptsText(Text text) {
        //texts do not move
    }

    private Vector2 checkBoundariesForShip(Vector2 position, float height, float width) {
        float x = position.getX();
        float y = position.getY();

        if(x > stage.getWidth() - width) x = stage.getWidth() - width;
        else if (x <= width) x = width;

        if(y > stage.getHeight() - height) y = stage.getHeight() - height;
        else if(y <= height) y = height;

        return Vector2.vector(x, y);
    }

    private void moveRigidBody(RigidBody rigidBody) {
        Vector2 nextPosition = rigidBody.getNextPosition();
        if(checkBoundaries(rigidBody))
            rigidBody.setNextPosition(nextPosition);
        else rigidBody.setActive(false);
    }

    private boolean checkBoundaries(RigidBody rigidBody) {
        Vector2 position = rigidBody.getNextPosition();
        int width = rigidBody.getWidth();
        int height = rigidBody.getHeight();
        return position.getX() < stage.getWidth() + width && position.getX() >= -width &&
                position.getY() < stage.getHeight() + height && position.getY() >= -height;
    }
}