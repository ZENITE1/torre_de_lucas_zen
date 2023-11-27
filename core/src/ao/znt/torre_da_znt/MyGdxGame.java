package ao.znt.torre_da_znt;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ao.znt.torre_da_znt.states.GameStateManager;
import ao.znt.torre_da_znt.states.MenuState;

public class MyGdxGame extends ApplicationAdapter {
	public static final String TITLE = "Tower zan";
	GameStateManager gsm;
	private SpriteBatch batch;

	@Override
	public void create () {
		gsm = new GameStateManager();
		batch = new SpriteBatch();
		Gdx.gl.glClearColor(1,0,0,1);
		gsm.push(new MenuState(gsm));
	}
	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	@Override
	public void dispose () {
		batch.dispose();
	}
}
