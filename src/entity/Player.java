package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        GetPlayerImage();
        setDefaultValues();
        spriteCount = 2;
    }

    public void GetPlayerImage() {

        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void update() {
        if (keyH.upPressed) {
            y -= speed;
            direction = "up";
        }
        if (keyH.downPressed) {
            y += speed;
            direction = "down";
        }
        if (keyH.leftPressed) {
            x -= speed;
            direction = "left";
        }
        if (keyH.rightPressed) {
            x += speed;
            direction = "right";
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;


        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }

                break;

        }
        if (keyH.downPressed || keyH.upPressed || keyH.leftPressed || keyH.rightPressed) {
            spriteCounter++;
        } else {
            spriteNum = 1;
        }

        if (spriteCounter > 12) {
            if (spriteNum < spriteCount) {
                spriteNum++;

            } else
                spriteNum = 1;
            spriteCounter = 0;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

}
