package ru.vpapin.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Asteroid {
    private static Texture texture1;
    private static Texture texture2;
    private Texture texture;
    private Vector2 position;
    private float speed;
    private float speedRotate;
    private boolean clockwise;
    private float countRotate;
    private Rectangle rectangle;


    public Asteroid() {
        if (texture1 == null) {
            texture1 = new Texture("asteroid.png");
        }
        if (texture2 == null) {
            texture2 = new Texture("asteroid_2.png");
        }
        rectangle = new Rectangle();
        recreate();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y,
                texture.getWidth() / 2.0f, texture.getHeight() / 2.0f, // coordinate of the scaling and rotation
                texture.getWidth(), texture.getHeight(),    //width & height in pixels
                1f, 1f,                                     // scale
                countRotate,                                // rotation
                1, 1,                                        // texel
                texture.getWidth(), texture.getHeight(),
                false, false);                              // flipX and flipY
    }

    public void update() {
        position.x -= speed;
        if (position.x < -85) {
            recreate();
        }
        if (clockwise) {
            countRotate += speedRotate;
        } else {
            countRotate -= speedRotate;
        }

        if (Math.abs(countRotate) > 360) {
            countRotate = 0.0f;
        }
        rectangle.x = position.x;
        rectangle.y = position.y;
    }

    public void recreate() {
        countRotate = 0.0f;
        speed = 4.0f + (float) Math.random() * 6.0f;
        speedRotate = 0.5f + (float) Math.random() * 1.5f;

        if (position == null) {
            position = new Vector2();
        }
        position.x = (float) Math.random() * 1280 + 1280;
        position.y = (float) Math.random() * 720;
        if (System.nanoTime() % 2 == 0) {
            texture = texture1;
        } else {
            texture = texture2;
        }
        if (System.nanoTime() % 2 == 0) {
            clockwise = false;
        } else {
            clockwise = true;
        }
        rectangle.set(position.x, position.y, texture.getWidth(), texture.getHeight());


    }
}