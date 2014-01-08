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
    Board b;
    
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
    
    String bul = "/images/bullet.png";
    
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
    
    boolean allow;
    boolean weaponpickup1 = false;
    boolean weaponswitch1 = false;
    
    boolean upgrade1 = false;
    boolean upgrade2 = false;
    
    int health = 100;
    
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
        valueY = 550;
        
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
        return new Rectangle(CharacterPos, valueY, 74, 186);
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
        if (moveY == 3)
        {
            if (valueY > 200)
            {
                counter2+= 0.05;
                valueY += (int)((Math.sin(counter2) + Math.cos(counter2)) * 10);
                if (counter2 >= 7)
                {
                    counter2=4;
                }
                if (valueY >= 550)
                {
                    valueY=550;
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
        if (bolgravity)
        {
            if (valueY <= 550)
            {
                valueY += 10;
                // if (valueY == (172/3))
                // {
                //     gravity = 2;
                // }
                if (valueY >= 550)
                {
                    valueY = 550;
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
            CharacterPos = CharacterPos + moveX;
	}
        if (CharacterPos < 0)
        {
            CharacterPos = 0;
        }
        if (moveY == 3)
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
            moveX = -3;
            character = left.getImage();
        }
        
        if (key == KeyEvent.VK_RIGHT)
        {
            moveX = 3;
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
            moveY = 3;
            if (!weaponswitch1)
            {
                character = right.getImage();
            }
            else
            {
                character = weaponhold.getImage();
            }
            jump();
        }
        
        
        if (key == KeyEvent.VK_DOWN)
        {
            
        }
        
        if (key == MouseEvent.MOUSE_CLICKED)
        {
            
        }
        
        if (key == KeyEvent.VK_SPACE && !jumping) 
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
        
        if (key == KeyEvent.VK_P)
        {
            new GameTest();
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
            character = weaponhold.getImage();
            //heightdetect = true;
        }
    }
}