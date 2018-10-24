package edu.austral.starship.base.levels;

public interface LevelsController {
    public Level getCurrentLevel();
    public Level nextLevel();
    public Level previousLevel();
    public void setLevel(Level level);
}
