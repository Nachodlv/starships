package edu.austral.starship.base.levels;

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
    public Level nextLevel() {
        indexCurrentLevel ++;
        return levels.get(indexCurrentLevel);
    }

    @Override
    public Level previousLevel() {
        indexCurrentLevel --;
        return levels.get(indexCurrentLevel);
    }

    @Override
    public void setLevel(Level level) {
        indexCurrentLevel = levels.indexOf(level);
    }
}
