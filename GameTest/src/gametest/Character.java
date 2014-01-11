package gametest;

import javax.swing.*;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Character 
{
    // Declarations
    int valueX, valueY, moveX, moveY, FrameLength, FrameHeightTerrain, CharacterPos ;
    Image character;
    Board b;
    GameTest gt;
    MainMenu m;
    
    //boolean left, right;
    // Image Initializing
    ImageIcon def = new ImageIcon(getClass().getResource("/images/default.png"));
    ImageIcon right = new ImageIcon(getClass().getResource("/images/right.png"));
    ImageIcon left = new ImageIcon(getClass().getResource("/images/left.png"));
    ImageIcon jump = new ImageIcon(getClass().getResource("/images/jump.png"));
    ImageIcon jumprev = new ImageIcon(getClass().getResource("/images/jumprev.png"));
    ImageIcon weaponhold = new ImageIcon(getClass().getResource("/images/pistolhold.png"));
    ImageIcon pistol1 = new ImageIcon(getClass().getResource("/images/pistol1a.png"));
    ImageIcon pistol2 = new ImageIcon(getClass().getResource("/images/pistol1b.png"));
    ImageIcon crouch = new ImageIcon(getClass().getResource(""));
    
    ImageIcon chica = new ImageIcon(getClass().getResource("/images/SpriteIdle.png"));
    ImageIcon chica1 = new ImageIcon(getClass().getResource("/images/SpriteWalking1.png"));
    ImageIcon chica2 = new ImageIcon(getClass().getResource("/images/SpriteWalking2.png"));
    ImageIcon chica3 = new ImageIcon(getClass().getResource("/images/SpriteWalking3.png"));
    ImageIcon chica4 = new ImageIcon(getClass().getResource("/images/SpriteWalking4.png"));
    static int sprite=0;
    
    String bul = "/images/bullet.png";
    
    // X AXIS
    int MOVESPEED = 2;
    // Y AXIS
    int JUMPSPEED = 3;
    
    int velX = 0;
    int velY = 0;
    
    // Gravity ALWAYS GOES down
    // But if you have a limit where Y stops, it will.
    // Keep something like this
    private int gravity = 15;
    // boolean heightdetect = true;
    private boolean falling = true;
    public double counter2 = 4;
    private boolean jumping = false;
    boolean bolgravity = true;
    
    int ammo = 0;
    static ArrayList bullets;
    
    boolean male=false;
    boolean female=true;
    
    boolean allow;
    boolean weaponpickup1 = false;
    boolean weaponswitch1 = false;
    
    boolean upgrade1 = false;
    boolean upgrade2 = false;
    
    int health = 100;
    
//    private CharacterF chica;
    private BufferedImage[] idle;
//    private BufferedImage[] walking;
    private boolean facingLeft;
    Rectangle bodyBounds;
    int varY;
    
    public Character()
    {
        // Integer Value Initializing
        if (male)
        {
            if (!weaponswitch1)
            {
                character = right.getImage();
            }
            else
            {
                character = weaponhold.getImage();
            }
        }
        else if (female)
        {
            character = chica.getImage();
        }
        
        CharacterPos = 150;

        valueX = 10;
        if (male)
        {
            valueY = 550;
        }
        else if (female)
        {
            valueY = 585;
        }
        
        FrameLength = 1200;
        FrameHeightTerrain = 0;
        
        bullets = new ArrayList();

    }
    // This is the declare new rectangle for arms, weapons, etc
    public Rectangle getBounds(int x, int y, int posx, int posy)
    {
        return new Rectangle(x, y, posx, posy);
         //return new Rectangle(CharacterPos, valueY, 63, 154);
    }
    
    // This is to always have the body boundary
    public Rectangle defBody()
    {
        if (male)
        {
            bodyBounds = new Rectangle (CharacterPos, valueY, 74, 186);
        }
        else if (female)
        {
            bodyBounds = new Rectangle(CharacterPos, valueY, 83, 149);
        }
        return bodyBounds;
    }

    public static ArrayList getBullets()
    {
        return bullets;
    }

    public void fire()
    {
    // If valueY > 0
    // BulletY = valueY;
    // Set valueY of bullet position the value of the valueY;
        //if (allow)
//        if (moveX == 1 || moveX == 0)
//        {
            if (ammo > 0)
            {
                ammo--;
                //The v is from the board class, which corresponds to the character's
                //position when it is jumping, resulting in the bullets being formed 
                //at a higher position when the character is at the peak of its jump
                
                // CharacterPos + 63, where 63 = the length of the character which is 63 x 154
                // And valueY + 154/2 shoots from the middle of the character which is why
                // It is 154 / 2
                // Old CharacterPos + 63 (length of character), valueY + (154 / 2) to shoot from middle
                Bullet a = new Bullet ((CharacterPos + 136), (valueY + (186-115)), "/images/bullet.png");
                bullets.add(a);
                // allow = false;
            }
    }
//        else 
//        {
//            if (ammo > 0)
//            {
//                ammo--;
//                Bullet a = new Bullet ((CharacterPos - 63), (valueY + (154/2)), bul);
//                bullets.add(a);    
//            }
//        }
    
    public void jump()
    {
        if (male)
        {
            varY = 550;
        }
        else if (female)
        {
            varY = 585;
        }
        
        if (moveY == JUMPSPEED)
        {
            if (valueY > 200)
            {
                counter2+= 0.05;
                valueY += (int)((Math.sin(counter2) + Math.cos(counter2)) * 10);
                if (counter2 >= 7)
                {
                    counter2=4;
                }
                if (valueY >= varY)
                {
                    valueY=varY;
                }
            }
        }
        if (moveY == 0)
        {
            gravity();
        }

        //if (falling || jumping)
        //{
        //  velY += gravity;
        //}
    }
    
    public void gravity()
    {
        if (male)
        {
            varY = 550;
        }
        else if (female)
        {
            varY = 585;
        }
         
        if (bolgravity)
        {
            if (valueY <= varY)
            {
                valueY += 10;
                // if (valueY == (172/3))
                // {
                //     gravity = 2;
                // }
                if (valueY >= varY)
                {
                    valueY = varY;
                    gravity = 1;
                }
            }
        }
    }
   
    public void move()
    {
        CharacterPos += velX;
        valueY += velY;
        
        if (moveX != -1)
        {
            if (CharacterPos + moveX <= 400) // Character Screen Limit
            {
                CharacterPos += moveX;
            }
            // Moves Background instead of character
            else 
            {
                valueX = valueX + moveX;
                FrameLength = FrameLength + moveX;
                FrameHeightTerrain = FrameHeightTerrain + moveX;
            }
        }	
        else
	{
            if (CharacterPos + moveX > 0)
            {
                CharacterPos = CharacterPos + moveX;
            }
	}
        if (CharacterPos < 0)
        {
            CharacterPos = 0;
        }
        if (moveY == JUMPSPEED)
        {
            
        }
    }
    
        /* Moves Character only /*
        CharacterPos += moveX;
        if (CharacterPos >= 600)
        {
            FrameLength += moveX;
            FrameHeightTerrain += moveX;
            valueX += moveX;
        }
        */
//    public String getBullet()
//    {
//        return bul;
//    }
    
    public int getValueX()
    {
        return valueX;
    }
    
    public int getValueY()
    {
        return valueY;
    }

    public int getFrameHeight() 
    {
        return FrameHeightTerrain;
    }

    public int getFrameLength() 
    {
        return FrameLength;
    }

    public int getMoveX() 
    {
        return moveX;
    }
    
    public int getMoveY()
    {
        return moveY;
    }
    
    public int getChar()
    {
        return CharacterPos;
    }
    
    public Image getImage()
    {
        return character;
    }
    
    public boolean isJumping()
    {
        return jumping;
    }
    
    public boolean isFalling()
    {
        return falling;
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT)
        {
            moveX = -MOVESPEED;
            if (male)
            {
                character = left.getImage();
            }
            else if (female)
            {
                character = chica.getImage();
            }
        }
        
        if (key == KeyEvent.VK_RIGHT)
        {
            moveX = MOVESPEED;
            sprite++;
            if (male)
            {
                if (!weaponswitch1)
                {
                    character = right.getImage();
                }
                else if (weaponswitch1)
                {
                    character = weaponhold.getImage();
                }
            }
            if (female)
            {
                if (sprite <= 5)
                {
                    character = chica1.getImage();
                }
                else if (sprite > 6 && sprite < 9)
                {
                    character = chica2.getImage();
                }
                else if (sprite > 10 && sprite < 13)
                {
                    character = chica3.getImage();
                }
                else if (sprite > 14 && sprite < 17)
                {
                    character = chica4.getImage();
                    sprite=0;
                }
            }
        }
        
        if (key == KeyEvent.VK_UP)
        {
            moveY = JUMPSPEED;
            if (male)
            {
                if (!weaponswitch1)
                {
                    character = right.getImage();
                }
                else
                {
                    character = weaponhold.getImage();
                }
            }
            else if (female)
            {
                character = chica.getImage();
            }
                jump();
        }
        
        
        if (key == KeyEvent.VK_DOWN)
        {
            
        }
//        
//        if (key == MouseEvent.MOUSE_CLICKED)
//        {
//           
//        }
        
        if (key == KeyEvent.VK_SPACE && !jumping) 
        {
            if (male)
            {
                if (weaponpickup1)
                {
                    fire();
                }
            }
            else if (female)
            {
                character = chica.getImage();
            }
            //allow = true;
        }
        
        if (key == KeyEvent.VK_R)
        {
            // No timer for now
            ammo = 25;
        }
        
        if (key == KeyEvent.VK_1)
        {
            if (male)
            {
                if (weaponpickup1)
                {
                    weaponswitch1 = true;
                }
            }
            else if (female)
            {
                character = chica.getImage();
            }
        }
        
        if (key == KeyEvent.VK_P)
        {
            gt.frame.dispose();
            gt.value = 0;
            m.newgame = false;
            new GameTest();
        }
    }
    
    
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            moveX = 0;
            if (male)
            {
                character = left.getImage();
            }
            else if (female)
            {
                character = chica.getImage();
            }
        }
        
        if (key == KeyEvent.VK_RIGHT)
        {
            moveX = 0;
            if (male)
            {
                if (!weaponswitch1)
                {
                    character = right.getImage();
                }
                else
                {
                    character = weaponhold.getImage();
                }
            }
            else if (female)
            {
                character = chica.getImage();
            }
        }
        
        if (key == KeyEvent.VK_UP)
        {
            moveY = 0;
            if (male)
            {
                character = weaponhold.getImage();
            }
            else if (female)
            {
                character = chica.getImage();
            }
            //heightdetect = true;
        }
    }
}