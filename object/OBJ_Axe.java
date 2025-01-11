package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity{
    public OBJ_Axe(GamePanel gp){
        super(gp);

        name = "Woodcutter's Axe";
        type = type_axe;
        down1 = setup("/sprite/object/axe", gp.titleSize, gp.titleSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" + name + "]\nA bit rusty but still \ncan cut some trees.";

    }
}
