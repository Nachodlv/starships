package edu.austral.starship.base.levels;

import edu.austral.starship.base.engines.Engine;
import edu.austral.starship.base.player.Player;
import processing.core.PGraphics;

import java.util.List;

public class MainLevel implements Level {

    private List<Engine> engines;
    private Stage stage;

    @Override
    public void draw(PGraphics graphics) {

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
    public void setup(LevelsController levelController, List<Player> players) {

    }
}
