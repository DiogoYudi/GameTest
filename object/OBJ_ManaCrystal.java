package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ManaCrystal extends Entity{
    GamePanel gp;
    public OBJ_ManaCrystal(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Mana Crystal";
        type = type_pickupOnly;
        value = 1;
        down1 = setup("/sprite/object/manacrystal_full", gp.titleSize, gp.titleSize);
        image = setup("/sprite/object/manacrystal_full", gp.titleSize, gp.titleSize);
        image2 = setup("/sprite/object/manacrystal_blank", gp.titleSize, gp.titleSize);
    }

    public void use(Entity entity){
        gp.playSE(2);
        gp.ui.addMessage("Mana +" + value);
        entity.mana += value;
    }
}