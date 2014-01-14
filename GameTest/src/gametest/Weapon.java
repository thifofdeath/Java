package gametest;

import java.awt.*;
import javax.swing.*;

public class Weapon 
{
    Character p = new Character();
    int x, y;
    Image img;
    boolean visible = true;
    boolean weapon = true;
    
    int width = 58;
    int height = 48;
    
    public Weapon(int startX, int startY, String image)
    {
        x = startX;
        y = startY;
        ImageIcon weapon1 = new ImageIcon(getClass().getResource(image));
        img = weapon1.getImage();       
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
    
    public boolean isVisible()
    {
        return visible;
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle(x,y, width, height);
    }
    
}
