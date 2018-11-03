package edu.austral.starship.base.engines;

import edu.austral.starship.base.collision.CollisionEngine;
import edu.austral.starship.base.gameobjects.GameObject;
import edu.austral.starship.base.gameobjects.hue.Text;
import edu.austral.starship.base.gameobjects.animations.Explosion;
import edu.austral.starship.base.gameobjects.rigid_bodies.Asteroid;
import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;
import edu.austral.starship.base.gameobjects.rigid_bodies.GameObjectCollisionable;
import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;
import edu.austral.starship.base.levels.Stage;

import java.util.ArrayList;
import java.util.List;

public class CollisionEngineContainer implements Engine {
    private CollisionEngine<GameObjectCollisionable> collisionEngine;
    private List<GameObjectCollisionable> collisionables;

    public CollisionEngineContainer(CollisionEngine<GameObjectCollisionable> collisionEngine) {
        this.collisionEngine = collisionEngine;
        this.collisionables = new ArrayList<>();
    }

    @Override
    public void execute(Stage stage) {
        for (GameObject gameObject : stage.getGameObjects()) {
            gameObject.accepts(this);
        }
        this.collisionEngine.checkCollisions(collisionables);
        collisionables = new ArrayList<>();
    }

    @Override
    public void acceptsAsteroid(Asteroid asteroid) {
        collisionables.add(asteroid);
    }

    @Override
    public void acceptsBullet(Bullet bullet) {
        collisionables.add(bullet);
    }

    @Override
    public void acceptsShip(Ship ship) {
        collisionables.add(ship);
    }

    @Override
    public void acceptsExplosion(Explosion explosion) {
        //explosion is not a collisionable
    }

    @Override
    public void acceptsText(Text text) {
        //text is not a collisionable
    }
}
