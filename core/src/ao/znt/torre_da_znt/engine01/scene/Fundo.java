package ao.znt.torre_da_znt.engine01.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Fundo extends Actor {
    private int width; // Tamanho do disco
    private String img;
    private int height; // Altura do disco
    private Texture background;

    public Fundo(String img) {
        this.img = img;
        this.height = Gdx.graphics.getHeight();
        this.width = Gdx.graphics.getWidth();
        setBounds(0, 0, width, height); // Define os limites do ator
        background = new Texture(Gdx.files.internal(img.toString()));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(background,getX(),getY(),getWidth(),getHeight());
    }
}
