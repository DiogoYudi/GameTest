package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Fireball;
import object.OBJ_Key;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);

        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.titleSize/2);
        screenY = gp.screenHeight/2 - (gp.titleSize/2);

        //Colisão
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;    //são os pixels do boneco que vai ser colisivo
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }

    public void setDefaultValues(){
        worldX = gp.titleSize * 23;
        worldY = gp.titleSize * 21;
        speed = 4;
        direction = "down";

        //STATUS
        maxLife = 6;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        level = 1;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        projectile = new OBJ_Fireball(gp);
        attack = getAttack();
        defense = getDefense();
    }

    public void setItems(){
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Key(gp));
    }

    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        return strength * currentWeapon.attackValue;
    }
    public int getDefense(){
        return dexterity * currentShield.defenseValue;
    }

    public void getPlayerImage(){
        up1 = setup("/sprite/player/boy_up_1", gp.titleSize, gp.titleSize);
        up2 = setup("/sprite/player/boy_up_2", gp.titleSize, gp.titleSize);
        down1 = setup("/sprite/player/boy_down_1", gp.titleSize, gp.titleSize);
        down2 = setup("/sprite/player/boy_down_2", gp.titleSize, gp.titleSize);
        left1 = setup("/sprite/player/boy_left_1", gp.titleSize, gp.titleSize);
        left2 = setup("/sprite/player/boy_left_2", gp.titleSize, gp.titleSize);
        right1 = setup("/sprite/player/boy_right_1", gp.titleSize, gp.titleSize);
        right2 = setup("/sprite/player/boy_right_2", gp.titleSize, gp.titleSize);
    }

    public void getPlayerAttackImage(){
        if(currentWeapon.type == type_sword){
            attackUp1 = setup("/sprite/player/boy_attack_up_1", gp.titleSize, gp.titleSize * 2);    //Vezes 2 porque as imagens é 16x32
            attackUp2 = setup("/sprite/player/boy_attack_up_2", gp.titleSize, gp.titleSize * 2);
            attackDown1 = setup("/sprite/player/boy_attack_down_1", gp.titleSize, gp.titleSize * 2);
            attackDown2 = setup("/sprite/player/boy_attack_down_2", gp.titleSize, gp.titleSize * 2);
            attackLeft1 = setup("/sprite/player/boy_attack_left_1", gp.titleSize *2, gp.titleSize);
            attackLeft2 = setup("/sprite/player/boy_attack_left_2", gp.titleSize * 2, gp.titleSize);
            attackRight1 = setup("/sprite/player/boy_attack_right_1", gp.titleSize * 2, gp.titleSize);
            attackRight2 = setup("/sprite/player/boy_attack_right_2", gp.titleSize * 2, gp.titleSize);
        }
        if(currentWeapon.type == type_axe){
            attackUp1 = setup("/sprite/player/boy_axe_up_1", gp.titleSize, gp.titleSize * 2);    //Vezes 2 porque as imagens é 16x32
            attackUp2 = setup("/sprite/player/boy_axe_up_2", gp.titleSize, gp.titleSize * 2);
            attackDown1 = setup("/sprite/player/boy_axe_down_1", gp.titleSize, gp.titleSize * 2);
            attackDown2 = setup("/sprite/player/boy_axe_down_2", gp.titleSize, gp.titleSize * 2);
            attackLeft1 = setup("/sprite/player/boy_axe_left_1", gp.titleSize *2, gp.titleSize);
            attackLeft2 = setup("/sprite/player/boy_axe_left_2", gp.titleSize * 2, gp.titleSize);
            attackRight1 = setup("/sprite/player/boy_axe_right_1", gp.titleSize * 2, gp.titleSize);
            attackRight2 = setup("/sprite/player/boy_axe_right_2", gp.titleSize * 2, gp.titleSize);
        }
        
    }

    public void update(){
        if(attacking == true){
            attacking();
        }
        else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true){

            if(keyH.upPressed == true){
                direction = "up";
            }
            else if(keyH.downPressed == true){
                direction = "down";
            }
            else if(keyH.leftPressed == true){
                direction = "left";
            }
            else if(keyH.rightPressed == true){
                direction = "right";
            }
            //Checar colisão
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //Checar colisão com objeto
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //Checar colisão com npc
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //Checar colisão com monstro
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);

            //Checar evento
            gp.eHandler.checkEvent();

            if(collisionOn == false && keyH.enterPressed == false){
                switch (direction) {
                case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            if(keyH.enterPressed == true && attackCanceled == false){
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;
            gp.keyH.enterPressed = false;

            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        else{
            standCounter++;
            if(standCounter == 20){
                spriteNum = 1;
                standCounter = 0;
            }
        }
        if(gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30 && projectile.haveResources(this) == true){    //projectile.alive == false serve para que o player não possa jogar outro projetil enquanto ja houver um na tela
            projectile.set(worldX, worldY, direction, true, this);  //Setar uma cordenada, direção e user default

            //Diminuir a mana max pelo custo de uso
            projectile.subtractResources(this);

            //Adicionar na lista
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
            gp.playSE(10);
        }

        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
        if(life > maxLife){
            life = maxLife;
        }
        if(mana > maxMana){
            mana = maxMana;
        }
    }

    public void attacking(){
        spriteCounter++;
        if(spriteCounter <= 5){     //Mostra a sprite do boy_attacking_1 nesses 5 frames
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25){   //Mostra a sprite do boy_attacking_2 nesses 20 frames (6-25)
            spriteNum = 2;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction) {    //Attack area
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }
            //Attack area se torna a solidArea(Area de colisão)
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            //Checar colisão do ataque com o monstro
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack);

            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndex);

            //Depois de verificar a colisão, retornar os valores ao original
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > 25){     //Finalizar a animação de ataque
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i){
        if(i != 999){
            //PICKUP ONLY ITENS
            if(gp.obj[i].type == type_pickupOnly){
                gp.obj[i].use(this);
                gp.obj[i] = null;
            }
            //INVENTORY ITENS
            else{
                String text;
                if(inventory.size() != maxInventorySize){
                    inventory.add(gp.obj[i]);   //Adicionar no inventario o item que pegou
                    gp.playSE(1);
                    text = "Got a " + gp.obj[i].name + "!"; 
                }else{
                    text = "Inventory full!";
                }
                gp.ui.addMessage(text);
                gp.obj[i] = null;
            }
            
        }
    }

    public void interactNPC(int i){
        if(gp.keyH.enterPressed == true){
            if(i != 999){   //Player está tocando no npc
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        
    }

    public void contactMonster(int i){
        if(i != 999){   //Player está tocando no monstro
            if(invincible == false && gp.monster[i].dying == false){
                gp.playSE(6);
                int damage = gp.monster[i].attack - defense;
                if(damage < 0){
                    damage = 0;
                }
                life -= damage;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i, int attack){
        if(i != 999){   //O ataque do player está tocando no monstro
            if(gp.monster[i].invincible == false){
                gp.playSE(5);
                int damage = attack - gp.monster[i].defense;
                if(damage < 0){
                    damage = 0;
                }
                if(damage > gp.monster[i].life){
                    damage = gp.monster[i].life;
                }
                gp.monster[i].life -= damage;
                gp.ui.addMessage(damage + " damage!");
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if(gp.monster[i].life <= 0){
                    gp.monster[i].dying = true;
                    gp.ui.addMessage("Killed the " + gp.monster[i].name + "!");
                    gp.ui.addMessage("Exp + " + gp.monster[i].exp);
                    exp += gp.monster[i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    public void damageInteractiveTile(int i){
        if(i != 999 && gp.iTile[i].destuctible == true && gp.iTile[i].isCorrectItem(this) == true){
            gp.iTile[i].playSE();
            generateParticle(gp.iTile[i], gp.iTile[i]);
            gp.iTile[i] = gp.iTile[i].getDestroyedForm();
        }
    }

    public void checkLevelUp(){
        if(exp >= nextLevelExp){
            level++;
            nextLevelExp = nextLevelExp*2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSE(8);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You are level " + level + " now!\n" + "You feel more stronger!";
        }
    }

    public void selectItem(){
        int itemIndex = gp.ui.getItemIndexOnSlot();
        if(itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);
            if(selectedItem.type == type_sword || selectedItem.type == type_axe){
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if(selectedItem.type == type_shield){
                currentShield = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == type_consumable){
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch(direction){
        case "up":
                if(attacking == false){
                    if(spriteNum == 1) image = up1;
                    if(spriteNum == 2) image = up2;
                }
                if(attacking == true){
                    tempScreenY = screenY - gp.titleSize;
                    if(spriteNum == 1) image = attackUp1;
                    if(spriteNum == 2) image = attackUp2;
                }
                break;
            case "down":
                if(attacking == false){
                    if(spriteNum == 1) image = down1;
                    if(spriteNum == 2) image = down2;
                }
                if(attacking == true){
                    if(spriteNum == 1) image = attackDown1;
                    if(spriteNum == 2) image = attackDown2;
                }
                break;
            case "left":
                if(attacking == false){
                    if(spriteNum == 1) image = left1;
                    if(spriteNum == 2) image = left2;
                }
                if(attacking == true){
                    tempScreenX = screenX - gp.titleSize;
                    if(spriteNum == 1) image = attackLeft1;
                    if(spriteNum == 2) image = attackLeft2;
                }
                break;
            case "right":
                if(attacking == false){
                    if(spriteNum == 1) image = right1;
                    if(spriteNum == 2) image = right2;
                }
                if(attacking == true){
                    if(spriteNum == 1) image = attackRight1;
                    if(spriteNum == 2) image = attackRight2;
                }
                break;
        }
        
        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)); //Deixar o player um pouco mais transparente quando estiver em invencibilidade
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        //Resetar a transparencia
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //DEBUG
        // g2.setFont(new Font("Arial", Font.PLAIN, 26));
        // g2.setColor(Color.white);
        // g2.drawString("Invincible:" + invincibleCounter, 10, 400);
    }
    
}
