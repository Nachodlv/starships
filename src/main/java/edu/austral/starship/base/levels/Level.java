package edu.austral.starship.base.levels;

import edu.austral.starship.base.engines.Engine;
import edu.austral.starship.base.player.Player;
import processing.core.PGraphics;

import java.util.List;

public interface Level {

    public void draw(PGraphics graphics);

    public List<Engine> getEngines();

    public Stage getStage();

    public void setup(LevelsController levelController, List<Player> players);
}
