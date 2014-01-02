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
