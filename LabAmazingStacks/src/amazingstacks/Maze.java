//The maze class. It maintains the walls between cells.
//Created by James Vanderhyde, 2 November 2011
//Modified by James Vanderhyde, 27 March 2015
//  Fixed a bug in neighbors method

package amazingstacks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author jamesvanderhyde
 */
public class Maze
{
    protected boolean[][] hwalls; 
    protected boolean[][] vwalls; 

    public Maze(int width, int height)
    {
        if (width<0 || height <0)
            throw new IllegalArgumentException("Cannot have a negative maze size.");
        
        hwalls=new boolean[width][height+1];
        vwalls=new boolean[width+1][height];
        
        //Erect all the walls
        for (int i=0; i<width; i++) for (int j=0; j<=height; j++)
            hwalls[i][j]=true;
        for (int i=0; i<=width; i++) for (int j=0; j<height; j++)
            vwalls[i][j]=true;
    }
    
    public int width()
    {
        return hwalls.length;
    }
    
    public int height()
    {
        return vwalls[0].length;
    }

    public ArrayList<Cell> neighbors(Cell c)
    {
        ArrayList<Cell> n = new ArrayList<Cell>();
        if (c.x > 0)
            n.add(new Cell(c, Cell.EAST));
        if (c.x < width() - 1)
            n.add(new Cell(c, Cell.WEST));
        if (c.y > 0)
            n.add(new Cell(c, Cell.SOUTH));
        if (c.y < height() - 1)
            n.add(new Cell(c, Cell.NORTH));
        return n;
    }
    
    public void breakWallOnSide(Cell c, int direction)
    {
        switch (direction)
        {
        case Cell.NORTH:
            hwalls[c.x][c.y]=false;
            break;
        case Cell.SOUTH:
            hwalls[c.x][c.y+1]=false;
            break;
        case Cell.WEST:
            vwalls[c.x][c.y]=false;
            break;
        case Cell.EAST:
            vwalls[c.x+1][c.y]=false;
            break;
        }
        
    }
    
    public void breakWallBetween(Cell c1, Cell c2)
    {
        if (c1.isNeighbor(c2))
        {
            int x=Math.max(c1.x, c2.x);
            int y=Math.max(c1.y, c2.y);
            if (c1.x==c2.x)
                hwalls[x][y]=false;
            else
                vwalls[x][y]=false;
        }
        else
            throw new IllegalArgumentException("Cells are not neighbors: "+c1+" "+c2);
        
    }

    public void paint(Graphics2D g2, int cellSize, int x, int y)
    {
        g2.setColor(Color.BLACK);
        for (int i = 0; i < width(); i++)
            for (int j = 0; j <= height(); j++)
                if (hwalls[i][j])
                    g2.drawLine(x + i * cellSize, y + j * cellSize, x + (i + 1) * cellSize, y + j * cellSize);
        for (int i = 0; i <= width(); i++)
            for (int j = 0; j < height(); j++)
                if (vwalls[i][j])
                    g2.drawLine(x + i * cellSize, y + j * cellSize, x + i * cellSize, y + (j + 1) * cellSize);
    }

}
