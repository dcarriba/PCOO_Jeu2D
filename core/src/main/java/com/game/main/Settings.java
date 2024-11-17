package com.game.main;

public class Settings {
    private int width;
    private int height;

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
