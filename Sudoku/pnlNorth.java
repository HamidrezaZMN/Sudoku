package Sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class pnlNorth extends JPanel implements ActionListener{
    pnlCenter pnc;
    
    JButton btnStart, btnClean;

    public pnlNorth(pnlCenter pnc) {
        this.pnc = pnc;
        btnStart = new JButton("START");
        btnClean = new JButton("CLEAN");
        
        btnStart.addActionListener(this);
        btnClean.addActionListener(this);
        add(btnStart); add(btnClean);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btnPressed = (JButton) e.getSource();
        if(btnPressed==btnStart) pnc.start();
        else pnc.clean();
    }
}