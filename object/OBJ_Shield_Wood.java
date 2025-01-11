package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Wood extends Entity {
    public OBJ_Shield_Wood(GamePanel gp){
        super(gp);

        name = "Wood Shield";
        type = type_shield;
        down1 = setup("/sprite/object/shield_wood", gp.titleSize, gp.titleSize);
        defenseValue = 1;
        description = "[" + name + "]\nMade by wood.";
    }
}
