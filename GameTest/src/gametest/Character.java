package gametest;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.*;

public class Character 
{
    int x, dx, y, nx2, nx, leftrev, dy;
    Image character;
    //boolean left, right;
    ImageIcon def = new ImageIcon("C:/Users/thifofdeath/Documents/Game Test JAVA/default.png");
    ImageIcon right = new ImageIcon("C:/Users/thifofdeath/Documents/Game Test JAVA/dude.png");
    ImageIcon left = new ImageIcon("C:/Users/thifofdeath/Documents/Game Test JAVA/test.png");
    ImageIcon jump = new ImageIcon("C:/Users/thifofdeath/Documents/Game Test JAVA/jump.png");
    ImageIcon jumprev = new ImageIcon("C:/Users/thifofdeath/Documents/Game Test JAVA/jumprev.png");
    
    public Character()
    {
        
        character = def.getImage();
        leftrev = 150;
        x = 75;
        nx2 = 1200;
        nx = 0;
        y = 172;
    }
   
    public void move()
    {
        if (dx != -1)
        {
            if (leftrev + dx <= 150)
                leftrev = leftrev + dx;
            else 
            {
                x = x + dx;
                nx2 = nx2 + dx;
                nx = nx + dx;
            }
        }	
        else
	{
		if (leftrev + dx >0)
		leftrev = leftrev + dx;
	}		
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
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
            dx = -1;
            character = left.getImage();
        }
        
        if (key == KeyEvent.VK_RIGHT)
        {
            dx = 1;
            character = right.getImage();
        }
        if (key == KeyEvent.VK_UP)
        {
            if (dx == 1  || key == KeyEvent.VK_UP)
            {
                dy = 1;
                character = jump.getImage();
            }
            if (dx == -1)
            {
                dy = 1;
                character = jumprev.getImage();
            }
        }
        
    }
    
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            dx = 0;
            character = def.getImage();
        }
        
        if (key == KeyEvent.VK_RIGHT)
        {
            dx = 0;
            character = def.getImage();
        }
        
        if (key == KeyEvent.VK_UP)
        {
            dy = 0;
            character = def.getImage();
        }
    }
}