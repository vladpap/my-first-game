package ru.vpapin.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SpaceShip {
    private Texture texture;
    private Vector2 position;
    private float speed;
    private int fireRate;
    private int fireCount;

    public SpaceShip() {
        texture = new Texture("ship.png");
        position = new Vector2(0.0f, 0.0f);
        speed = 8.0f;
        fireRate = 6;
        fireCount = 0;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed;
            if (position.x < 0) {
                position.x = 0.0f;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed;
            if (position.x > (1280 - texture.getWidth())) {
                position.x = 1280 - texture.getWidth();
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += speed;
            if (position.y > 720) {
                position.y = -(texture.getHeight());
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= speed;
            if (position.y < -texture.getHeight()) {
                position.y = 720;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            fireCount++;
            if (fireCount > fireRate) {
                fire();
                fireCount = 0;
            }
        }
    }

    private void fire() {
        for (int i = 0; i < MyGdxGame.bullets.length; i++) {
            if (!MyGdxGame.bullets[i].isActive()) {
                MyGdxGame.bullets[i].setup(position.x + 60, position.y);
                break;
            }
        }
    }
}