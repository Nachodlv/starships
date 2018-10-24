package edu.austral.starship.base.gameobjects;

import edu.austral.starship.base.engines.Visitor;

public interface GameObject {
    public void accepts(Visitor visitor);
}
