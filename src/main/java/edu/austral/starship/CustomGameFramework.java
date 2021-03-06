package edu.austral.starship;

import edu.austral.starship.base.collision.CollisionEngine;
import edu.austral.starship.base.engines.*;
import edu.austral.starship.base.framework.GameFramework;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.framework.WindowSettings;
import edu.austral.starship.base.levels.*;
import edu.austral.starship.base.player.Player;
import processing.core.PGraphics;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CustomGameFramework implements GameFramework {

    public static final int WIDTH = 1500;
    public static final int HEIGHT = 1000;
    public static final int TIME_BETWEEN_KEYS = 10;
    public static final double SCORE_KILL_SHIP = 2000;

    private LevelsController levelsController;

    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        windowsSettings.setSize(WIDTH, HEIGHT);

        List<Level> levels = createLevels(imageLoader);

        List<Player> players = new ArrayList<>();
        levels.get(0).init(players);
    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        levelsController.getCurrentLevel().draw(graphics, keySet);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        // using keySet on draw instead
    }

    @Override
    public void keyReleased(KeyEvent event) {
        // using keySet on draw instead
    }

    private List<Engine> createEngines(ImageLoader imageLoader) {
        List<Engine> engines = new ArrayList<>();
        engines.add(new RenderEngine(imageLoader));
        engines.add(new SpawnerEngine(30 ,5));
        engines.add(new DeleteEngine());
        engines.add(new CollisionEngineContainer(new CollisionEngine<>()));
        engines.add(new MoveEngine());
        return engines;
    }

    private List<Level> createLevels(ImageLoader imageLoader) {
        List<Level> levels = new ArrayList<>();

        Level menu = new Menu(new Stage(HEIGHT, WIDTH));
        levels.add(menu);
        Level keySelect = new KeySelect(new Stage(HEIGHT, WIDTH));
        levels.add(keySelect);
        Level weaponSelect = new WeaponSelect(new Stage(HEIGHT, WIDTH));
        levels.add(weaponSelect);
        Level mainLevel = new MainLevel(new Stage(HEIGHT, WIDTH), imageLoader.load("assets/background.jpg"));
        levels.add(mainLevel);
        Level pause = new Pause(new Stage(HEIGHT, WIDTH));
        levels.add(pause);
        Level gameOver = new GameOver(new Stage(HEIGHT, WIDTH));
        levels.add(gameOver);

        levelsController = new LevelsControllerImpl(levels);

        List<Engine> engines = createEngines(imageLoader);
        List<Engine> engine = Collections.singletonList(engines.get(0));
        menu.setup(engine, levelsController);
        keySelect.setup(engine, levelsController);
        weaponSelect.setup(engine, levelsController);
        mainLevel.setup(engines, levelsController);
        pause.setup(engine, levelsController);
        gameOver.setup(engine, levelsController);
        return levels;
    }
}
