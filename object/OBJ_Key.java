package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity{
    public OBJ_Key(GamePanel gp){
        super(gp);
        name = "Key";
        down1 = setup("/sprite/object/key", gp.titleSize, gp.titleSize);
        description = "[" + name + "]\nIt opens a door.";
    }
}
