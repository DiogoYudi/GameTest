package tile_interactive;

import main.GamePanel;

public class IT_Trunk extends InteractiveTile{
    GamePanel gp;
    public IT_Trunk(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.titleSize * col;
        this.worldY = gp.titleSize * row;

        down1 = setup("/sprite/tiles_interactive/trunk", gp.titleSize, gp.titleSize);
        
        //Sem colisão
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
