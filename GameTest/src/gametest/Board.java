package gametest;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Board extends JPanel implements ActionListener //, Runnable  
{
    Thread animator;
    public Image img;
    Timer time;
    
    Character p;
    Bullet bul;
    Enemy en;
    Enemy en2;
//    static int v = 172;
    
    boolean lost = false;
//    boolean k = false;
    
    int mobX1, mobX2 , randomX;
    int ran1 = 601;
    int ran2 = 600;
    boolean reviver = true;
    int generation = 2;
    int count=0;
   
    // int max = 1200;
    // int range = (max) + 1;

    static Font font = new Font("SanSerif", Font.BOLD, 24);
    static Font font1 = new Font("SanSerif", Font.BOLD, 60);
    
    public Board()
    {
        p = new Character();
        addKeyListener(new AL());
        setFocusable(true);
        ImageIcon i = new ImageIcon(getClass().getResource("/images/back.jpg"));
        img = i.getImage();
        time = new Timer(5, this);
        time.start();
                                                     
        en = new Enemy(mobX1, 200, ("/images/enemy.png"));  // OR p.bul or p.getBul();  
	en2 = new Enemy(mobX2, 200, ("/images/enemy.png")); // WHERE p = Character;
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

        if (p.valueX > mobX1)
        {
            en.move(p.getMoveX(), p.getChar());
        }
        if (p.valueX > mobX2)
        {
            en2.move(p.getMoveX(), p.getChar());
        }
        
        repaint();
		
    }
    
    public void MobGenerator()
    {
        // THIS IS THE SECOND MOB POSITION
        if (generation == 1)
        {
            mobX2 = randomX;
            if (mobX1 > mobX2)
            {
                generation = 2;
                count++;
                randomGenerator();
            }
            else 
            {    
                reviver=false;
            }
        }
        // THIS IS THE FIRST MOB POSITION
        if (generation == 2)
        {
            mobX1 = randomX;
            // this WILL stop if the SECOND mob is still alive
            // Making the generator loop to the SECOND mob
            if (en2.isAlive() && en.isAlive() == false)
            {
                reviver=false;
            }
        }
    }
    
    public void randomGenerator()
    {   
        for (int i = 1; generation >= i;)
        {
        //  if (generation == 0)
        //  {
        //      break;
        //  }
            if (reviver)
            {
                if (count > 0)
                {
                    if (generation == 1)
                    {
                        mobX2 = (int)(Math.random() * ran1 + ran2);
                    }
                    if (generation == 2 && !en.isAlive())
                    {
                        ran1+=301;
                        ran2+=300;
                    }
                }
                randomX = (int)(Math.random() * ran1 + ran2);
                MobGenerator();
                // int max=5, min1=2; **THIS IS NOT IDIOT PROOF**
                // int range = (max - min1) + 1;     
                // int random = (int)(Math.random() * range) + min1;
                // IF number == 0 then + min1 == 2
                // 
                // (int)(Math.random() * 5 + 2; apparently does the same thing
                generation--;                
            }
        }
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
                        reviver=true;

		}
		else if (r2.intersects(m1)&& en2.isAlive())
		{
			en2.alive = false;
			m.visible = false;
                        lost = false;
                        reviver=true;
//                        generation=2;
//                        count++;
//                      if (en.isAlive() == false && en2.isAlive())
//                      {
//                          reviver = true;
//                          generation = 2;
//                          ran1 += 601;
//                          ran2 += 600;
//                      }
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
        randomGenerator();
        if (lost)
        {
            System.exit(0);
        }
        
//        if (p.moveY == 1 && k == false);
//        {
//            k = true;
//            animator = new Thread(this);
//            animator.start();
//        }
        
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
                + " " + bul.getX() + " " + mobX1 + " " + mobX2 + " " + generation);
        //if (p.getValueY() >)
       
        ArrayList bullets = Character.getBullets();
        for (int w = 0; w < bullets.size(); w++)
        {   
            Bullet m = (Bullet) bullets.get(w);
            if (m.getVisible())
            {
                g2d.drawImage(m.getImage(),m.getX(), m.getY(), null);
            }
        }
        
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Ammo left: " + p.ammo, 10, 25);
        if (p.valueX > mobX1)
        {
            if (en.isAlive() == true)
            {
                g2d.drawImage(en.getImage(), en.getX(), en.getY(), null);
            }
        }
        if (p.valueX > mobX2)
        {
            if (en2.isAlive() == true)
            {
                g2d.drawImage(en2.getImage(), en2.getX(), en2.getY(), null);
            }
        }
        
        if (en.getX() < p.getChar() - 150)
        {
            en.alive = false;
            lost = false;
            reviver=true;
            generation=2;
            count++;
        }
        if (en2.getX() < p.getChar() - 150)
        {
            en2.alive = false;
            lost = false;
            reviver=true;
            generation=2;
            count++;
        }
        
        if (!en.isAlive() && !en2.isAlive())
        {
            
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
//    
//    boolean h = false;
//    boolean done = false;
//    
//   public void cycle() 
//   {
//        if (h == false)
//        {
//            v--;
//        }
//        if (v == 125)
//        {
//                h = true;
//        }
//        if (h == true && v <= 172) 
//        {
//                v++;
//                if (v == 172) 
//                {
//                        done = true;
//                }
//        }
//    }
//
//	public void run() 
//        {
//
//		long beforeTime, timeDiff, sleep;
//
//		beforeTime = System.currentTimeMillis();
//
//		while (done == false) 
//                {
//
//                    cycle();
//
//                    timeDiff = System.currentTimeMillis() - beforeTime;
//                    sleep = 10 - timeDiff;
//
//                    if (sleep < 0)
//                    {
//                            sleep = 2;
//                    }
//                    try 
//                    {
//                            Thread.sleep(sleep);
//                    } 
//                    catch (InterruptedException e) 
//                    {
//                            System.out.println("interrupted");
//                    }
//
//                    beforeTime = System.currentTimeMillis();
//		}
//		done = false;
//		h = false;
//		k = false;
//	}
}