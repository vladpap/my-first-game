package ru.vpapin.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Background background;
    SpaceShip ship;
    Asteroid[] asteroids;
    public static Bullet[] bullets;
    Explosion[] explosions;
    final static int COUNTASTEROID = 20;
    BitmapFont font;
    private int score;
    private int scoreRate;
    private int count;
    private String scoreTxt;

    @Override
    public void create() {
        score = 0;
        count = 0;
        scoreRate = 10;
        font = new BitmapFont(Gdx.files.internal("data/gamefont.fnt"));
        batch = new SpriteBatch();
        background = new Background();
        ship = new SpaceShip();
        asteroids = new Asteroid[COUNTASTEROID];
        for (int i = 0; i < asteroids.length; i++) {
            asteroids[i] = new Asteroid();
        }
        bullets = new Bullet[100];
        for (int i = 0; i < bullets.length; i++) {
            bullets[i] = new Bullet();
        }
        explosions = new Explosion[COUNTASTEROID];
        for (int i = 0; i < explosions.length; i++) {
            explosions[i] = new Explosion();
        }
    }

    @Override
    public void render() {
        update();
        count++;
        if (count > scoreRate) {
            count = 0;
            score++;
        }
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
        background.render(batch);
        ship.render(batch);
        for (Asteroid asteroid : asteroids) {
            asteroid.render(batch);
        }
        for (Bullet bullet : bullets) {
            if (bullet.isActive()) {
                bullet.render(batch);
            }
        }
        for (Explosion explosion : explosions) {
            if (explosion.isActive()) {
                explosion.render(batch);
            }
        }
        font.draw(batch, "Score :", 30, 700);
        font.draw(batch, scoreTxt, 140, 700);
        // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
        batch.end();
    }

    private void update() {
        scoreTxt = String.format("%1$06d", score);
        background.update();
        ship.update();
        for (Asteroid asteroid : asteroids) {
            asteroid.update();
        }
        for (Bullet bullet : bullets) {
            if (bullet.isActive()) {
                bullet.update();
                for (Asteroid asteroid : asteroids) {
                    if (bullet.isContact(asteroid.getRectangle())) {
                        bullet.disable();
                        for (Explosion explosion : explosions) {
                            if (!explosion.isActive()) {
                                explosion.setup(asteroid.getPosition().x, asteroid.getPosition().y);
                                score += 10;
                                break;
                            }
                        }
                        asteroid.recreate();
                    }
                }
            }
        }
        for (Explosion explosion : explosions) {
            if (explosion.isActive()) {
                explosion.update();
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
