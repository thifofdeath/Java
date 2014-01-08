package gametest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JPanel implements ActionListener
{
    GameTest gt;
    Timer time;
    Image img;
    
    boolean newgame = false;
    
    public MainMenu()
    {
        addKeyListener(new MC());
        setFocusable(true);
        ImageIcon Main = new ImageIcon(getClass().getResource("/images/AmyMainMenu.png"));
        img = Main.getImage();
        time = new Timer(5, this);
        time.start();
        
    }
    public void actionPerformed(ActionEvent e)
    {
        repaint();	
    }
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics g2d = (Graphics2D) g;
        g2d.drawImage(img, 0 , 0, null);
    }
    
    private class MC extends KeyAdapter
    {
        public void keyReleased(KeyEvent e)
        {
            keyReleased(e);
        }
        public void keyPressed(KeyEvent e)
        {
            keyPressed(e);
        }
    }
    
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT)
        {
            
        }
    }
    
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT)
        {
            
        }
    }
    
    
}
