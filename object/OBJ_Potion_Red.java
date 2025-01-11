package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity{
    GamePanel gp;
    public OBJ_Potion_Red(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Red Potion";
        value = 5;
        type = type_consumable;
        down1 = setup("/sprite/object/potion_red", gp.titleSize, gp.titleSize);
        description = "[" + name + "]\nHeals your life " + value + ".";
    }

    public void use(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the " + name + "!\n" + "Your life has been recovered by " + value + ".";
        entity.life += value;
        gp.playSE(2);
    }
}
