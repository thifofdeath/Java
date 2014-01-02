package gametest;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Board extends JPanel implements ActionListener, Runnable  
{
    Thread animator;
    public Image img;
    Timer time;
    
    Character p;
    Bullet bul;
    Enemy en;
    Enemy en2;
    static int v = 172;
    
    boolean lost = false;
    boolean k = false;
    
    static Font font = new Font("SanSerif", Font.BOLD, 24);
    static Font font1 = new Font("SanSerif", Font.BOLD, 60);
    
    public Board()
    {
        p = new Character();
        addKeyListener(new AL());
        setFocusable(true);
        ImageIcon i = new ImageIcon("back.jpg");
        img = i.getImage();
        time = new Timer(5, this);
        time.start();
        
        bul = new Bullet(350, 210, "bullet.png");
        en = new Enemy(700, 200, "enemy.png");
	en2 = new Enemy(700, 200, "enemy.png");
        
    }
            
    public void actionPerformed(ActionEvent e)
    {
        p.jump();
        checkCollisions();
        ArrayList bullets = Character.getBullets();
        for (int w = 0; w < bullets.size(); w++)
        {
            Bullet m = (Bullet) bullets.get(w);
            if (m.getVisible() == true)
            {
                m.move();
            }
            else
            {
                bullets.remove(w);
            }
        }
        p.move();

        if (p.valueX > 400)
        {
            en.move(p.getMoveX(), p.getChar());
        }
        if (p.valueX > 500)
        {
            en2.move(p.getMoveX(), p.getChar());
        }
        repaint();
		
    }

    public void checkCollisions()
    {
	Rectangle r1 = en.getBounds();
	Rectangle r2 = en2.getBounds();
        
	ArrayList bullets = Character.getBullets();
	for (int w = 0; w < bullets.size(); w++)
	{
		Bullet m = (Bullet) bullets.get(w);
		Rectangle m1 = m.getBounds();
		if (r1.intersects(m1) && en.isAlive())
		{
			en.alive = false;
			m.visible = false;
                        lost = false;
		}
		else if (r2.intersects(m1)&& en2.isAlive())
		{
			en2.alive = false;
			m.visible = false;
                        lost = false;
		}
	}
	
	Rectangle d = p.getBounds();
	if (d.intersects(r1) && en.alive == true || d.intersects(r2) && en2.alive == true)
        {
		lost = true;
        }
	
    }
    
    public void paint(Graphics g)
    {
        if (lost)
        {
            System.exit(0);
        }
        
        if (p.moveY == 1 && k == false);
        {
            k = true;
            animator = new Thread(this);
            animator.start();
        }
        
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        // Loops background so background never disappears
        if ((p.getValueX() - 10) % 2400 == 0)
        {
            p.FrameHeightTerrain = 0;
        }
        
        if ((p.getValueX() - 1210) % 2400 == 0)
        {            
            p.FrameLength = 0;
        }
        // End of Background loop
        g2d.drawImage(img, 1200 - p.getFrameLength(), 0, null);
        
        if (p.getValueX() >= 10)
        {
            g2d.drawImage(img, 1200-p.getFrameHeight(), 0, null);
        }
        g2d.drawImage(p.getImage(), p.CharacterPos, p.getValueY(), null);
        
        if (p.getMoveX() == -1) 
        {	
            g2d.drawImage(img, 1200 - p.getFrameLength(), 0, null);
            g2d.drawImage(p.getImage(), p.CharacterPos, p.getValueY(), null);
        }
        System.out.println(p.getValueY() + " " + p.getValueX() + " " + p.getMoveY() + " " + p.getMoveX()
                + " " + " ");
        //if (p.getValueY() >)
       
        ArrayList bullets = Character.getBullets();
        for (int w = 0; w < bullets.size(); w++)
        {   
            Bullet m = (Bullet) bullets.get(w);
            if (m.getVisible())
            g2d.drawImage(m.getImage(),m.getX(), m.getY(), null);
        }
        g2d.setFont(font);
        g2d.setColor(Color.BLUE);
        g2d.drawString("Ammo left: " + p.ammo, 500, 20);
        
        if (p.valueX > 400)
        {
            if (en.isAlive() == true)
            {
                g2d.drawImage(en.getImage(), en.getX(), en.getY(), null);
            }
        }
        if (p.valueX > 500)
        {
            if (en2.isAlive() == true)
            {
                g2d.drawImage(en2.getImage(), en2.getX(), en2.getY(), null);
            }
        }
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
        {
            v--;
        }
        if (v == 125)
        {
                h = true;
        }
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

		while (done == false) 
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
                    catch (InterruptedException e) 
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