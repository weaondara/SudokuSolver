package net.wea_ondara.sudokusolver.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import lombok.Getter;
import net.wea_ondara.sudokusolver.Sudoku;

public class SudokuPanel extends JPanel
{

    private final static float BORDER_WIDTH = 3;
    private final static float BOX_BORDER_WIDTH = 2;
    private final static float LINE_WIDTH = 1;

    @Getter
    private Sudoku sudoku;
    private Point selected;
    
    public SudokuPanel()
    {
        sudoku = new Sudoku();
        for (int x = 0; x < 9; x++)
        {
            for (int y = 0; y < 9; y++)
            {
                sudoku.set(x, y, y + 1);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //bg
        g.setColor(this.getBackground());
        g.drawRect(0, 0, getWidth(), getHeight());

        //lines
        drawField(g2d);

        //numbers
        drawNumbers(g2d);
    }

    private void drawField(Graphics2D g)
    {
        g.setColor(Color.BLACK);

        //vertical
        float cellsize = cellWidth();
        float add = BORDER_WIDTH;
        for (int x = 1; x < 9; x++)
        {
            //box border
            if ((x + 0) % 3 == 0)
            {
                g.setStroke(new BasicStroke(BOX_BORDER_WIDTH));
                g.drawLine((int) (x * cellsize + add), 0, (int) (x * cellsize + add), getHeight());
                add += BOX_BORDER_WIDTH;
            }
            //lines
            else
            {
                g.setStroke(new BasicStroke(LINE_WIDTH));
                g.drawLine((int) (x * cellsize + add), 0, (int) (x * cellsize + add), getHeight());
                add += LINE_WIDTH;
            }
        }

        //horizontal
        cellsize = cellHeight();
        add = BORDER_WIDTH;
        for (int y = 1; y < 9; y++)
        {
            //box border
            if ((y + 0) % 3 == 0)
            {
                g.setStroke(new BasicStroke(BOX_BORDER_WIDTH));
                g.drawLine(0, (int) (y * cellsize + add), getWidth(), (int) (y * cellsize + add));
                add += BOX_BORDER_WIDTH;
            }
            //lines
            else
            {
                g.setStroke(new BasicStroke(LINE_WIDTH));
                g.drawLine(0, (int) (y * cellsize + add), getWidth(), (int) (y * cellsize + add));
                add += LINE_WIDTH;
            }
        }

        //border
        g.setStroke(new BasicStroke(BORDER_WIDTH));
        g.drawRect((int) (BORDER_WIDTH / 2), (int) (BORDER_WIDTH / 2),
                   (int) (getWidth() - BORDER_WIDTH), (int) (getHeight() - BORDER_WIDTH));
    }

    private void drawNumbers(Graphics2D g)
    {
        FontMetrics fm = g.getFontMetrics(getFont());
        int height = fm.getHeight() - fm.getDescent();
        for (int x = 0; x < 9; x++)
        {
            for (int y = 0; y < 9; y++)
            {
                int val = sudoku.get(x, y);
                if (val == 0)
                {
                    continue;
                }

                int width = fm.stringWidth("" + val);

                float px = calcX(x) + (cellWidth() - width) / 2;
                float py = calcX(y) + (cellHeight() + height) / 2;

                g.drawString("" + val, px, py);
            }
        }
    }

    private boolean isSelected(int x, int y)
    {
        if (selected == null)
        {
            return false;
        }
        return selected.x == x && selected.y == y;
    }

    private float cellWidth()
    {
        return (getWidth() - 2 * BORDER_WIDTH - 2 * BOX_BORDER_WIDTH - 6 * LINE_WIDTH) / 9F;
    }
    private float cellHeight()
    {
        return (getHeight() - 2 * BORDER_WIDTH - 2 * BOX_BORDER_WIDTH - 6 * LINE_WIDTH) / 9F;
    }
    private float calcX(int column)
    {
        float x = BORDER_WIDTH;
        float cellsize = cellWidth();

        if (column > 5)
        {
            x += 2 * BOX_BORDER_WIDTH + (column - 2) * LINE_WIDTH;
        }
        else if (column > 2)
        {
            x += 1 * BOX_BORDER_WIDTH + (column - 1) * LINE_WIDTH;
        }
        else
        {
            x += (column - 1) * LINE_WIDTH;
        }
        
        x += (column) * cellsize;

        return x;
    }
}
