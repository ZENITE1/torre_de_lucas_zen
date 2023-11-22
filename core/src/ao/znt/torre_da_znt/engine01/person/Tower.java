package ao.znt.torre_da_znt.engine01.person;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Stack;

import ao.znt.torre_da_znt.engine01.scene.Disk;

public class Tower extends Actor {
    private float x; // Posição X da torre
    private float y; // Posição Y da torre
    private float width; // Largura da torre
    private float height; // Altura da torre
    private int limiteDeDiscos = 3;
    public Stack<Disk> disks;
    private Texture texture; // A textura que representa a torre

    public Tower(Texture texture,int x) {
        this.texture = texture;
        this.width = 50;
        this.height = Gdx.graphics.getHeight() - 200;
        //this.setX(x);
        //this.y = 0;
        this.setY(this.y);
        this.disks = new Stack<>();
        this.setBounds(x-(width/2), y, width, height);
    }
    public boolean push(Disk disk) {
        // Verifica se a torre já atingiu o limite de discos
        if (disks.size() < limiteDeDiscos) {
            disks.push(disk);
            return true; // O disco foi adicionado com sucesso
        } else {
            return false; // A torre está cheia, o disco não pode ser adicionado
        }
    }
    public Disk pop() {// remove
        if (!disks.isEmpty()) {
            return disks.pop();
        } else {
            return null;
        }
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), width, height);

        // Desenhe os discos na torre
        int i = 0;
        for (Disk disk : disks) {
            System.out.println("Desenha Discos na Torre. Disco: "+i);
            disk.setX((getX()+width/2)-(disk.getWidth()/2));
            disk.setY(getDiskPosition(disk)*10+getDiskPosition(disk)*40);
            disk.draw(batch, parentAlpha);
            i++;
        }
    }
    public Disk peekTopDisk() {
        if (!disks.isEmpty()) {
            return disks.peek(); // Obtém o disco no topo da pilha (sem removê-lo)
        } else {
            return null; // A torre está vazia, não há disco no topo
        }
    }
    public boolean isMoveValid(Disk movingDisk, Tower destinationTower) {
        Disk topDisk = destinationTower.peekTopDisk(); // Método para obter o disco do topo da torre

        if (topDisk == null || movingDisk.getSize() < topDisk.getSize()) {
            return true; // O movimento é válido
        } else {
            return false; // O movimento é inválido
        }
    }
    public int getDiskPosition(Disk targetDisk) {
        int position = 0;

        for (Disk disk : disks) {
            if (disk == targetDisk) {
                return position; // O disco foi encontrado, retornar sua posição
            }
            position++;
        }

        // Caso o disco não seja encontrado, você pode retornar -1 ou outra indicação de que o disco não está na pilha
        return -1;
    }

    public Disk pickTopDisk(float x, float y) {
        for (int i = disks.size() - 1; i >= 0; i--) {
            Disk disk = disks.get(i);
            if (disk.getBounds().contains(x, y)) {

                return disk; // Retorna o disco se estiver nas coordenadas
            }
        }
        return null; // Retorna nulo se nenhum disco foi tocado
    }
    public Tower getTowerAtPosition(float touchX,float touchY) {
        if (getBounds().contains(touchX, touchY))
            return this; // A torre foi tocada, retornamos a referência para ela

        return null; // Nenhuma torre foi tocada, retornamos null
    }

    public Tower getTowerAtPosition(Rectangle rectangle) {
        if (getBounds().contains(rectangle))
            return this; // A torre foi tocada, retornamos a referência para ela

        return null; // Nenhuma torre foi tocada, retornamos null
    }
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), width, height);
    }
}