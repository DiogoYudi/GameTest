package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class InteractiveTile extends Entity{
    GamePanel gp;
    public boolean destuctible = false;
    public InteractiveTile(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;
    }

    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;
        return isCorrectItem;
    }

    public void playSE(){}
    
    public InteractiveTile getDestroyedForm(){
        InteractiveTile tile = null;
        return tile;
    }

    public void update(){
        
    }
}
