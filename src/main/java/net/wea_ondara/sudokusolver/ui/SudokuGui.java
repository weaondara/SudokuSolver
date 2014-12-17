package net.wea_ondara.sudokusolver.ui;

import java.awt.Dimension;
import javax.swing.JFrame;

public class SudokuGui extends JFrame
{
    private SudokuPanel spanel;
    public SudokuGui()
    {
        spanel = new SudokuPanel();
        spanel.setSize(new Dimension(400, 400));
        spanel.setLocation(0, 0);
        
        this.setSize(500, 500);
        this.setLayout(null);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(spanel);
    }
}
