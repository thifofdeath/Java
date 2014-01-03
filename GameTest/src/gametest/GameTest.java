package gametest;

import javax.swing.*;

public class GameTest
{

    public GameTest()
    {
        JFrame frame = new JFrame();
        frame.add(new Board());	
        frame.setTitle("2-D Test Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,365);// 800 for gravity test
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
	
    public static void main(String[] args)
    {
	new GameTest();
    }
}
// Sphere rectangle or sphere, when intersects +1 Y and have a loop so it goes escalating
// A Concept like this ish
// Y++ IF  NEW X > PREVIOUS X value

// Ammo = 0 if no gun pickup // Make Gun inventory top right or next to ammo

// Entire new class where you declare/initialize images and give instructions on how to.

// Car Road rage game