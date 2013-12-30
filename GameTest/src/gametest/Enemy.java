package gametest;

import java.awt.*;
import javax.swing.*;

public class Enemy 
{
    int x, y;
    Image img;
    boolean alive = true;
    
    public Enemy(int startX, int startY, String image)
    {
        x = startX;
        y = startY;
        ImageIcon Enemy = new ImageIcon(image);
        img = Enemy.getImage();
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
        return img;
    }
    
    public boolean isAlive()
    {
        return alive;
    }
    
    public void move(int dx, int left)
    {
        if (dx == 1 && !((left + dx )< 150)) //Added this to only move enemy 
        x = x - dx;                          // when character moves forward (not back)
    }

    public Rectangle getBounds()
    {
        return new Rectangle(x,y, 73, 78);
    }
    
}
