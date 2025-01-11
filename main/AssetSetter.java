package main;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import object.OBJ_Axe;
import object.OBJ_Key;
import object.OBJ_Shield_Blue;
import tile_interactive.IT_DryTree;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){        //Local dos objetos
        int i = 0;
        gp.obj[i] = new OBJ_Axe(gp);
        gp.obj[i].worldX = gp.titleSize*24;
        gp.obj[i].worldY = gp.titleSize*7;
        i++;

        gp.obj[i] = new OBJ_Shield_Blue(gp);
        gp.obj[i].worldX = gp.titleSize*21;
        gp.obj[i].worldY = gp.titleSize*7;
        i++;

        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = gp.titleSize*25;
        gp.obj[i].worldY = gp.titleSize*21;
        i++;
    }

    public void setNPC(){
        int i = 0;
        gp.npc[i] = new NPC_OldMan(gp);
        gp.npc[i].worldX = gp.titleSize*21;
        gp.npc[i].worldY = gp.titleSize*21;
    }

    public void setMonster(){
        int i = 0;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.titleSize*11;
        gp.monster[i].worldY = gp.titleSize*32;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.titleSize*21;
        gp.monster[i].worldY = gp.titleSize*22;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.titleSize*22;
        gp.monster[i].worldY = gp.titleSize*21;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.titleSize*22;
        gp.monster[i].worldY = gp.titleSize*37;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.titleSize*24;
        gp.monster[i].worldY = gp.titleSize*37;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.titleSize*26;
        gp.monster[i].worldY = gp.titleSize*38;
        i++;
    }

    public void setInteractiveTile(){
        int i = 0;

        gp.iTile[i] = new IT_DryTree(gp, 28, 7);
        i++;

        gp.iTile[i] = new IT_DryTree(gp, 33, 7);
        i++;
    }
}
