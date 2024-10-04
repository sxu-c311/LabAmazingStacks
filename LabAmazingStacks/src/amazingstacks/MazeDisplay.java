//A class for creating a maze and drawing it on screen
//Created by James Vanderhyde, 2 November 2011

package amazingstacks;

import java.awt.Graphics2D;

/**
 *
 * @author jamesvanderhyde
 */
public class MazeDisplay extends AnimationComponent
{
    private static final int MAZE_HEIGHT = 9;
    private static final int MAZE_WIDTH = 12;
    
    private CarvedMaze m;
    
    public MazeDisplay()
    {
        Cell entrance = new Cell(0,0);
        Cell exit = new Cell(MAZE_WIDTH-1,MAZE_HEIGHT-1);
        
        m = new CarvedMaze(MAZE_WIDTH, MAZE_HEIGHT);
        m.carveRandomMaze(entrance,exit);
    }

    @Override
    public void paint(Graphics2D g2)
    {
        //Draw the maze with grid size 16 pixels, starting at pixel location (8,8)
        m.paint(g2, 16, 8, 8);
    }

}
