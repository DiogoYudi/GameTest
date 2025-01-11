package object;

import entity.Entity;
import entity.Projectile;
import java.awt.Color;
import main.GamePanel;

public class OBJ_Fireball extends Projectile{
    GamePanel gp;
    public OBJ_Fireball(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Fireball";
        speed = 7;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage(){
        up1 = setup("/sprite/projectile/fireball_up_1", gp.titleSize, gp.titleSize);
        up2 = setup("/sprite/projectile/fireball_up_2", gp.titleSize, gp.titleSize);
        down1 = setup("/sprite/projectile/fireball_down_1", gp.titleSize, gp.titleSize);
        down2 = setup("/sprite/projectile/fireball_down_2", gp.titleSize, gp.titleSize);
        left1 = setup("/sprite/projectile/fireball_left_1", gp.titleSize, gp.titleSize);
        left2 = setup("/sprite/projectile/fireball_left_2", gp.titleSize, gp.titleSize);
        right1 = setup("/sprite/projectile/fireball_right_1", gp.titleSize, gp.titleSize);
        right2 = setup("/sprite/projectile/fireball_right_2", gp.titleSize, gp.titleSize);

    }

    public boolean haveResources(Entity user){
        boolean haveResources = false;
        if(user.mana >= useCost){
            haveResources = true;
        }
        return haveResources;
    }

    public void subtractResources(Entity user){
        user.mana -= useCost;
    }

    public Color getParticleColor(){
        Color color = new Color(240, 50, 0);
        return color;
    }

    public int getParticleSize(){
        int size = 10;   //10 pixel
        return size;
    }

    public int getParticleSpeed(){
        int speed = 1;
        return speed;
    }

    public int getParticleMaxLife(){
        int maxLife = 20;
        return maxLife;
    }

}
