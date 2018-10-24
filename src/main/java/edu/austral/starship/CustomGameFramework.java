package edu.austral.starship;

import edu.austral.starship.base.engines.Engine;
import edu.austral.starship.base.engines.RenderEngine;
import edu.austral.starship.base.framework.GameFramework;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.framework.WindowSettings;
import edu.austral.starship.base.levels.*;
import edu.austral.starship.base.player.Player;
import processing.core.PGraphics;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CustomGameFramework implements GameFramework {
    int width = 500;
    int height = 500;
    LevelsController levelsController;

    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        windowsSettings
            .setSize(width, height);
        List<Level> levels = new ArrayList<>();
        List<Player> players = new ArrayList<>();
        players.add(new Player());

        Level level = getMainLevel();
        levels.add(getMainLevel());

        levelsController = new LevelsControllerImpl(levels);
    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        levelsController.getCurrentLevel().draw(graphics);
    }

    @Override
    public void keyPressed(KeyEvent event) {

    }

    @Override
    public void keyReleased(KeyEvent event) {

    }

    private Level getMainLevel() {
        List<Engine> engines = new ArrayList<>();
        engines.add(new RenderEngine());
        return new MainLevel(engines, new Stage(height, width));
    }
}
