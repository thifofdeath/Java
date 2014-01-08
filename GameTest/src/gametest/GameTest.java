
package gametest;

import javax.swing.*;

public class GameTest
{
    static boolean newgame = false;
    
    public GameTest()
    {
        JFrame frame = new JFrame();
        frame.add(new Board());	
        frame.setTitle("2-D Test Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);// 800 for gravity test
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        //frame.addMouseListener(handler);
    }
	
    public static void main(String[] args)
    {
          new GameTest();
    }
}
// Overdrive OP superman mode

// Press P bring pause, if ** typed or pressed, new Page();
// or something like that

// Sphere rectangle or sphere, when intersects +1 Y and have a loop so it goes escalating
// A Concept like this ish
// Y++ IF  NEW X > PREVIOUS X value

// Ammo = 0 if no gun pickup // Make Gun inventory top right or next to ammo

// Entire new class where you declare/initialize images and give instructions on how to.

// Car Road rage game