package gametest;

import java.awt.*;
import javax.swing.*;

public class Enemy 
{
    Character p = new Character();
    int x, y;
    Image img;
    boolean alive = true;
    
    public Enemy(int startX, int startY, String image)
    {
        x = startX + 800;
        y = startY;
        ImageIcon Enemy = new ImageIcon(getClass().getResource(image));
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
    
    public void move(int moveX, int CharacterPosition)
    {
        if (moveX == p.MOVESPEED && !((CharacterPosition + moveX )< 400)) //Added this to only move enemy 
        {                                    // when character moves forward (not back)
            x = x - moveX;
        } 
    }

    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 68, 68);
    }
//    public Rectangle getBounds()
//    {
//        return new Rectangle(x,y, 142, 68);
//    }
//    
//    public Rectangle getBounds2()
//    {
//        return new Rectangle(x+46, y+36, 141, 93);
//    }
//    public Rectangle getBounds2(int valuex, int valuey, int posx, int posy)
//    {
//        return new Rectangle(valuex, valuey, posx , posy);
//    }
    
}
