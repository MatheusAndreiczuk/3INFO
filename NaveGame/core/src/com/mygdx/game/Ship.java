package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

import java.awt.Cursor;

public class Ship extends Actor {
    Ship(float x, float y, Texture texture, MyGdxGame game) {
        super(x, y, texture, game);
    }
    boolean tiro = false;
    @Override
    void execute() {
        if(Gdx.input.isTouched()) {
            int y = Gdx.input.getY();
            int x = Gdx.input.getX();
            if (y < Gdx.graphics.getHeight() / 2 && !tiro) {
                game.shot(
                        sprite.getX() + (sprite.getWidth() / 2f),
                        sprite.getY() + sprite.getHeight() + 2);
                tiro = true;
            }
            else if (x < Gdx.graphics.getWidth() / 2) {
                sprite.translateX(-10);
            } else if (x > Gdx.graphics.getWidth()/2) {
                sprite.translateX(10);
            }
        } else{
            tiro = false;
        }
        for (Actor a : game.actors) {
            if (collide(a)) {
                dead = true;
                a.dead = true;
                game.explosion(sprite.getX(), sprite.getY());
                game.explosion(a.sprite.getX(), a.sprite.getY());
                game.finished = true;
            }
        }
    }
}