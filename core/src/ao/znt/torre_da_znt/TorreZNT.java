package ao.znt.torre_da_znt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class TorreZNT extends Game {
    GameScreenMain gameScreen;
    SpriteBatch batch;
    Texture img;
    ShapeRenderer shapeRenderer;
    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        gameScreen = new GameScreenMain(batch,shapeRenderer);
        setScreen(gameScreen);
    }

    @Override
    public void render() {
        super.render();
        batch.end();
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        gameScreen.resize(width, height);
    }

    @Override
    public void dispose() {
        gameScreen.dispose();
        shapeRenderer.dispose();
        batch.dispose();
    }
}