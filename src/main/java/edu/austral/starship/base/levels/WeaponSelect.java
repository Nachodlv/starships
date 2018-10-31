package edu.austral.starship.base.levels;

import edu.austral.starship.base.engines.Engine;
import edu.austral.starship.base.gameobjects.rigid_bodies.weapon.WeaponType;
import edu.austral.starship.base.levels.Screen.Screen;
import edu.austral.starship.base.levels.Screen.WeaponSelectScreen;
import edu.austral.starship.base.player.Player;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class WeaponSelect implements Level {
    private List<Engine> engines;
    private Stage stage;
    private List<Player> players;
    private LevelsController levelsController;
    private List<Screen> screens;
    private int currentPlayer;
    private int KEY_WAIT = 10;
    private int currentKeyWait;

    public WeaponSelect(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void draw(PGraphics graphics, Set<Integer> keySet) {
        stage.update(graphics);
        for (Engine engine : engines) {
            engine.execute(stage);
        }

        Iterator<Integer> iterator = keySet.iterator();
        if(iterator.hasNext() && currentKeyWait >= KEY_WAIT) assignWeapon(iterator.next());
        currentKeyWait ++;

        if(currentPlayer >= players.size()){
            levelsController.nextLevel(players);
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
        this.currentPlayer = 0;
        this.currentKeyWait = 0;
        this.screens = new ArrayList<>();

        for (Player player : players) {
            String name = player.getPlayerNumber().getName();
            screens.add(new WeaponSelectScreen(name));
        }
        changeScreen();
    }

    private void assignWeapon(Integer key) {
        Player player = players.get(currentPlayer);
        int numberPressed = key - 49;
        if(numberPressed >= 0 && numberPressed < WeaponType.values().length) {
            WeaponType weapon = WeaponType.values()[numberPressed];
            player.setWeapon(weapon.getWeapon(player));
            currentPlayer ++;
            currentKeyWait = 0;
            changeScreen();
        }
    }

    private void changeScreen() {
        stage.resetStage();
        if(currentPlayer < screens.size()) stage.addGameObjects(screens.get(currentPlayer).draw());
    }
}
