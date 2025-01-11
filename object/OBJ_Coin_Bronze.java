package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin_Bronze extends Entity{
    GamePanel gp;
    public OBJ_Coin_Bronze(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Coin Bronze";
        value = 1;
        type = type_pickupOnly;
        down1 = setup("/sprite/object/coin_bronze", gp.titleSize, gp.titleSize);
    }

    public void use(Entity entity){
        gp.playSE(1);
        gp.ui.addMessage("Coin +" + value);
        gp.player.coin += value;
    }
}
