package gametest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JPanel implements ActionListener
{
    GameTest gt;
    Timer time;
    Image img;
    Character p;
    private static int x,y;
    static Rectangle gameBounds = new Rectangle(800, 250, 300, 58);
    static Rectangle mouse = new Rectangle(x,y,0,0);
    static boolean newgame;
    
    public MainMenu()
    {
        addMouseListener(new MC());
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
//        g2d.fillRect(800, 250, 300, 58);
//        g2d.fillRect(x,y,1,1);
    }
    
    static class MC extends MouseAdapter
    {
        public void mousePressed (MouseEvent e)
        {
            x = e.getX();
            y = e.getY();
            if (x > 800 && y > 250 && x < 1100 && y < 308)
            {
                newgame=true;
                new GameTest();
            }
        }
    }
}
    
//    public static void check()
//    {
//        contains or intersects does not work
//        if (gameBounds.contains(mouse))
//        {
//            System.out.println("test");
//        }     
//    }
//}
