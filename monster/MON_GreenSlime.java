package monster;

import entity.Entity;
import java.util.Random;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

public class MON_GreenSlime extends Entity{
    GamePanel gp;
    public MON_GreenSlime(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = "Green Slime";
        type = type_monster;
        speed = 1;
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 2;
        projectile = new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){
        up1 = setup("/sprite/monster/greenslime_down_1", gp.titleSize, gp.titleSize);
        up2 = setup("/sprite/monster/greenslime_down_2", gp.titleSize, gp.titleSize);

        down1 = setup("/sprite/monster/greenslime_down_1", gp.titleSize, gp.titleSize);
        down2 = setup("/sprite/monster/greenslime_down_2", gp.titleSize, gp.titleSize);

        left1 = setup("/sprite/monster/greenslime_down_1", gp.titleSize, gp.titleSize);
        left2 = setup("/sprite/monster/greenslime_down_2", gp.titleSize, gp.titleSize);

        right1 = setup("/sprite/monster/greenslime_down_1", gp.titleSize, gp.titleSize);
        right2 = setup("/sprite/monster/greenslime_down_2", gp.titleSize, gp.titleSize);
    }

    public void setAction(){
        actionLockCounter++;

        if(actionLockCounter == 120){               //Para que o npc não fique trocando de direção a cada frame (120frame = 2s)
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i <= 25){                            //É tipo uma IA, que vai escolher aleatoriamente o movimento do npc
                direction = "up";
            }if(i > 25 && i <= 50){
                direction = "down";
            }if(i > 50 && i <= 75){
                direction = "left";
            }if(i > 75 && i <= 100){
                direction = "right";
            }
            actionLockCounter = 0;   
        }

        int i = new Random().nextInt(100)+1;
        if(i > 99 && projectile.alive == false && shotAvailableCounter == 30){
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
    }

    public void damageReaction(){
        actionLockCounter = 0;
        direction = gp.player.direction;
    }

    public void checkDrop(){
        int i = new Random().nextInt(100)+1;

        //SET MONSTER DROP
        if(i < 25){
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if(i >= 25 && i < 50){
            dropItem(null);
        }
        if(i >= 50 && i < 75){
            dropItem(new OBJ_Heart(gp));
        }
        if(i >= 75 && i < 100){
            dropItem(new OBJ_ManaCrystal(gp));
        }
    }
}
