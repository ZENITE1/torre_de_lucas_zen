package ao.znt.torre_da_znt.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import ao.znt.torre_da_znt.engine.PlayState;

public class MenuState extends State {
    private Texture background;
    private Texture playGame;


    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private BitmapFont bitmap;
    private TextureAtlas atlas;
    private TextureRegion[] regions;
    private boolean audioActive = true;
    int numerosDeMovimentosPorNivel[] = {0,0,0,0,0,0,0,0,0,0,0};
    int numerosDeMovimentosAlvo[] = {3,7,15,31,63,127,255,511,1023,2047,4096};
    int menor_movimento_alvo;
    int numerosDeMovimentosPorNivelActual[] = {0,0,0,0,0,0,0,0,0,0,0};
    int nivel;
    int nivelActual;
    int menor_movimento_nivel_actual = 0;

    private int xRegion0;
    private int yRegion0;

    public MenuState(GameStateManager gsm){
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

        atlas = new TextureAtlas(Gdx.files.internal("assetspng.atlas"));

        regions = new TextureRegion[4];
        regions[0] = atlas.findRegion("grey_boxCheckmark");
        regions[1] = atlas.findRegion("grey_sliderLeft");
        regions[2] = atlas.findRegion("grey_sliderRight");
        regions[3] = atlas.findRegion("grey_boxCross");

        xRegion0 = Gdx.graphics.getWidth() - (regions[0].getRegionWidth()+20);
        yRegion0 = Gdx.graphics.getHeight() - (regions[0].getRegionHeight()+10);

    }
    @Override
    public void handleInput() {
        int xBtnPlay = Gdx.graphics.getWidth()/2 - playGame.getWidth()/2;
        int yBtnPlay = Gdx.graphics.getHeight()/2 - playGame.getHeight()/2;

        if(Gdx.input.justTouched()){
            //btn play
            if (Gdx.input.getX() > xBtnPlay &&
                    Gdx.input.getX() < xBtnPlay + playGame.getWidth() &&
                    Gdx.input.getY() > yBtnPlay &&
                    Gdx.input.getY() < yBtnPlay + playGame.getHeight()) {
                gsm.set(new PlayState(gsm));
                dispose();
            }
            //TUDO quando for tocado mudar a png e talvez audio de click
            if(Gdx.input.getX() > xRegion0 &&
                    Gdx.input.getX() < xRegion0 + regions[0].getRegionWidth()+30 &&
                    Gdx.input.getY() < (regions[0].getRegionHeight()+50)
            ) {
                audioActive = !audioActive;
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

        sb.draw(regions[1],Gdx.graphics.getWidth()/2 - 2*regions[1].getRegionWidth(),50);
        bitmap.draw(sb, ""+nivel, Gdx.graphics.getWidth()/2-10, 50+30);
        sb.draw(regions[2],Gdx.graphics.getWidth()/2 + regions[2].getRegionWidth(),50);



        bitmap.draw(sb, "nível: "+nivel, 20, Gdx.graphics.getHeight() - 20);
        bitmap.draw(sb, "alvo: "+menor_movimento_alvo+" mov", 20, Gdx.graphics.getHeight() - 50);

        bitmap.draw(
                sb, "audio",
                Gdx.graphics.getWidth() - 150,
                Gdx.graphics.getHeight() - 20
        );
        //TUDO quando for tocado mudar a png e talvez audio de click
        if (!audioActive) {
            sb.draw(
                    regions[3],
                    xRegion0,
                    yRegion0
            );
        }else {
            sb.draw(
                    regions[0],
                    xRegion0,
                    yRegion0
            );
        }


        sb.end();
    }
    @Override
    public void dispose() {
        background.dispose();
        playGame.dispose();
    }
}