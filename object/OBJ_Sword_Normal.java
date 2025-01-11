package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity{
    public OBJ_Sword_Normal(GamePanel gp){
        super(gp);
        
        name = "Normal Sword";
        type = type_sword;
        down1 = setup("/sprite/object/sword_normal", gp.titleSize, gp.titleSize);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nAn old sword.";
    }
}
