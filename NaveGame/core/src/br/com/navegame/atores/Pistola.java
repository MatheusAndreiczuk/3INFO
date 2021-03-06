package br.com.navegame.atores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Pistola extends Actor {

    Texture imagem;

    public Pistola() {
        imagem = new Texture("Pistola.jpg");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(imagem, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if ((Gdx.input.isTouched())) {
            this.setY(this.getY() + 10);
        } else {
            this.setY(this.getY() - 10);
        }
    }
}
