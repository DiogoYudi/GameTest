package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity{
    public OBJ_Door(GamePanel gp){
        super(gp);
        name = "Door";
        down1 = setup("/sprite/object/door", gp.titleSize, gp.titleSize);
        collision = true;

        //Área de colisão
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
