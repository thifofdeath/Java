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
    
    ImageIcon chica = new ImageIcon(getClass().getResource("/images/female/idle/SpriteIdle.png"));
    ImageIcon leftchica = new ImageIcon(getClass().getResource("/images/female/idle/LeftSpriteIdle.png"));
    
    ImageIcon chica1 = new ImageIcon(getClass().getResource("/images/female/walking/SpriteWalking1.png"));
    ImageIcon chica2 = new ImageIcon(getClass().getResource("/images/female/walking/SpriteWalking2.png"));
    ImageIcon chica3 = new ImageIcon(getClass().getResource("/images/female/walking/SpriteWalking3.png"));
    ImageIcon chica4 = new ImageIcon(getClass().getResource("/images/female/walking/SpriteWalking4.png"));
    ImageIcon leftchica1 = new ImageIcon(getClass().getResource("/images/female/walking/LeftSpriteWalking1.png"));
    ImageIcon leftchica2 = new ImageIcon(getClass().getResource("/images/female/walking/LeftSpriteWalking2.png"));
    ImageIcon leftchica3 = new ImageIcon(getClass().getResource("/images/female/walking/LeftSpriteWalking3.png"));
    ImageIcon leftchica4 = new ImageIcon(getClass().getResource("/images/female/walking/LeftSpriteWalking4.png"));
    
    ImageIcon chicajumping1 = new ImageIcon(getClass().getResource("/images/female/jumping/SpriteJumping1.png"));
    ImageIcon chicajumping2 = new ImageIcon(getClass().getResource("/images/female/jumping/SpriteJumping2.png"));
    ImageIcon chicafalling = new ImageIcon(getClass().getResource("/images/female/falling/SpriteFalling1.png"));
    
    ImageIcon chicaattack1 = new ImageIcon(getClass().getResource("/images/female/attacking/SpriteAttacking1.png"));
    ImageIcon chicaattack2 = new ImageIcon(getClass().getResource("/images/female/attacking/SpriteAttacking2.png"));
    ImageIcon chicaattack3 = new ImageIcon(getClass().getResource("/images/female/attacking/SpriteAttacking3.png"));
    ImageIcon chicaattack4 = new ImageIcon(getClass().getResource("/images/female/attacking/SpriteAttacking4.png"));
    
    static int sprite=0;
    
    String bul = "/images/bullet.png";
    
    // X AXIS
    int MOVESPEED = 2;
    // Y AXIS
    int JUMPSPEED = 2;
    // Weapon Speed       
    int ASPD = 30;
    
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
    boolean melee= false;
    
    boolean walkallow;
    boolean walking = false;
    boolean rewalking;
    boolean rewalkallow=false;
    
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
    
    public void attack()
    {
        sprite++;
        if (sprite == ASPD)
        {   
            character = chicaattack1.getImage();
            sprite++;
        }
        else if (sprite > ASPD + 30 && sprite < ASPD + 60)
        {
            character = chicaattack2.getImage();
            sprite++;
        }
        else if (sprite > ASPD + 90 && sprite < ASPD + 120)
        {
            character = chicaattack3.getImage();
            sprite++;
        }
        else if (sprite > ASPD + 150 && sprite < ASPD + 200)
        {
            character = chicaattack4.getImage();
            sprite++;
        }
        if (sprite > ASPD + 201)
        {
            sprite=0;
        }
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
                character = chicajumping2.getImage();
            }
        }
        if (moveY == -JUMPSPEED)
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
    
    public void walking()
    {
        sprite++;
         if (sprite == ASPD)
        {   
            character = chica1.getImage();
            sprite++;
        }
        else if (sprite > ASPD + 30 && sprite < ASPD + 60)
        {
            character = chica2.getImage();
            sprite++;
        }
        else if (sprite > ASPD + 90 && sprite < ASPD + 120)
        {
            character = chica3.getImage();
            sprite++;
        }
        else if (sprite > ASPD + 150 && sprite < ASPD + 200)
        {
            character = chica4.getImage();
            sprite++;
        }
        if (sprite > ASPD + 201)
        {
            sprite=0;
        }
    }
    
    public void rewalking()
    {
        sprite++;
         if (sprite == ASPD)
        {   
            character = leftchica1.getImage();
            sprite++;
        }
        else if (sprite > ASPD + 30 && sprite < ASPD + 60)
        {
            character = leftchica2.getImage();
            sprite++;
        }
        else if (sprite > ASPD + 90 && sprite < ASPD + 120)
        {
            character = leftchica3.getImage();
            sprite++;
        }
        else if (sprite > ASPD + 150 && sprite < ASPD + 200)
        {
            character = leftchica4.getImage();
            sprite++;
        }
        if (sprite > ASPD + 201)
        {
            sprite=0;
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
                rewalkallow = true;
                rewalking= true;
                rewalking();
            }
        }
        if (key == KeyEvent.VK_RIGHT)
        {
            moveX = MOVESPEED;
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
            else if (female)
            {
                walkallow = true;
                walking= true;
                walking();
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
                character = chicajumping1.getImage();
            }
        }
        
        
        if (key == KeyEvent.VK_DOWN)
        {
            
        }
        
        if (key == KeyEvent.VK_SPACE) 
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
                melee = true;
                allow = true;
                attack();
            }
            //allow = true;
        }
        
        if (key == KeyEvent.VK_R)
        {
            // No timer for now
            if (weaponpickup1)
            {
                ammo = 25;
            }
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
                rewalking=false;
                rewalkallow=false;
                character = leftchica.getImage();
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
                walking=false;
                walkallow=false;
                character = chica.getImage();
            }
        }
        
        if (key == KeyEvent.VK_UP)
        {
            moveY = -JUMPSPEED;
            if (male)
            {
                character = weaponhold.getImage();
            }
            else if (female)
            {
                sprite=0;
                character = chica.getImage();
            }
            //heightdetect = true;
        }
        
        if (key == KeyEvent.VK_SPACE) 
        {
            if (male)
            {
            }
            else if (female)
            {
            }
        }
    }
}