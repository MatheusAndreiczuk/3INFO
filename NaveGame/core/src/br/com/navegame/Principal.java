package br.com.navegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

import br.com.navegame.atores.Fundo;
import br.com.navegame.atores.Nuvem;
import br.com.navegame.atores.Pistola;
import br.com.navegame.atores.Projetil;

public class Principal extends ApplicationAdapter {
	final float LARGURAVIRTUAL=1024;
	final float ALTURAVIRTUAL=768;
	float alturaDispositivo, larguraDispositivo;
	int estadoJogo = 0;
	Stage mundo;
	Rectangle balaForma;
	Rectangle nuvemForma;
	Rectangle paredeForma;
	Random aleatorio = new Random();
	int pY = aleatorio.nextInt(768) + 1;
	Pistola arma;
	Projetil bala;
	Nuvem nuvem;
	Fundo fundo;
	OrthographicCamera camera;
	Viewport viewport;


	@Override
	public void create () {
		alturaDispositivo=ALTURAVIRTUAL;
		larguraDispositivo=LARGURAVIRTUAL;
		mundo = new Stage();
		arma = new Pistola();
		nuvem = new Nuvem();
		bala = new Projetil();
		fundo = new Fundo();
		fundo.setBounds(0,0, larguraDispositivo, alturaDispositivo);
		arma.setBounds(0, alturaDispositivo/2, 200, 120);
		bala.setBounds(arma.getX(),arma.getY(), 64,64);
		nuvem.setBounds(larguraDispositivo*2, pY, 200, 200);
		mundo.addActor(fundo);
		mundo.addActor(arma);
		mundo.addActor(bala);
		mundo.addActor(nuvem);
		camera=new OrthographicCamera();
		camera.position.set(LARGURAVIRTUAL/2,ALTURAVIRTUAL/2,0);
		viewport=new StretchViewport(LARGURAVIRTUAL,ALTURAVIRTUAL,camera);
	}

	@Override
	public void render () {
		camera.update();
		ScreenUtils.clear(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		mundo.draw();
		if(estadoJogo==0){
			if(Gdx.input.justTouched()){
				estadoJogo=1;
			}
		}else{
			balaForma = new Rectangle(bala.getX(), bala.getY(), 64, 64);
			nuvemForma = new Rectangle(nuvem.getX(), nuvem.getY(), 200, 200);
			paredeForma = new Rectangle(0, 0, 200, alturaDispositivo);
			if(Intersector.overlaps(nuvemForma, balaForma)){
				nuvem = new Nuvem();
				nuvem.setBounds(larguraDispositivo*2, pY, 200, 200);
				estadoJogo = 1;
			} else if(Intersector.overlaps(nuvemForma, paredeForma)){
				estadoJogo = 0;
			}
		}
		mundo.act(Gdx.graphics.getDeltaTime());
		}
	
	@Override
	public void dispose () {
		mundo.dispose();
	}
}
