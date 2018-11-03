package edu.austral.starship.base.levels;

import edu.austral.starship.base.engines.Engine;
import edu.austral.starship.base.player.Player;
import processing.core.PGraphics;

import java.util.List;
import java.util.Set;

public interface Level {

    void draw(PGraphics graphics, Set<Integer> keySet);

    List<Engine> getEngines();

    Stage getStage();

    void setup(List<Engine> engines, LevelsController levelsController);

    void init(List<Player> players);
}
