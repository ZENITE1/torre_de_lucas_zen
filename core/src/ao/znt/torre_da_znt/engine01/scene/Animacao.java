package ao.znt.torre_da_znt.engine01.scene;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animacao {
    private Array<TextureRegion> frames;
    private float maxFrameTimes;
    private float currentFrameTame;
    private int frameCount;
    private int frame;

    public Animacao(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for (int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(region, i*frameWidth,0,frameWidth,region.getRegionHeight()));
            
        }
    }
}
