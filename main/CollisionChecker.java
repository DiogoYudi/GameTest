package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;    //Vai pegar a posição do jogador (Posição da área do player colisivo)
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.titleSize;
        int entityRightCol = entityRightWorldX/gp.titleSize;
        int entityTopRow = entityTopWorldY/gp.titleSize;
        int entityBottomRow = entityBottomWorldY/gp.titleSize;

        int titleNum1, titleNum2;

        switch(entity.direction){ //Vai "prever" para onde o player irá caso aperte a tal tecla
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.titleSize;
                titleNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                titleNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[titleNum1].collision == true || gp.tileM.tile[titleNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.titleSize;
                titleNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                titleNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[titleNum1].collision == true || gp.tileM.tile[titleNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.titleSize;
                titleNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                titleNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[titleNum1].collision == true || gp.tileM.tile[titleNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.titleSize;
                titleNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                titleNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[titleNum1].collision == true || gp.tileM.tile[titleNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }
    }
    
    public int checkObject(Entity entity, boolean player){ //Verifica se o player está "relando" no objeto
        int index = 999;
        for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null){
                //Posição do player
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //Posição do objeto
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch(entity.direction){ //Vai verificar para onde o player irá 
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if(entity.solidArea.intersects(gp.obj[i].solidArea)){ //Verifica se o player e o objeto estão se colidindo
                    if(gp.obj[i].collision == true){
                        entity.collisionOn = true;
                    }
                    if(player){  //Esse if serve para que um outro personagem, como NPC ou MOB não consiga pegar os objetos
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    // COLISÃO ENTRE ENTIDADES
    public int checkEntity(Entity entity, Entity[] target){
        int index = 999;
        for(int i = 0; i < target.length; i++){
            if(target[i] != null){
                //Posição do player
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //Posição do objeto
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch(entity.direction){ //Vai verificar para onde o player irá 
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if(entity.solidArea.intersects(target[i].solidArea)){ //Verifica se o player e o objeto estão se colidindo
                    if(target[i] != entity){
                        entity.collisionOn = true;
                        index = i;  
                    }
                    
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity){     //Verificar colisão do npc para o player
        boolean contactPlayer = false;
        //Posição do player
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        //Posição do objeto
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch(entity.direction){ //Vai verificar para onde o player irá 
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
        }
        if(entity.solidArea.intersects(gp.player.solidArea)){ //Verifica se o player e o objeto estão se colidindo
            entity.collisionOn = true;
            contactPlayer = true;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return contactPlayer;
    }
}
