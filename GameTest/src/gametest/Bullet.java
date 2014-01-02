package gametest;

import java.awt.*;
import javax.swing.*;

public class Bullet 
{
    Character p = new Character();
    static int x, y;
    Image img;
    boolean visible;
    
    public Bullet(int startX, int startY, String image)
    {
        
        x = startX;
        y = startY;
        ImageIcon newBullet = new ImageIcon(getClass().getResource(image));
        img = newBullet.getImage();
        visible = true;
    }
    
    static int getX()
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
        
            x = x + 2;
            if (x > 800)
            {
                visible = false;
            }
        
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
        return new Rectangle(x,y, 31, 8);
    }
}
