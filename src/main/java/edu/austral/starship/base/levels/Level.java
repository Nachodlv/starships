package edu.austral.starship.base.levels;

import edu.austral.starship.base.engines.Engine;
import edu.austral.starship.base.framework.FontLoader;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.player.Player;
import processing.core.PGraphics;

import java.util.List;
import java.util.Set;

public interface Level {

    public void draw(PGraphics graphics, Set<Integer> keySet);

    public List<Engine> getEngines();

    public Stage getStage();

    public void setup(ImageLoader imageLoader, LevelsController levelsController);

    public void init(List<Player> players);
}
