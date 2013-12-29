package gametest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel implements ActionListener, Runnable  
{
    Character p;
    Image img;
    Timer time;
    int v = 172;
    Thread animator;
    
    public Board()
    {
        p = new Character();
        addKeyListener(new AL());
        setFocusable(true);
        ImageIcon i = new ImageIcon("C:/Users/thifofdeath/Documents/Game Test JAVA/back.jpg");
        img = i.getImage();
        time = new Timer(5, this);
        time.start();
    }
            
    public void actionPerformed(ActionEvent e)
    {
        p.move();
        repaint();
    }
    boolean k = false;
    public void paint(Graphics g)
    {
        if (p.dy == 1 && k == false);
        {
            k = true;
            animator = new Thread(this);
            animator.start();
        }
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        
        if ((p.getX() - 75) % 2400 == 0)
            p.nx = 0;
        if ((p.getX() - 1275) % 2400 == 0)
            p.nx2 = 0;
                g2d.drawImage(img, 1200-p.nx2, 0, null);
                System.out.println(p.getX());
                System.out.println(p.getY());
                if (p.getX() >= 75)
                    g2d.drawImage(img, 1200-p.nx, 0, null);
        g2d.drawImage(p.getImage(), p.leftrev, p.getY(), null);
       
    }
    
    private class AL extends KeyAdapter
    {
        public void keyReleased(KeyEvent e)
        {
            p.keyReleased(e);
        }
        public void keyPressed(KeyEvent e)
        {
            p.keyPressed(e);
        }
    }
    
    boolean h = false;
    boolean done = false;
    
    public void cycle()
    {
        if (h == false)
            v--;
        if (v == 125)
            h = true;
        if (h == true && v <= 172)
        {
            v++;
            if (v == 172)
            {
                done = true;
            }
        }
    }
    
    public void run()
    {
        long beforeTime, timeDiff, sleep;
        
        beforeTime = System.currentTimeMillis();
        while(done == false)
        {
            cycle();
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = 10 - timeDiff;
            if (sleep < 0)
            {
                sleep = 2;
            }
            try 
            {
                Thread.sleep(sleep);
            }
            catch(InterruptedException e)
            {
                System.out.println("interrupted");
            }
            beforeTime = System.currentTimeMillis();
        }
        done = false;
        h = false;
        k = false;
    }
    
}