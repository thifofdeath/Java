package gametest;

import javax.swing.*;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.ArrayList;

public class Character 
{
    // Declarations
    int valueX, valueY, moveX, moveY, FrameLength, FrameHeightTerrain, CharacterPos ;
    Image character;
    
    //boolean left, right;
    // Image Initializing
    ImageIcon def = new ImageIcon(getClass().getResource("/images/default.png"));
    ImageIcon right = new ImageIcon(getClass().getResource("/images/right.png"));
    ImageIcon left = new ImageIcon(getClass().getResource("/images/left.png"));
    ImageIcon jump = new ImageIcon(getClass().getResource("/images/jump.png"));
    ImageIcon jumprev = new ImageIcon(getClass().getResource("/images/jumprev.png"));
    ImageIcon weaponhold = new ImageIcon(getClass().getResource("/images/pistolhold.png"));
    
    String bul = "/images/bullet.png";
     
    int velX = 0;
    int velY = 0;
    
    // Gravity ALWAYS GOES down
    // But if you have a limit where Y stops, it will.
    // Keep something like this
    private int gravity = 1;
    // boolean heightdetect = true;
    private boolean falling = true;
    private boolean jumping = false;
    boolean bolgravity = true;
    
    int ammo = 25;
    static ArrayList bullets;
    
    boolean allow;
    boolean weaponpickup1 = false;
    boolean weaponswitch1 = false;
    
    // int health = 100;
    
    public Character()
    {
        // Integer Value Initializing
        if (!weaponswitch1)
        {
            character = right.getImage();
        }
        else
        {
            character = weaponhold.getImage();
        }
        CharacterPos = 150;
        
        valueX = 10;
        valueY = 140;
        
        FrameLength = 1200;
        FrameHeightTerrain = 0;
        
        bullets = new ArrayList();
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle(CharacterPos, valueY, 74, 186);
         //return new Rectangle(CharacterPos, valueY, 63, 154);
    }
    
    // This is here because of the arm. Holding the gun
    public Rectangle getBounds2()
    {
        return new Rectangle(CharacterPos + 62, valueY + 24, 62, 24);
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
                Bullet a = new Bullet ((CharacterPos + 136), (valueY + (186-120)), "/images/bullet.png");
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
        if (moveY == 1)
        {
            if (valueY > 0)
            {
                valueY -= gravity;
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
        if (bolgravity)
        {
            if (valueY <= 140)
            {
                valueY += gravity;
                // if (valueY == (172/3))
                // {
                //     gravity = 2;
                // }
                if (valueY >= 140)
                {
                    valueY = 140;
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
            if (CharacterPos + moveX <= 150) // Character Screen Limit
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
            CharacterPos = CharacterPos + moveX;
	}
        if (moveY == 1)
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
            moveX = -1;
            character = left.getImage();
        }
        
        if (key == KeyEvent.VK_RIGHT)
        {
            moveX = 1;
            if (!weaponswitch1)
            {
                character = right.getImage();
            }
            else
            {
                character = weaponhold.getImage();
            }
        }
        
        if (key == KeyEvent.VK_UP)
        {
            character = jump.getImage();
            moveY = 1;
            jump();
        }
        
        if (key == KeyEvent.VK_SPACE)
        {
            if (weaponpickup1)
            {
                fire();
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
            if (weaponpickup1)
            {
                weaponswitch1 = true;
            }
        }
    }
    
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            moveX = 0;
            character = left.getImage();
        }
        
        if (key == KeyEvent.VK_RIGHT)
        {
            moveX = 0;
            if (!weaponswitch1)
            {
                character = right.getImage();
            }
            else
            {
                character = weaponhold.getImage();
            }
        }
        
        if (key == KeyEvent.VK_UP)
        {
            moveY = 0;
            character = def.getImage();
            //heightdetect = true;
        }
    }
}