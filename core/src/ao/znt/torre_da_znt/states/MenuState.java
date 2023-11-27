package ao.znt.torre_da_znt.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ao.znt.torre_da_znt.engine.PlayState;

public class MenuState extends State {
    private Texture background;
    private Texture playGame;
    public MenuState(GameStateManager gsm){
        super(gsm);
        background = new Texture("bg.png");
        playGame = new Texture("playbtn.png");
    }
    @Override
    public void handleInput() {
        int xBtnPlay = Gdx.graphics.getWidth()/2 - playGame.getWidth()/2;
        int yBtnPlay = Gdx.graphics.getHeight()/2 - playGame.getHeight()/2;

        if(Gdx.input.justTouched()){
            if (Gdx.input.getX() < xBtnPlay + playGame.getWidth() && Gdx.input.getX() > xBtnPlay - playGame.getWidth()
                    && Gdx.input.getY() > yBtnPlay - playGame.getHeight() && Gdx.input.getY() < yBtnPlay + playGame.getHeight()) {
                gsm.set(new PlayState(gsm));
                dispose();
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        sb.draw(playGame,
                Gdx.graphics.getWidth()/2 - (playGame.getWidth()/2),
                Gdx.graphics.getHeight()/2 - (playGame.getHeight()/2));
        sb.end();
    }
    @Override
    public void dispose() {
        background.dispose();
        playGame.dispose();
    }
}