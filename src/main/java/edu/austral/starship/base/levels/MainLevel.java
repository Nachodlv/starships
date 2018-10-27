package edu.austral.starship.base.levels;

import edu.austral.starship.base.engines.Engine;
import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;
import edu.austral.starship.base.player.Player;
import processing.core.PGraphics;

import java.util.List;

public class MainLevel implements Level {

    private List<Engine> engines;
    private Stage stage;

    public MainLevel(List<Engine> engines, Stage stage) {
        this.engines = engines;
        this.stage = stage;
    }

    @Override
    public void draw(PGraphics graphics) {
        stage.update(graphics);
        for (Engine engine :
                engines) {
            engine.execute(stage);
        }
    }

    @Override
    public List<Engine> getEngines() {
        return engines;
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public void setup(List<Player> players) {
        Ship ship = GameObjectFactory.createShip(GameObjectFactory.createWeapon(players.get(0)));
        players.get(0).setShip(ship);
        stage.addGameObject(ship);
    }

}
