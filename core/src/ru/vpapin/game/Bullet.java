package ru.vpapin.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    private static Texture texture;
    private Vector2 position;
    private float speed;
    private boolean active;

    public Bullet() {
        if (texture == null) {
            texture = new Texture("bullet.png");
        }
        this.position = new Vector2(0.0f, 0.0f);
        this.speed = 20.0f;
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isContact(Rectangle rect) {
        if (rect.contains(position)) {
            return true;
        }
        return false;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void setup(float x, float y) {
        position.x = x;
        position.y = y;
        active = true;
    }

    public void update() {
        position.x += speed;
        if (position.x > 1280) {
            disable();
        }
    }

    public void disable() {
        active = false;
    }
}