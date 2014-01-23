package gametest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CharSelect extends JPanel implements ActionListener
{
    static GameTest gt;
    Timer time;
    Image img;
    static Character p;
    private static int x,y;
    static Rectangle gameBounds = new Rectangle(800, 250, 300, 58);
    static Rectangle mouse = new Rectangle(x,y,0,0);
    static boolean newgame;
    Font fontBase;
    static Font font = new Font("SanSerif", Font.BOLD, 20);
    
    public CharSelect()
    {
        addMouseListener(new MC());
        setFocusable(true);
        ImageIcon charselect = new ImageIcon(getClass().getResource("/images/charselect.png"));
        img = charselect.getImage();
        time = new Timer(5, this);
        time.start();
//        fontBase = gt.fontRaw.deriveFont(Font.PLAIN, 18); TOO BLURRY, HERE JUST IN CASE
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
        g2d.setColor(Color.white);
        g2d.setFont(font);
        g2d.drawString("Male Character", 450, 225);
        g2d.drawString("Female Character", 650, 225);
        g2d.fillRect(450, 250, 150, 200);
        g2d.fillRect(650, 250, 150, 200);
        
//        g2d.fillRect(x,y,1,1);
    }
    
    static class MC extends MouseAdapter
    {
        public void mousePressed (MouseEvent e)
        {
            x = e.getX();
            y = e.getY();
            
            if (x > 450 && y > 250 && x < 600 && y < 450)
            {
                p.male=true;
                gt.value1++;
                newgame=true;
                new GameTest();
            }
            else if (x > 650 && y > 250 && x < 800 && y < 450)
            {
                p.female = true;
                gt.value1++;
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
