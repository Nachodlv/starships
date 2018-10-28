package edu.austral.starship.base.framework;

import processing.core.PApplet;
import processing.core.PFont;

public class FontLoader {
    private final PApplet applet;

    public FontLoader(PApplet applet) {this.applet = applet;}

    public PFont load(String fileName) { return applet.createFont(fileName, 32); }

}
