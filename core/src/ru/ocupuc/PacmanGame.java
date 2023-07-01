package ru.ocupuc;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.IOException;

public class PacmanGame extends ApplicationAdapter {
	SpriteBatch batch;
	Pacman pacman = new Pacman();
//	Texture img;

	Texture pacmanTexture;

	public PacmanGame() throws IOException {
	}

	@Override
	public void create () {
		pacmanTexture = new Texture(Gdx.files.internal("badlogic.jpg"));
    	batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


//		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(pacmanTexture, pacman.getX(), pacman.getY());
		batch.end();

		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			pacman.move(Direction.UP, 1);  // Перемещение Pacman вверх на 1 шаг
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			pacman.move(Direction.DOWN, 1);  // Перемещение Pacman вниз на 1 шаг
		} else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			pacman.move(Direction.LEFT, 1);  // Перемещение Pacman влево на 1 шаг
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			pacman.move(Direction.RIGHT, 1);  // Перемещение Pacman вправо на 1 шаг
		}
	}

	
	@Override
	public void dispose () {
//		batch.dispose();
	//	img.dispose();
	}
}
