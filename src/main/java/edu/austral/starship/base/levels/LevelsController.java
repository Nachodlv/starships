package edu.austral.starship.base.levels;

import edu.austral.starship.base.player.Player;

import java.util.List;

public interface LevelsController {
    public Level getCurrentLevel();
    public Level nextLevel(List<Player> players);
    public Level previousLevel(List<Player> players);
    public void setLevel(int level, List<Player> players);
}
