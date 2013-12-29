package gametest;

import javax.swing.*;

public class GameTest {

    public static void main(String[] args) 
    {
       JFrame frame = new JFrame("2D Game");
       frame.add(new Board());
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(1200,365);
       frame.setVisible(true);
    }
}
