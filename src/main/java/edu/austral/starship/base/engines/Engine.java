package edu.austral.starship.base.engines;

import edu.austral.starship.base.levels.Stage;

public interface Engine extends Visitor{
    void execute(Stage stage);
}
