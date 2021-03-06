package edu.austral.starship.base.engines;

import edu.austral.starship.base.gameobjects.Drawable;
import edu.austral.starship.base.gameobjects.GameObject;
import edu.austral.starship.base.gameobjects.hue.Text;
import edu.austral.starship.base.gameobjects.animations.Explosion;
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
        if(!asteroid.isActive()) {
            stage.deleteGameObject(asteroid);
            stage.addGameObject(new Explosion(asteroid.getPosition(), 10, 7,
                    asteroid.getHeight(), asteroid.getWidth()));
        }

    }

    @Override
    public void acceptsBullet(Bullet bullet) {
        deleteDrawable(bullet);
    }

    @Override
    public void acceptsShip(Ship ship) {
        if(!ship.isActive()) {
            stage.deleteGameObject(ship);
            stage.addGameObject(new Explosion(ship.getPosition(), 10, 7,
                    ship.getHeight(), ship.getWidth()));
        }
    }

    @Override
    public void acceptsExplosion(Explosion explosion) {
        deleteDrawable(explosion);
    }

    @Override
    public void acceptsText(Text text) {
        deleteDrawable(text);
    }

    private void deleteDrawable(Drawable drawable) {
        if(!drawable.isActive()) stage.deleteGameObject(drawable);
    }
}
