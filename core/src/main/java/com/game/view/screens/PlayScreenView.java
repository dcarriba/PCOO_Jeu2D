package com.game.view.screens;

import com.game.model.screens.PlayScreen;

public class PlayScreenView {
    private final PlayScreen playScreen;

    public PlayScreenView(PlayScreen playScreen){
        this.playScreen = playScreen;
    }

    public void update(){
        // Update the camera's view to follow the player
        playScreen.getGamecam().position.x = playScreen.getPlayer().getPositionX() + 8;
        playScreen.getGamecam().position.y = playScreen.getPlayer().getPositionY() + 8;

        // Make sure camera is updated after position change
        playScreen.getGamecam().update();
    }

    public void render(){
        update(); // updates the camera

        // Render the map
        playScreen.getWorldMap().getRenderer().setView(playScreen.getGamecam()); // Set the view for the map renderer based on camera
        playScreen.getWorldMap().getRenderer().render(); // Render the map



    }
}
