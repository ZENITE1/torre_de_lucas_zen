package ao.znt.torre_da_znt.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class GameOverState extends State {
    int numerosDeMovimentosPorNivel[] = {0,0,0,0,0,0,0,0,0,0,0};
    int numerosDeMovimentosAlvo[] = {3,7,15,31,63,127,255,511,1023,2047,4096};
    int menor_movimento_alvo;
    int numerosDeMovimentosPorNivelActual[] = {0,0,0,0,0,0,0,0,0,0,0};
    int nivel;
    int nivelActual;
    int menor_movimento_nivel_actual = 0;

    private int xRegion0;
    private int yRegion0;
    private Texture background;
    private Texture playGame;


    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private BitmapFont bitmap;
    private TextureAtlas atlas;
    private TextureRegion[] regions;

    public GameOverState(GameStateManager gsm) {
        super(gsm);

        Preferences preferences = Gdx.app.getPreferences("towergame");
        this.nivel  = preferences.getInteger("nivel",1);
        for (int i = 0; i < 11; i++) {
            this.numerosDeMovimentosPorNivel[i] = preferences.getInteger("menor_n_movimento_nivel" + i, 0);
        }
        this.menor_movimento_nivel_actual = numerosDeMovimentosPorNivel[nivel-1];// nivel-1 por se nivel for 1 posicao sera 0
        menor_movimento_alvo = this.numerosDeMovimentosAlvo[nivel-1];

        //Na tela de GameOver ou Sair do jogo e salvar os dados
        //Preferencias
        /*if(nivelActual > nivel){
            preferences.putInteger("nivel",nivelActual);
        }
        for (int i = 0; i < 12; i++) {
            if (numerosDeMovimentosPorNivelActual[i] < numerosDeMovimentosPorNivel[i])
                preferences.putInteger("menor_n_movimento_nivel" + i,this.numerosDeMovimentosPorNivelActual[i]);
        }*/


        background = new Texture("bg.png");
        playGame = new Texture("playbtn.png");

        generator = new FreeTypeFontGenerator(Gdx.files.internal("kfuture.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 25;
        parameter.borderWidth = 1;
        parameter.borderColor = Color.CYAN;
        parameter.color = Color.GRAY;
        bitmap = generator.generateFont(parameter);

    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        sb.draw(playGame,
                Gdx.graphics.getWidth()/2 - (playGame.getWidth()/2),
                Gdx.graphics.getHeight()/2 - (playGame.getHeight()/2));
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
