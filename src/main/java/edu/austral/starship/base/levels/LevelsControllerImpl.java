package edu.austral.starship.base.levels;

import edu.austral.starship.base.player.Player;

import java.util.List;

public class LevelsControllerImpl implements LevelsController {

    private List<Level> levels;
    private int indexCurrentLevel;

    public LevelsControllerImpl(List<Level> levels) {
        this.levels = levels;
        indexCurrentLevel = 0;
    }

    @Override
    public Level getCurrentLevel() {
        return levels.get(indexCurrentLevel);
    }

    @Override
    public Level nextLevel(List<Player> players) {
        indexCurrentLevel ++;
        Level level = levels.get(indexCurrentLevel);
        level.init(players);
        return level;
    }

    @Override
    public Level previousLevel(List<Player> players) {
        indexCurrentLevel --;
        Level level = levels.get(indexCurrentLevel);
        level.init(players);
        return level;
    }

    @Override
    public void setLevel(int level, List<Player> players) {
        indexCurrentLevel = level;
        levels.get(indexCurrentLevel).init(players);
    }
}
