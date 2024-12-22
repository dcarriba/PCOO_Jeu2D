package com.game.model.graphics;

import com.badlogic.gdx.graphics.Texture;

/** The <code>SpriteSheet</code> class represents a spritesheet containing all the sprites of an entity */
public class SpriteSheet {
    /** The Texture object representing the loaded sprite sheet */
    private final Texture texture;
    /** Width of each frame in the sprite sheet */
    private final int frameWidth;
    /** Height of each frame in the sprite sheet */
    private final int frameHeight;
    /** The row in which the first sprite of the entity is on the spritesheet */
    private final int characterRow;
    /** The column in which the first sprite of the entity is on the spritesheet */
    private final int characterColumn;

    /**
     * Constructor to create a SpriteSheet
     * @param spriteSheetPath Path to the sprite sheet image
     * @param frameWidth Width of each frame in the sprite sheet
     * @param frameHeight Height of each frame in the sprite sheet
     * @param characterRow The row in which the first sprite of the character is on the spritesheet
     * @param characterColumn The column in which the first sprite of the character is on the spritesheet
     */
    public SpriteSheet(String spriteSheetPath, int frameWidth, int frameHeight, int characterRow, int characterColumn) {
        this.texture = new Texture(spriteSheetPath); // Loads the texture from the path
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.characterRow = characterRow;
        this.characterColumn = characterColumn;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public int getCharacterRow() {
        return characterRow;
    }

    public int getCharacterColumn() {
        return characterColumn;
    }

    public Texture getTexture() {
        return texture;
    }

    public void dispose() {
        texture.dispose(); // Disposes the texture when no longer needed
    }
}
