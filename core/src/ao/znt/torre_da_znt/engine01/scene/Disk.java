package ao.znt.torre_da_znt.engine01.scene;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Disk extends Actor {
    private TextureRegion textureRegion; // Textura do disco
    private int width; // Largura do disco
    private int size; // Tamanho do disco
    private int height; // Altura do disco

    public Disk(Texture texture,int width) {
        this.textureRegion = new TextureRegion(texture);
        this.height = 50;
        this.size = width;
        setBounds(getX(), getY(), width, height); // Define os limites do ator
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY(), getWidth(), getHeight());
    }

    public int getSize() {
        return size;
    }
    public Rectangle getBounds() {
        System.out.println("DISK X: "+getX()+"Comprimento: "+getWidth());
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}