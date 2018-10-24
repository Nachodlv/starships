package edu.austral.starship.base.levels;

public interface LevelsController {
    public Level getCurrentLevel();
    public Level nextLevel();
    public Level prevousLevel();
    public void setLevel(Level level);
}
