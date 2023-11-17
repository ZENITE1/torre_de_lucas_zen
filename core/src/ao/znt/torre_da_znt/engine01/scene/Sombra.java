package ao.znt.torre_da_znt.engine01.scene;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Sombra extends Actor {
    private int size; // Tamanho do disco
    private int height; // Altura do disco

    public Sombra(int width) {
        this.height = 50;
        this.size = width;
        setBounds(getX(), getY(), width, height); // Define os limites do ator
    }

    public void draw(ShapeRenderer shapeRenderer,int x,int y){
        setBounds(x, y, size, height); // Define os limites do ator
        shapeRenderer.rect(x-(size/2),y,size,height);
    }

    public int getSize() {
        return size;
    }

    public Rectangle getBounds() {
        System.out.println("DISK X: " + getX() + "Comprimento: " + getWidth());
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
