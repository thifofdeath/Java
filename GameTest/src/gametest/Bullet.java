package gametest;

import java.awt.*;
import javax.swing.*;

public class Bullet 
{
    Character p = new Character();
    int x, y;
    Image img;
    static boolean visible;
    int bulWidth = 21;
    int bulHeight = 8;
    
    public Bullet(int startX, int startY, String image)
    {
        x = startX;
        y = startY;
        ImageIcon newBullet = new ImageIcon(getClass().getResource(image));
        img = newBullet.getImage();
        visible = true;
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
    
    public boolean getVisible()
    {
        return visible;
    }
    
    public void setVisible(boolean isVisible)
    {
        visible = isVisible;
    }
    
    public void move()
    {
        x = x + 3;
//        if (x > 1200)
//        {
//            visible = false;
//        }
        // Fail attempt to make shoot left
//        else 
//        {
//            x = x - 50;
//            if (x < 800) 
//            {
//                visible = false;
//            }
//        }
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle(x,y, bulWidth, bulHeight);
    }
}
