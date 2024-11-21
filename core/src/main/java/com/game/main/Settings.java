package com.game.main;

/**
 * The <code>Settings</code> class contains all the user's game settings
 */
public class Settings {
    /**
     * Screen witdh
     */
    private int width;
    /**
     * Screen height
     */
    private int height;

    /**
     * Constructor creating the default settings
     */
    public Settings(){
        this.width = 1280;
        this.height = 720;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
