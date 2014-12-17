package net.wea_ondara.sudokusolver;

import com.google.common.base.Preconditions;

public class Sudoku 
{
    private int[][] numbers;
    private boolean[][][] notes;
    
    public Sudoku()
    {
        numbers = new int[9][9];
        notes = new boolean[9][9][9];
    }
    
    public int get(int x, int y)
    {
        Preconditions.checkPositionIndex(x, 9, "x must be between 0 and 9");
        Preconditions.checkPositionIndex(y, 9, "x must be between 0 and 9");
        return numbers[x][y];
    }
    public void set(int x, int y, int number)
    {
        Preconditions.checkPositionIndex(x, 9, "x must be between 0 and 9");
        Preconditions.checkPositionIndex(y, 9, "x must be between 0 and 9");
        Preconditions.checkPositionIndex(number + 1, 10, "x must be between 1 and 9");
        
        numbers[x][y] = number;
    }
    public boolean[] getNotes(int x, int y)
    {
        Preconditions.checkPositionIndex(x, 9, "x must be between 0 and 9");
        Preconditions.checkPositionIndex(y, 9, "x must be between 0 and 9");
        return notes[x][y];
    }
    public void setNote(int x, int y, int number, boolean set)
    {
        Preconditions.checkPositionIndex(x, 9, "x must be between 0 and 9");
        Preconditions.checkPositionIndex(y, 9, "x must be between 0 and 9");
        Preconditions.checkPositionIndex(number + 1, 10, "x must be between 1 and 9");
        
        notes[x][y][number] = set;
    }
    public void clearNotes(int x, int y)
    {
        Preconditions.checkPositionIndex(x, 9, "x must be between 0 and 9");
        Preconditions.checkPositionIndex(y, 9, "x must be between 0 and 9");
        notes[x][y] = new boolean[9];
    }
}
