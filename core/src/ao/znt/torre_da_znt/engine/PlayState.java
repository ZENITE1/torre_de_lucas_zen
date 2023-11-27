package ao.znt.torre_da_znt.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import ao.znt.torre_da_znt.engine01.person.Tower;
import ao.znt.torre_da_znt.engine01.scene.Disk;
import ao.znt.torre_da_znt.engine01.scene.Fundo;
import ao.znt.torre_da_znt.engine01.scene.Sombra;
import ao.znt.torre_da_znt.states.GameOverState;
import ao.znt.torre_da_znt.states.GameStateManager;
import ao.znt.torre_da_znt.states.State;

public class PlayState extends State {

    private final FreeTypeFontGenerator generator;
    private final BitmapFont bitmap;
    private Fundo background;
    private Fundo background1;
    private final ShapeRenderer shapeRenderer;
    private Stage stage; // Objeto Stage para renderização da interface
    private Tower tower1,tower2,tower3, towerSelected;
    private BitmapFont bitmapFont;
    // ShapeRenderer shapeRenderer;
    Viewport v;
    private Skin skin;
    private Disk selectedDisk; // Disco selecionado para movimento
    private boolean isDragging = false; // Indica se um disco está sendo arrastado
    private static final int whith = Gdx.graphics.getWidth();
    private static final int height = Gdx.graphics.getHeight();
    private int whith3 = whith/3;
    private  float worldCoordsx = 0;
    private float worldCoordsy = 0;
    private boolean showSombra = false;
    private boolean movimentoPermitido = true;
    private int contador_de_movimento = 0;
    private Sombra sombra;
    OrthographicCamera camera;
    ArrayList<Tower> towers;
    Viewport viewport;
    int nivel;
    int nivelActual;
    int menor_movimento_nivel_actual = 0;
    int numerosDeMovimentosPorNivel[] = {0,0,0,0,0,0,0,0,0,0,0,0};
    int numerosDeMovimentosPorNivelActual[] = {0,0,0,0,0,0,0,0,0,0,0,0};
    int numerosDeMovimentosAlvo[] = {3,7,15,31,63,127,255,511,1023,2047,4096};
    int menor_movimento_alvo;

    public PlayState(GameStateManager gsm){
        super(gsm);
        Preferences preferences = Gdx.app.getPreferences("towergame");
        this.nivel  = preferences.getInteger("nivel",1);
        for (int i = 0; i < 12; i++) {
            this.numerosDeMovimentosPorNivel[i] = preferences.getInteger("menor_n_movimento_nivel" + i, 0);
        }
        this.menor_movimento_nivel_actual = numerosDeMovimentosPorNivel[nivel-1];// nivel-1 por se nivel for 1 posicao sera 0
        this.menor_movimento_alvo = numerosDeMovimentosAlvo[nivel-1];
        //Na tela de GameOver ou Sair do jogo e salvar os dados
        //Preferencias
        /*if(nivelActual > nivel){
            preferences.putInteger("nivel",nivelActual);
        }
        for (int i = 0; i < 11; i++) {
            if (numerosDeMovimentosPorNivelActual[i] < numerosDeMovimentosPorNivel[i])
                preferences.putInteger("menor_n_movimento_nivel" + i,this.numerosDeMovimentosPorNivelActual[i]);
        }*/




        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        generator = new FreeTypeFontGenerator(Gdx.files.internal("kfuture.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 25;
        parameter.borderWidth = 1;
        parameter.borderColor = Color.BLACK;
        parameter.color = Color.WHITE;
        bitmap = generator.generateFont(parameter);

        background = new Fundo("bg.png");
        background1 = new Fundo("bg1.png");


        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //stage.addActor(background);
        stage.addActor(background1);

        final Texture newTexture0 = new Texture(Gdx.files.internal("haste.png")); // Carregue uma nova textura
        //shapeRenderer = new ShapeRenderer();

        // Crie três torres

        towers = new ArrayList<Tower>();
        tower1 = new Tower(newTexture0, whith3/2,nivel+1);
        tower2 = new Tower(newTexture0,(whith3)+(whith3/2),nivel+1);
        tower3 = new Tower(newTexture0,2*whith3+(whith3/2),nivel+1);

        towers.add(tower1);
        towers.add(tower2);
        towers.add(tower3);


        // Adicione as torres ao stage
        stage.addActor(tower1);
        stage.addActor(tower2);
        stage.addActor(tower3);

        // Crie n discos com tamanhos diferentes
        Disk disk00 = new Disk(whith3-245);
        Disk disk01 = new Disk(whith3-225);
        Disk disk02 = new Disk(whith3-205);
        Disk disk03 = new Disk(whith3-185);
        Disk disk04 = new Disk(whith3-165);
        Disk disk05 = new Disk(whith3-145);
        Disk disk06 = new Disk(whith3-125);
        Disk disk07 = new Disk(whith3-105);
        Disk disk08 = new Disk(whith3-85);
        Disk disk09 = new Disk(whith3-65); // Tamanho 100
        Disk disk10 = new Disk(whith3-45); // Tamanho 100
        Disk disk11 = new Disk(whith3-25);  // Tamanho 75
        Disk disk12 = new Disk(whith3-5);  // Tamanho 50

        //Sombra dos discos
        sombra = new Sombra(whith3-100);
        stage.addActor(sombra);
        // Adicione os discos à torre 1
        tower1.push(disk12);
        tower1.push(disk11);
        tower1.push(disk10);
        tower1.push(disk09);
        tower1.push(disk08);
        tower1.push(disk07);
        tower1.push(disk06);
        tower1.push(disk05);
        tower1.push(disk04);
        tower1.push(disk03);
        tower1.push(disk02);
        tower1.push(disk01);
        tower1.push(disk00);

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector3 worldCoords = new Vector3(screenX, screenY,0);
                camera.unproject(worldCoords);
                System.out.println("Clicaste em X: "+worldCoords.x+" Y: "+worldCoords.y);

                // executa quando tocamos ensima do objecto
                System.out.println("touchDown");

                // Verifique se um disco foi tocado
                for (Tower tower:towers) {
                    if (tower.pickTopDisk(worldCoords.x, worldCoords.y) != null) {
                        towerSelected = tower;
                        selectedDisk = tower.pop();
                        //selectedDisk = tower.pickTopDisk(worldCoords.x, worldCoords.y);
                        break;
                    }
                }
                return true; // Consuma o evento de toque
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                if (selectedDisk != null) {
                    //quando arastamos o objecto
                    System.out.println("touchDragged");
                    Vector3 worldCoords = new Vector3(screenX, screenY,0);
                    camera.unproject(worldCoords);

                    // Atualize a posição do disco arrastado
                    selectedDisk.setPosition(worldCoords.x, worldCoords.y);



                    //inicia a desenhar a sombra
                    showSombra = true;// desenha a sambra
                    sombra.setSize((int) selectedDisk.getWidth());//tamanho da saobra
                    worldCoordsx  = worldCoords.x; // posicoes das sombras quando é arrastada
                    worldCoordsy = worldCoords.y;
                }
                return true;
            }
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {

                Vector3 worldCoords = new Vector3(screenX, screenY,0);
                camera.unproject(worldCoords);


                if (selectedDisk != null) {
                    // executa quando largamos o visor depois de arrastar
                    System.out.println("touchUp");

                    // Verifique se o disco pode ser movido para uma das torres
                    Tower targetTower = null;
                    for (Tower tower:towers) {
                        if (tower.getTowerAtPosition(worldCoords.x,worldCoords.y) != null) {
                            System.out.println("uma torre encontrada: "+tower.getTowerAtPosition(worldCoords.x, worldCoords.y));
                            targetTower = tower;//.getTowerAtPosition(worldCoords.x, worldCoords.y);
                            // moveDisk(towerSelected,targetTower);
                            break;
                        }
                    }
                    System.out.println("disco pode ser movido para uma torre?: "+targetTower);
                    if (targetTower != null && targetTower.isMoveValid(selectedDisk,targetTower)) {
                        //targetTower.push(selectedDisk);
                        movimentoPermitido = moveDisk(towerSelected,targetTower);
                        isGameOver(tower2.disks.size());
                    } else {
                        towerSelected.push(selectedDisk);
                        //Voltar o disco na torre de origem
                        // O movimento é inválido, retorne o disco à posição original
                        //if(towerSelected != null)
                        //nao faça nada ou mostra um aviso
                        //towerSelected.push(selectedDisk);
                        //moveDisk(targetTower,towerSelected);
                    }

                    selectedDisk = null;
                    towerSelected = null;
                    showSombra = false;

                }
                return true;
            }
        });
    }

    @Override
    protected void handleInput() {
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void render(SpriteBatch sb) {
        // Atualize a lógica do jogo
        camera.update();

        // Limpe a tela


        sb.begin();
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Desenhe a interface do jogo


        stage.getBatch().setProjectionMatrix(camera.combined);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();


        bitmap.draw(sb,"mov: "+contador_de_movimento,20,Gdx.graphics.getHeight() - 20);
        bitmap.draw(sb,"alvo:"+menor_movimento_alvo,20,Gdx.graphics.getHeight()- 50);
        //bitmapFont.draw(this.batch,""+contador_de_movimento,10,Gdx.graphics.getHeight() - 10);

        if(showSombra){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.LIGHT_GRAY);
            sombra.draw(shapeRenderer,(int) worldCoordsx,(int) worldCoordsy);
        }


        sb.end();
        shapeRenderer.end();
    }

    @Override
    public void dispose() {

        stage.dispose();
        shapeRenderer.dispose();
    }
 public boolean moveDisk(Tower sourceTower, Tower destinationTower) {
     boolean result;
        if (selectedDisk !=null) {
            contador_de_movimento++; //sempre que mover uma peca contar o movimento
            result = destinationTower.push(selectedDisk); // Adicione o disco à torre de destin
            //verificar se alcancou o objectivo
            System.out.println("SIZE: "+tower2.disks.size());
            return result;
        }else {
            Disk movingDisk = sourceTower.pop(); // Remova o disco da torre de origem
            return destinationTower.push(movingDisk); // Adicione o disco à torre de destino
             }
    }
    private void isGameOver(int size){
        if (size == nivel+1) {
            gameOver();
        }
    }

    private void gameOver() {
        gsm.set(new GameOverState(gsm));
        dispose();
    }

}