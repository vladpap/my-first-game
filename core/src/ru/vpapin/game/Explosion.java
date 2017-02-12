package ru.vpapin.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Explosion {
    private static Texture texture;
    private boolean active;
    private Vector2 position;
    private int countRate;
    private int count;
    private float scale;

    public Explosion() {
        if (texture == null) {
            texture = new Texture("explosion2.png");
        }
        active = false;
        position = new Vector2(0.0f, 0.0f);
        countRate = 2;
        count = 0;
        scale = 0.0f;
    }

    public boolean isActive() {
        return active;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y,
                texture.getWidth() / 2.0f, texture.getHeight() / 2.0f, // coordinate of the scaling and rotation
                texture.getWidth(), texture.getHeight(),    //width & height in pixels
                scale, scale,                                     // scale
                0,                                // rotation
                1, 1,                                        // texel
                texture.getWidth(), texture.getHeight(),
                false, false);
    }

    public void setup(float x, float y) {
        position.x = x - 10;
        position.y = y - 10;
        active = true;
    }

    public void disable() {
        active = false;
        count = 0;
        scale = 0.0f;
    }

    public void update() {
        if (active) {
            count++;
            if (count > countRate) {
                scale += 0.2f;
                if (scale > 1.0f) {
                    disable();
                }
            }
        }

    }
}