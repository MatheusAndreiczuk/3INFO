package br.com.navegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.Rectangle;
import java.util.Random;

import br.com.navegame.atores.Nuvem;
import br.com.navegame.atores.Pistola;

public class Principal extends ApplicationAdapter {
	Texture nuvemDestruida = new Texture("vermelho.jpg");
	final float LARGURAVIRTUAL=1024;
	final float ALTURAVIRTUAL=768;
	float alturaDispositivo, larguraDispositivo;
	int estadoNuvem;
	int estadoJogo=0;
	Rectangle nuvemForma;
	Rectangle balaForma;
	Stage mundo;
	Random aleatorio = new Random();
	int pY = aleatorio.nextInt(768) + 1;
	Pistola arma;
	Nuvem nuvem;
	OrthographicCamera camera;
	Viewport viewport;

	@Override
	public void create () {
		alturaDispositivo=ALTURAVIRTUAL;
		larguraDispositivo=LARGURAVIRTUAL;
		mundo = new Stage();
		arma = new Pistola();
		nuvem = new Nuvem();
		arma.setBounds(0, alturaDispositivo/2, 200, 120);
		nuvem.setBounds(larguraDispositivo*2, pY, 200, 200);
		mundo.addActor(arma);
		mundo.addActor(nuvem);
		camera=new OrthographicCamera();
		camera.position.set(LARGURAVIRTUAL/2,ALTURAVIRTUAL/2,0);
		viewport=new StretchViewport(LARGURAVIRTUAL,ALTURAVIRTUAL,camera);
	}

	@Override
	public void render () {
		camera.update();
		estadoNuvem = 1;
		ScreenUtils.clear(1, 0, 0, 1);
		if(estadoJogo==0){
			if(Gdx.input.justTouched()){
				estadoJogo=1;
			}
		}else {
			if(estadoJogo==1){
				Texture nuvem = nuvemDestruida;
				mundo.draw();
				mundo.act(Gdx.graphics.getDeltaTime());
			}
		}

		nuvemForma = new Rectangle(200, 200, nuvem.getWidth(), nuvem.getHeight();
		balaForma = new Rectangle(64, 64);
		if(nuvemForma.intersects(balaForma)) {
			estadoJogo = 0;
		}
	}
	
	@Override
	public void dispose () {
		mundo.dispose();
	}
}
