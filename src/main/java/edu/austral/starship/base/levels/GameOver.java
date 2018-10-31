package edu.austral.starship.base.levels;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.engines.*;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.gameobjects.GameObject;
import edu.austral.starship.base.gameobjects.HUE.Text;
import edu.austral.starship.base.levels.Screen.GameOverScreen;
import edu.austral.starship.base.player.Player;
import edu.austral.starship.base.vector.Vector2;
import processing.core.PConstants;
import processing.core.PGraphics;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GameOver implements Level {

    private Stage stage;
    private List<Engine> engines;
    private LevelsController levelsController;
    private List<Player> players;

    public GameOver(Stage stage) {
        this.stage = stage;
        this.engines = new ArrayList<>();
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

        for (GameObject gameObject : new GameOverScreen(players).draw()) {
            stage.addGameObject(gameObject);
        }
    }


}
