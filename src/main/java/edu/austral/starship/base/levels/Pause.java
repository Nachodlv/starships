package edu.austral.starship.base.levels;

import edu.austral.starship.base.engines.Engine;
import edu.austral.starship.base.levels.screen.PauseScreen;
import edu.austral.starship.base.player.Player;
import processing.core.PGraphics;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

public class Pause implements Level {
    private Stage stage;
    private List<Engine> engines;
    private LevelsController levelsController;
    private List<Player> players;

    public Pause(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void draw(PGraphics graphics, Set<Integer> keySet) {
        stage.update(graphics);
        for (Engine engine :
                engines) {
            engine.execute(stage);
        }
        for (Integer integer : keySet) {
            if(integer == KeyEvent.VK_R) {
                stage.resetStage();
                levelsController.previousLevel(players);
                return;
            } else if(integer == KeyEvent.VK_M) {
                stage.resetStage();
                levelsController.setLevel(0, players);
                return;
            }
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
    public void setup(List<Engine> engines, LevelsController levelsController) {
        this.engines = engines;
        this.levelsController = levelsController;
    }

    @Override
    public void init(List<Player> players) {
        this.players = players;
        stage.resetStage();
        stage.addGameObjects(new PauseScreen().draw());
    }
}
