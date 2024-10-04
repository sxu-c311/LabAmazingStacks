//A maze that is created by carving out cells
//Created by James Vanderhyde, 2 November 2011
//Modified by James Vanderhyde, 27 March 2015
//  Changed parameter order on carve method to be more intuitive
//  Added parameter to carveMazeUsingStack to make checking the end easier

package amazingstacks;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author jamesvanderhyde
 */
public class CarvedMaze extends Maze
{
    private boolean[][] carved;
    
    public CarvedMaze(int width, int height)
    {
        super(width,height);
        
        //Everything starts out uncarved (false)
        carved=new boolean[width][height];
    }
    
    @Override
    public void paint(java.awt.Graphics2D g2, int cellSize, int x, int y)
    {
        //Fill the carved and uncarved cells a different color
        for (int i = 0; i < width(); i++)
            for (int j = 0; j < height(); j++)
            {
                if (carved[i][j])
                    g2.setColor(Color.LIGHT_GRAY);
                else
                    g2.setColor(Color.DARK_GRAY);
                g2.fillRect(x + i * cellSize, y + j * cellSize, cellSize, cellSize);
            }
        
        //Draw the walls
        super.paint(g2, cellSize, x, y);
    }

    /**
     * Carves out a cell as part of maze. Also breaks down the wall between two cells.
     * @param from the cell we're coming from, where the wall is to be broken.
     *             If null, no walls are broken.
     * @param dest the destination cell, the cell to carve out
     * @throws IllegalArgumentException if dest is null, or if dest is already carved.
     */
    public void carve(Cell from, Cell dest)
    {
        if (dest==null)
            throw new IllegalArgumentException("Cell to carve is null.");
        if (carved[dest.x][dest.y])
            throw new IllegalArgumentException("Cell already carved: "+dest);
        
        if (from != null)
        {
            //break down wall
            breakWallBetween(dest, from);
        }

        //carve out the cell
        carved[dest.x][dest.y]=true;
    }
    
    public boolean canCarve(Cell c)
    {
        //Check boundary
        if (c.x<0 || c.x>=width() || c.y<0 || c.y>=height())
            return false;
        
        //Otherwise, can carve only if not already carved.
        return !carved[c.x][c.y];
    }

    /**
     * Looks at all the neighbors of the given cell and returns one that is not carved.
     * @param c the cell to look at
     * @return a random uncarved neighbor, or null if none exist.
     */
    public Cell randomUncarvedNeighbor(Cell c)
    {
        ArrayList<Cell> neighbors=neighbors(c);
        java.util.Iterator<Cell> it=neighbors.iterator();
        while (it.hasNext())
        {
            Cell n=it.next();
            if (!canCarve(n))
                it.remove();
        }
        if (neighbors.size()>0)
        {
            int r=(int)(Math.random()*neighbors.size());
            return neighbors.get(r);
        }
        else
            return null;
    }
    
    /**
     * Carves a maze from a starting cell to an ending cell. Breaks down the
     * wall between the start cell and the outside and the wall between the
     * end cell and the outside.
     * (The main work of carving is performed by the carveMazeUsingStack
     * method).
     * @param start The cell to start carving from. Must be inside the maze.
     * @param end The cell to stop carving at. Must be inside the maze.
     */
    public void carveRandomMaze(Cell start, Cell end)
    {
        //Check that the starting cell is inside the maze
        if (start.x<0 || start.x>=width() ||
            start.y<0 || start.y>=height())
            throw new IllegalArgumentException("Start cell "+start+" not inside the maze.");
        
        //Open walls to the start from the outside
        if (start.x==0)
            breakWallOnSide(start,Cell.WEST);
        else if (start.x==width()-1)
            breakWallOnSide(start,Cell.EAST);
        else if (start.y==0)
            breakWallOnSide(start,Cell.NORTH);
        else if (start.y==height()-1)
            breakWallOnSide(start,Cell.SOUTH);
        
        //Carve out the maze, starting at given cell
        carveMazeUsingStack(start, end);
        
        //Check that the ending cell is inside the maze
        if (end.x>=0 && end.x<width() &&
            end.y>=0 && end.y<height())
        {
            //Open walls to the end from the outside
            if (end.x==width()-1)
                breakWallOnSide(end,Cell.EAST);
            else if (end.x==0)
                breakWallOnSide(end,Cell.WEST);
            else if (end.y==height()-1)
                breakWallOnSide(end,Cell.SOUTH);
            else if (end.y==0)
                breakWallOnSide(end,Cell.NORTH);
        }
    }

    /**
     * Uses a stack to carve out a random maze
     * @param start the cell to start carving from
     * @param end the cell adjacent to the maze exit
     */
    private void carveMazeUsingStack(Cell start, Cell end)
    {
        //student implementation
    }
}
