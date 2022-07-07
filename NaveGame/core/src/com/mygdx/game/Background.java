package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    MyGdxGame game;
    Texture texture;
    int ay, by;

    Background(Texture texture, MyGdxGame game) {
        this.game = game;
        this.texture = texture;
        ay = 0;
        by = texture.getHeight();
    }

    void run() {
        if (game.gamePause) return;
        ay -= 2;
        by -= 2;
        if (ay <= -texture.getHeight()) {
            ay = by + texture.getHeight();
        }
        if (by <= -texture.getHeight()) {
            by = ay + texture.getHeight();
        }
    }

    void draw(SpriteBatch batch) {
        batch.draw(texture, 0, ay);
        batch.draw(texture, 0, by);
    }
}