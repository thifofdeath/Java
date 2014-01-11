package gametest;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Board extends JPanel implements ActionListener// MouseListener //, Runnable  
{
    // Thread animator;
    Image img, menu;
    Image pistol;
    Timer time;
    MainMenu m;
    Character p;
 
    Bullet bul;
    Enemy en, en2;
    Weapon wp, wph;
//    static int v = 172;
    
    boolean lost = false;
//    boolean k = false;
    
    int mobX1, mobX2 , randomX;
    int ran1 = 601;
    int ran2 = 600;
    int mobY;
    boolean reviver = true;
    int generation = 2;
    int count=0;
    int kill=0;
    boolean enable = true, enable2 = true;
    
    // int max = 1200;
    // int range = (max) + 1;
//    boolean mainmenu = true;

    static Font font = new Font("SanSerif", Font.BOLD, 24);
    static Font font1 = new Font("SanSerif", Font.BOLD, 60);
    
    // Custom font
//    Font fontRaw = Font.createFont(Font.TRUETYPE_FONT, 
//            new BufferedInputStream(getClass().getClassLoader().
//                getResourceAsStream("resources/fonts/slkscr.ttf")));
//        Font fontBase = fontRaw.deriveFont(Font.PLAIN, 20);

    
    public Board()
    {
        p = new Character();
        addKeyListener(new AL());
        setFocusable(true);
        ImageIcon i = new ImageIcon(getClass().getResource("/images/background.png"));
        img = i.getImage();
        time = new Timer(5, this);
        time.start();
                                                     
        en = new Enemy(mobX1, Math(), ("/images/enemy.png"));  // OR p.bul or p.getBul();  
	en2 = new Enemy(mobX2, Math(), ("/images/enemy.png")); // WHERE p = Character;
//        ImageIcon pistol1 = new ImageIcon(getClass().getResource("/images/pistol.png"));
//        pistol = pistol1.getImage();   // Previous and default version of inputting picture
        wp = new Weapon(0, 688, ("/images/pistol.png"));
        wph = new Weapon(0, 688, ("/images/pistolgone.png"));
        // (10 - p.getValueX()) instead of 0, if you want to be more precise.
    }
            
    public void actionPerformed(ActionEvent e)
    {
        p.jump();
        p.move();
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
        
        // Enemy moves with you when you move backwards.
        if (p.valueX > mobX1)
        {
            en.move(p.getMoveX(), p.getChar());
        }
        if (p.valueX > mobX2)
        {
            en2.move(p.getMoveX(), p.getChar());
        }
        // *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* \\
        
        repaint();	
    }
    
    public void randomGenerator()
    {
//        if (!en.isAlive())
//        {
//            ran1+=40;
//            ran2+=40;
//        }
        if (generation > 0)
        {
            if (generation == 2)
            {
                randomX = (int)(Math.random() * ran1 + ran2);
                mobX1 = randomX;
                if (count <= 0)
                {  
                    generation--;
                }
                else
                {
                    en = new Enemy(mobX1, Math(), ("/images/enemy.png"));
                    generation -=2;
                }
            }
            else if (generation == 1)
            {
                randomX = (int)(Math.random() * ran1 + ran2);
                mobX2 = randomX;
                if (count > 0)
                {
                    en2 = new Enemy(mobX2, Math(), ("/images/enemy.png"));
                }
                if (count == 0)
                {

                }
                generation--;
            }
        }
    }
    
    public final int Math()
    {
        mobY = (int)(Math.random() * 550 + 400);
//        mobY = (int)(Math.random() * 365);
//        if (mobY > 200) 198 to be more precise // Use this if you want more complicated
//        {
//            Math();
//        }
        return mobY;
    }

    // Collision detection, bullet to enemy / body and arm to enemy
    public void checkCollisions()
    {
	Rectangle firstMob = en.getBounds();
	Rectangle secondMob = en2.getBounds();
        // (en.getX() + 46, en.getY() + 36 , 141, 93)
        
	ArrayList bullets = Character.getBullets();
	for (int w = 0; w < bullets.size(); w++)
	{
		Bullet blt = (Bullet) bullets.get(w);
		Rectangle bulletrect = blt.getBounds();
		if (firstMob.intersects(bulletrect) && en.isAlive())
		{
			en.alive = false;
			blt.visible = false;
                        lost = false;
                        reviver = true;
                        generation=2;
                        count++;
                        kill++;
                }
//                      if (en2.isAlive() == false && en.isAlive())
//                      {
//                          reviver = true;
//                          generation = 2;
//                          ran1 += 601;
//                          ran2 += 600;
//                      }
		else if (secondMob.intersects(bulletrect)&& en2.isAlive())
		{
			en2.alive = false;
			blt.visible = false;
                        lost = false;
                        reviver = true;
                        count++;
                        generation=1;
                        kill++;
                }
//                      if (en.isAlive() == false && en2.isAlive())
//                      {
//                          reviver = true;
//                          generation = 2;
//                          ran1 += 601;
//                          ran2 += 600;
//                      }
	}	
        
        // Body and arm detector
        Rectangle body = p.getBounds(p.CharacterPos, p.valueY, 74, 186);
        Rectangle arm = p.getBounds(p.CharacterPos + 61, p.valueY + 26, 62, 26);
	if (body.intersects(firstMob) && en.alive == true || body.intersects(secondMob) && en2.alive == true)
        {
            if (body.intersects(firstMob) && enable)
            {
                int minus=5;
                en.alive = false;
                reviver = true;
                count++;
                generation=1;
                p.health-=minus;
                enable = false;
                
                
            }
            else if (body.intersects(secondMob) && enable2)
            {
                int minus=5;
                en2.alive = false;
                reviver = true;
                count++;
                generation=1;
                p.health-=minus;
                enable2 = false;
            }
        }
        else if (arm.intersects(firstMob) && en.alive == true  && wp.weapon == false && p.weaponpickup1 == true
                || arm.intersects(secondMob) && en2.alive == true && wp.weapon == false && p.weaponpickup1 == true)
        {
            if (arm.intersects(firstMob) && enable)
            {
                int minus=5;
                en.alive = false;
                reviver = true;
                count++;
                generation=1;
                p.health-=minus;
                enable = false;
            }
            
            else if (arm.intersects(secondMob) && enable2)
            {
                int minus=5;
                en2.alive = false;
                reviver = true;
                count++;
                generation=1;
                p.health-=minus;
                enable2= false;
            }
        }
	// *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* \\
    }
    
    public void paint(Graphics g)
    {
        System.out.println(p.getValueY() + " " + p.getValueX() + " " + p.getMoveY() + " " + p.getMoveX()
                + ""  + " " + mobX1 + " " + mobX2 + " " + generation + " " + p.getChar() + " " + p.health);
        randomGenerator();  
//        if (p.moveY == 1 && k == false);
//        {
//            k = true;
//            animator = new Thread(this);
//            animator.start();
//        }
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
  
//        if (mainmenu)
//        {
//            g2d.drawImage(menu, 0, 0, null);
//        }
//        else 
//        if (m.newgame)
        {
            // Loops background so background never disappears
            if ((p.getValueX() - 10) % 2400 == 0)
            {
                p.FrameHeightTerrain = 0;
            }

            if ((p.getValueX() - 1210) % 2400 == 0)
            {            
                p.FrameLength = 0;
            }

            g2d.drawImage(img, 1200 - p.getFrameLength(), 0, null);

            if (p.getValueX() >= 10)
            {
                g2d.drawImage(img, 1200-p.getFrameHeight(), 0, null);
            }
            g2d.drawImage(p.getImage(), p.CharacterPos, p.getValueY(), null);

            if (p.getMoveX() == -3) 
            {	
                g2d.drawImage(img, 1200 - p.getFrameLength(), 0, null);
                g2d.drawImage(p.getImage(), p.CharacterPos, p.getValueY(), null);
            }
            
            // *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* \\

            //if (p.getValueY() >)

            // Bullet initializer
            ArrayList bullets = Character.getBullets();
            for (int w = 0; w < bullets.size(); w++)
            {   
                Bullet m = (Bullet) bullets.get(w);
                if (m.getVisible())
                {
                    g2d.drawImage(m.getImage(),m.getX(), m.getY(), null);
                }
            }
            // Ammo and weapons you currently have
            g2d.setFont(font);
            g2d.setColor(Color.WHITE);
            g2d.drawString("Health: " + p.health , 10, 25);
            g2d.drawString("Ammo: " + p.ammo, 10, 55);
            g2d.drawString("Kills: " + kill, 250, 25);
            // *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* \\

            // When enemy spawns
            boolean enemyactivated = false;
            boolean enemyactivated2 = false;
            if (p.valueX > mobX1)
            {
                if (en.isAlive() == true)
                {
                    g2d.drawImage(en.getImage(),en.getX(), en.getY(), null);
                    enemyactivated = true;
                    enable= true;
                }
            }
            if (p.valueX > mobX2)
            {
                if (en2.isAlive() == true)
                {
                    g2d.drawImage(en2.getImage(), en2.getX(), en2.getY(), null);
                    enemyactivated2 = true;
                    enable2 = true;
                }
            }

            if (enemyactivated)
            {
                en.x-= 2;
            }
            if (enemyactivated2)
            {
                en2.x-=2;
            }
            // *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* \\

            if (p.getChar() > p.getValueX() - 1200)
            {
                //g2d.drawImage(pistol, (10 - p.getValueX()), 279, null);
                if (wp.isVisible() == true)
                {
                    g2d.drawImage(wp.getImage(), wp.getX(), wp.getY(), null);
                }   
            }
            if (p.getChar() < p.getValueX() - 1200)
            {
                wp.visible = false;
            }

            // Weapon activator and detector
            Rectangle wprect = new Rectangle (wp.getBounds());
            Rectangle chrect = new Rectangle (p.defBody());
            if (chrect.intersects(wprect) && wp.weapon == true && wp.visible == true)
            {
                wp.weapon = false;
                p.weaponswitch1 = true;
                p.weaponpickup1 = true;
                // p.character = p.weaponhold.getImage();
                wp.img = wph.getImage();
                wp.visible = false;
                p.ammo = 25;
            }
    //            if (!wp.weapon) 
    //            {
    //                if (!wp.weapon && wp.visible)
    //                {
    //                    if (p.getValueX() == 150);
    //                    {
    //                        System.out.println("lol");
    //                        wp.visible = false;        
    //                    }
    //                }
    //            }
            // *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* \\

            // Enemy goes past screen behind you, resets counter so you can see enemies again
            if (en.getX() < p.getChar() - 198 ) // 150 + length of enemy (48 in this case)
            {
                en.alive = false;	
                lost = false;
                reviver = true;
                generation=2;
                count++;
            }
            if (en2.getX() < p.getChar() - 198)
            {
                en2.alive = false;
                lost = false;
                reviver = true;
                generation=1;
                count++;
            }
            // *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* \\

            // What happens when you lose.
            if (p.health <= 0)
            {
                lost = true;
                time.stop();
                g2d.setFont(font1);
                g2d.setColor(Color.WHITE);
                g2d.drawString("GAME OVER", 400, 400);
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
//    
//    public void mouseEntered(MouseEvent e)
//    {
//        mainmenu=false;
//    }
}

              // *-*-*-* JUMPING GRAVITY CYCLE LOOP *-*-*-* \\
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
    
    // *-*-*-* OLD GENERATOR *-*-*-* \\
//    public void MobGenerator()
//    {
//        // THIS IS THE SECOND MOB POSITION
//        if (generation == 1)
//        {
//            mobX2 = randomX;
//            if (mobX1 > mobX2)
//            {
//                generation = 2;
//                count++;
//                randomGenerator();
//            }
//            else 
//            {    
//                reviver=false;
//            }
//        }
//        // THIS IS THE FIRST MOB POSITION
//        if (generation == 2)
//        {
//            mobX1 = randomX;
//            // this WILL stop if the SECOND mob is still alive
//            // Making the generator loop to the SECOND mob
//            if (en2.isAlive() && en.isAlive() == false)
//            {
//                reviver=false;
//            }
//        }
//    }
//    
//    public void randomGenerator()
//    {   
//        for (int i = 1; generation >= i;)
//        {
//        //  if (generation == 0)
//        //  {
//        //      break;
//        //  }
//            if (reviver)
//            {
//                if (count > 0)
//                {
//                    if (generation == 1)
//                    {
//                        int number1 = ran1;
//                        int number2 = ran2;
//                        //int ran3;
//                      
//                        mobX1 = (int)(Math.random() * number1 + number2);
//                        
//                    }
////                    if (generation == 2 && !en2.isAlive())
////                    {
////                        ran1+=151;
////                        ran2+=150;
////                    }
//                }
//                
//                    randomX = (int)(Math.random() * ran1 + ran2);
//                    MobGenerator();
//                    // int max=5, min1=2; **THIS IS NOT IDIOT PROOF**
//                    // int range = (max - min1) + 1;     
//                    // int random = (int)(Math.random() * range) + min1;
//                    // IF number == 0 then + min1 == 2
//                    // 
//                    // (int)(Math.random() * 5 + 2; apparently does the same thing
//                    generation--;   
//                
//            }
//        }
//    }