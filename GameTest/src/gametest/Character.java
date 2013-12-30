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
    ImageIcon def = new ImageIcon("C:/Users/thifofdeath/Documents/Game Test JAVA/default.png");
    ImageIcon right = new ImageIcon("C:/Users/thifofdeath/Documents/Game Test JAVA/dude.png");
    ImageIcon left = new ImageIcon("C:/Users/thifofdeath/Documents/Game Test JAVA/test.png");
    ImageIcon jump = new ImageIcon("C:/Users/thifofdeath/Documents/Game Test JAVA/jump.png");
    ImageIcon jumprev = new ImageIcon("C:/Users/thifofdeath/Documents/Game Test JAVA/jumprev.png");
    
    int ammo = 25;
    static ArrayList bullets;
    
    public Character()
    {
        // Integer Value Initializing
        character = def.getImage();
        CharacterPos = 150;
        
        valueX = 10;
        valueY = 172;
        
        FrameLength = 1200;
        FrameHeightTerrain = 0;
        
        bullets = new ArrayList();
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle(CharacterPos, valueY, 63, 154);
    }  

    public static ArrayList getBullets()
    {
        return bullets;
    }

    public void fire()
    {
        if (ammo > 0)
        {
            ammo--;
            //The v is from the board class, which corresponds to the character's
            //position when it is jumping, resulting in the bullets being formed 
            //at a higher position when the character is at the peak of its jump
            Bullet a = new Bullet ((CharacterPos + 60), (Board.v + (154/2)), ("C:/Users/thifofdeath/Documents/Game Test JAVA/bullet.png"));
            bullets.add(a);
        }
    }
   
    public void move()
    {
        if (moveX != -1)
        {
            if (CharacterPos + moveX <= 150)
                CharacterPos += moveX;
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
		if (CharacterPos + moveX >0)
		CharacterPos = CharacterPos + moveX;
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
            character = right.getImage();
        }
        
        if (key == KeyEvent.VK_UP)
        {
            moveY = 1;
            character = jump.getImage();
        }
        
        if (key == KeyEvent.VK_SPACE)
        {
            fire();
        }

    }
    
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            moveX = 0;
            character = def.getImage();
        }
        
        if (key == KeyEvent.VK_RIGHT)
        {
            moveX = 0;
            character = def.getImage();
        }
        
        if (key == KeyEvent.VK_UP)
        {
            moveY = 0;
            character = def.getImage();
        }
    }
}