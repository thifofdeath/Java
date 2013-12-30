package gametest;

import java.awt.*;
import javax.swing.*;

public class Bullet 
{
    int x, y;
    Image img;
    boolean visible;
    
    public Bullet(int startX, int startY, String image)
    {
        x = startX;
        y = startY;
        ImageIcon newBullet = new ImageIcon(image);
        img = newBullet.getImage();
        visible = true;
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle(x,y, 31, 8);
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
    
    public void move()
    {
        x = x + 2;
        if ( x > 700)
        {
            visible = false;
        }
    }

    public void setVisible(boolean isVisible)
    {
            visible = isVisible;
    }
}
