package Sudoku;

import java.awt.BorderLayout;
import javax.swing.*;

public class MainClass {

    public MainClass(){
        JFrame f = new JFrame();
        f.setName("Soduko");
        f.setSize(600, 600);
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        pnlCenter pnc = new pnlCenter();
        pnlNorth pnn = new pnlNorth(pnc);
        f.add(pnc, BorderLayout.CENTER);
        f.add(pnn, BorderLayout.NORTH);

        f.setVisible(true);
    }

    public static void main(String[] args){
        new MainClass();
    }
}