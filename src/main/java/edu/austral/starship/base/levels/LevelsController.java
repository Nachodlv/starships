package edu.austral.starship.base.levels;

import edu.austral.starship.base.player.Player;

import java.util.List;

public interface LevelsController {
    Level getCurrentLevel();
    void nextLevel(List<Player> players);
    void setLevel(int level, List<Player> players);
    void previousLevel(List<Player> players);
    public int levelsQuantity();
}
