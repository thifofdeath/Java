package gametest;

import java.awt.Font;
import java.io.BufferedInputStream;
import javax.swing.*;

public class GameTest
{
    MainMenu m;
    CharSelect cs;
    Board b;
    static JFrame menu = new JFrame();
    static JFrame select = new JFrame();
    static JFrame frame = new JFrame();
    static int value = 0;
    static int value1 = 0;
    static Font fontBase;
    static Font fontRaw;
    
    public GameTest()
    {
        try 
        {
            fontRaw = Font.createFont(Font.TRUETYPE_FONT, 
                new BufferedInputStream(getClass().getClassLoader().
                    getResourceAsStream("font/customfont.ttf")));
            fontBase = fontRaw.deriveFont(Font.PLAIN, 24);
        }
        catch (Exception e)
        {}
        
        if (value <= 0)
        {
            menu.add(new MainMenu());
            menu.setTitle("Main Menu");
            menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menu.setSize(1200,800);// 800 for gravity test
            menu.setVisible(true);
            menu.setLocationRelativeTo(null);
        }
        if (value1 == 1)
        {
            menu.setVisible(false);
            menu.dispose();
            select.add(new CharSelect());
            select.setTitle("Character Select");
            select.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            select.setSize(1200,800);// 800 for gravity test
            select.setVisible(true);
            select.setLocationRelativeTo(null);
        }
        if (cs.newgame)
        {
            select.setVisible(false);
            select.dispose();
            frame.add(new Board());	
            frame.setTitle("2-D Test Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200,800);// 800 for gravity test
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
        }
        
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