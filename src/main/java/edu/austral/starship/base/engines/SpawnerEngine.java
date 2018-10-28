package edu.austral.starship.base.engines;

import edu.austral.starship.base.gameobjects.GameObject;
import edu.austral.starship.base.gameobjects.HUE.Text;
import edu.austral.starship.base.gameobjects.animations.Explosion;
import edu.austral.starship.base.gameobjects.rigid_bodies.Asteroid;
import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;
import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;
import edu.austral.starship.base.levels.Stage;
import edu.austral.starship.base.vector.Vector2;

import java.util.List;
import java.util.Random;

public class SpawnerEngine implements Engine {
    private Stage stage;
    private int asteroidSpawnTime;
    private int asteroidVelocity;
    private int lastAsteroidSpawned;

    public SpawnerEngine(int asteroidSpawnTime, int asteroidVelocity) {
        this.asteroidSpawnTime = asteroidSpawnTime;
        this.lastAsteroidSpawned = 0;
        this.asteroidVelocity = asteroidVelocity;
    }

    @Override
    public void execute(Stage stage) {
        this.stage = stage;
        spawnAsteroids();
        List<GameObject> gameObjects = stage.getGameObjects();
        for (int i = 0; i < gameObjects.size(); i ++) {
            gameObjects.get(i).accepts(this);
        }
    }

    @Override
    public void acceptsAsteroid(Asteroid asteroid) {
        //asteroids don't spawn other game objects
    }

    @Override
    public void acceptsBullet(Bullet bullet) {
        //bullets don't spawn other game objects
    }

    @Override
    public void acceptsShip(Ship ship) {
        if(ship.isShootTriggered()) {
            ship.shoot(false);
            List<Bullet> bullets = ship.getWeapon().shoot();
            Vector2 shipPosition = ship.getNextPosition();
            for (Bullet bullet : bullets) {
                float x = shipPosition.getX();
                float y = shipPosition.getY();

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

    @Override
    public void acceptsExplosion(Explosion explosion) {
        //explosions don't spawn other game objects
    }

    @Override
    public void acceptsText(Text text) {
        //texts don't spawn other game objects
    }


    private void spawnAsteroids() {
        if(lastAsteroidSpawned == 0) {
            Asteroid asteroid = generateRandomAsteroid();
            stage.addGameObject(asteroid);
            lastAsteroidSpawned = asteroidSpawnTime;
        } else {
            lastAsteroidSpawned--;
        }
    }

    /*
    * 0 = top border
    * 1 = left border
    * 2 = bottom border
    * 3 = right border
    * */
    private Asteroid generateRandomAsteroid() {
        Random random = new Random();
        int border = random.nextInt(4);
        float height = stage.getHeight();
        float width = stage.getWidth();
        float x = 0;
        float y = 0;
        float angle = random.nextFloat() * (float) Math.PI;
        switch (border) {
            case 0:
                x = random.nextFloat() * width;
                y = 0;
                angle += Math.PI/2;
                break;
            case 1:
                x = width;
                y = random.nextFloat() * height;
                angle += Math.PI;
                break;
            case 2:
                x = random.nextFloat() * width;
                y = height;
                angle = angle / 2;
                angle += 1.5 * Math.PI;
                angle = angle > Math.PI * 2 ? angle - (float)Math.PI * 2: angle;
                break;
            default:
                x = 0;
                y = random.nextFloat() * height;
                break;
        }

        return new Asteroid(angle, Vector2.vector(x, y), Vector2.vector(0, -1), asteroidVelocity);
    }

}
