package object;

import entity.Projectile;
import java.awt.Color;
import main.GamePanel;

public class OBJ_Rock extends Projectile{
    GamePanel gp;
    public OBJ_Rock(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Rock";
        speed = 6;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage(){
        up1 = setup("/sprite/projectile/rock_down_1", gp.titleSize, gp.titleSize);
        up2 = setup("/sprite/projectile/rock_down_1", gp.titleSize, gp.titleSize);
        down1 = setup("/sprite/projectile/rock_down_1", gp.titleSize, gp.titleSize);
        down2 = setup("/sprite/projectile/rock_down_1", gp.titleSize, gp.titleSize);
        left1 = setup("/sprite/projectile/rock_down_1", gp.titleSize, gp.titleSize);
        left2 = setup("/sprite/projectile/rock_down_1", gp.titleSize, gp.titleSize);
        right1 = setup("/sprite/projectile/rock_down_1", gp.titleSize, gp.titleSize);
        right2 = setup("/sprite/projectile/rock_down_1", gp.titleSize, gp.titleSize);

    }

    public Color getParticleColor(){
        Color color = new Color(40, 50, 0);
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
