package edu.austral.starship;

import edu.austral.starship.base.collision.CollisionEngine;
import edu.austral.starship.base.engines.*;
import edu.austral.starship.base.framework.FontLoader;
import edu.austral.starship.base.framework.GameFramework;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.framework.WindowSettings;
import edu.austral.starship.base.levels.*;
import edu.austral.starship.base.player.Player;
import edu.austral.starship.base.player.PlayerNumber;
import edu.austral.starship.base.player.controls.*;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.event.KeyEvent;


import java.util.*;

public class CustomGameFramework implements GameFramework {

    public static final int WIDTH = 1500;
    public static final int HEIGHT = 1000;

    private List<Player> players;
    private LevelsController levelsController;

    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader, FontLoader fontLoader) {
        windowsSettings.setSize(WIDTH, HEIGHT);
        List<Level> levels = new ArrayList<>();
        players = new ArrayList<>();

        Level menu = new Menu(new Stage(HEIGHT, WIDTH));
        levels.add(menu);

        Level keySelect = new KeySelect(new Stage(HEIGHT, WIDTH));
        levels.add(keySelect);

        Level mainLevel = new MainLevel(new Stage(HEIGHT, WIDTH));
        levels.add(mainLevel);

        Level gameOver = new GameOver(new Stage(HEIGHT, WIDTH));
        levels.add(gameOver);

        levelsController = new LevelsControllerImpl(levels);

//        players.add(new Player(PlayerNumber.PLAYER_ONE));
//        players.add(new Player(controlsPlayerTwo(), PlayerNumber.PLAYER_TWO));

        List<Engine> engines = createEngines(imageLoader);
        menu.setup(Collections.singletonList(engines.get(0)), levelsController);
        keySelect.setup(Collections.singletonList(engines.get(0)), levelsController);
        mainLevel.setup(engines, levelsController);
        gameOver.setup(Collections.singletonList(engines.get(0)), levelsController);

        menu.init(players);

    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        levelsController.getCurrentLevel().draw(graphics, keySet);
    }

    @Override
    public void keyPressed(KeyEvent event) {

    }

    @Override
    public void keyReleased(KeyEvent event) {

    }

    private List<Engine> createEngines(ImageLoader imageLoader) {
        List<Engine> engines = new ArrayList<>();
        engines.add(new RenderEngine(imageLoader));
        engines.add(new MoveEngine());
        engines.add(new SpawnerEngine(30 ,5));
        engines.add(new DeleteEngine());
        engines.add(new CollisionEngineContainer(new CollisionEngine<>()));
        return engines;
    }
}
