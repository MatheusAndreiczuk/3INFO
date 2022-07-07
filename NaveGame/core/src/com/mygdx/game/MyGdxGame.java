package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Sound sndBomb;
    Sound sndExplosion;
    Music music;
    Texture background;
    Texture textureShip;
    Texture textureEnemy;
    Texture textureBomb;
    Texture texturePause;
    Texture textureGameOver;
    List<Texture> textureExplosions = new ArrayList<>();
    Background bk;
    BitmapFont font;
    List<Actor> actors = new ArrayList<>();
    List<Actor> news = new ArrayList<>();
    int w, h;
    Random rand = new Random();
    int enemyCounter = 0;
    int enemyMax = 50;
    int score;
    boolean gamePause = false;
    boolean isPlaying = true;
    boolean finished = false;

    @Override
    public void create() {
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        batch = new SpriteBatch();

        background = new Texture("background.png");
        textureShip = new Texture("ship.png");
        textureEnemy = new Texture("enemy.png");
        textureBomb = new Texture("bomb.png");
        texturePause = new Texture("paused.png");
        textureGameOver = new Texture("gameover.png");
        textureExplosions.add(new Texture("explosion_0.png"));
        textureExplosions.add(new Texture("explosion_1.png"));
        textureExplosions.add(new Texture("explosion_2.png"));
        textureExplosions.add(new Texture("explosion_1.png"));
        textureExplosions.add(new Texture("explosion_0.png"));
        bk = new Background(background, this);
        actors.add(new Ship(
                w / 2f - textureShip.getWidth() / 2f, 10,
                textureShip, this));
        font = new BitmapFont(
                Gdx.files.internal("verdana.fnt"),
                Gdx.files.internal("verdana.png"), false);
        font.setColor(Color.BLACK);
        sndBomb = Gdx.audio.newSound(Gdx.files.internal("bomb.wav"));
        sndExplosion = Gdx.audio.newSound(Gdx.files.internal("explosion.wav"));
        music = Gdx.audio.newMusic(Gdx.files.internal("music.ogg"));
        music.setLooping(true);
        music.play();
    }

    public void execute() {
        if (finished) {
            if (Gdx.input.justTouched()) {
                finished = false;
                actors.clear();
                actors.add(new Ship(
                        w / 2f - textureShip.getWidth() / 2f, 10,
                        textureShip, this));
                score = 0;
            }
        }
        bk.run();
        for (Actor a : actors) a.run();
        enemies();
        clean();
    }


    @Override


    public void render() {
         execute();
         Gdx.gl.glClearColor(1, 0, 0, 1);
         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         batch.begin();
         bk.draw(batch);
         for (Actor a : actors) a.draw();
         font.draw(batch, "SCORE: " + score, 1, h);
         if (gamePause) {
         batch.draw(texturePause,
        w / 2f - texturePause.getWidth() / 2f, h / 2f);

        }
         if (finished) {
         batch.draw(textureGameOver,
        w / 2f - textureGameOver.getWidth() / 2f, h / 2f);

        }
         batch.end();

    }

    void enemies() {
         if (gamePause) return;
         enemyCounter++;
         if (enemyCounter > enemyMax) {
             actors.add(new Enemy(
                    rand.nextInt(w - textureEnemy.getWidth()), h + 50,
                    textureEnemy, this));
            enemyCounter = 0;
            enemyMax = 30 + rand.nextInt(50);
        }
    }

    void shot(float x, float y) {
        news.add(new Bomb(x - (textureBomb.getWidth() / 2f), y, textureBomb, this));
    }

    void explosion(float x, float y) {
        news.add(new Explosion(x, y, textureExplosions, this));
    }

    void clean() {
        List<Actor> aux = actors;
        actors = new ArrayList<>();
        for (Actor a : aux) if (!a.dead) actors.add(a);
        actors.addAll(news);
        news.clear();
    }

    @Override
    public void dispose() {
        background.dispose();
        batch.dispose();
    }
}