package ao.znt.torre_da_znt.engine01.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.Random;

public class Disk extends Actor {
    private TextureRegion textureRegion; // Textura do disco
    private int size; // Tamanho do disco
    private int height; // Altura do disco

    ArrayList<Texture> texturas;
    public Disk(int width) {
        this.height = 30;
        this.size = width;
        setBounds(getX(), getY(), width, height); // Define os limites do ator

        texturas  = new ArrayList<Texture>();
        final Texture newTexture5 = new Texture(Gdx.files.internal("img1.png")); // Carregue uma nova textura
        final Texture newTexture6 = new Texture(Gdx.files.internal("img2.png")); // Carregue uma nova textura
        final Texture newTexture7 = new Texture(Gdx.files.internal("img3.png")); // Carregue uma nova textura
        final Texture newTexture8 = new Texture(Gdx.files.internal("img4.png")); // Carregue uma nova textura
        final Texture newTexture9 = new Texture(Gdx.files.internal("img5.png")); // Carregue uma nova textura
        texturas.add(newTexture5);
        texturas.add(newTexture6);
        texturas.add(newTexture7);
        texturas.add(newTexture8);
        texturas.add(newTexture9);

        this.textureRegion = new TextureRegion(getTexture());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY(), getWidth(), getHeight());
    }

    public void setWidth(int width) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
    public Rectangle getBounds() {
        System.out.println("DISK X: "+getX()+"Comprimento: "+getWidth());
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    private Texture getTexture(){
        return texturas.get(new Random().nextInt(texturas.size()));
    }
}