package ru.vpapin.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    private Texture texture;
    private float positionX;

    public Background() {
        texture = new Texture("missions_bg_image.jpg");
        positionX = 0.0f;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, positionX, 0);
        batch.draw(texture, 1280 + positionX, 0);
    }

    public void update() {
        positionX -= 0.5f;
        if (positionX < -1280) {
            positionX = 0.0f;
        }
    }
}