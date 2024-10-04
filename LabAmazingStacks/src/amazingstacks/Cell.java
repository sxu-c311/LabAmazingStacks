//A class representing a single cell of a maze
//Created by James Vanderhyde, 2 November 2011

package amazingstacks;

/**
 *
 * @author jamesvanderhyde
 */
public class Cell extends java.awt.Point
{
    public static final int NORTH = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 4;
    public static final int EAST = 8;

    public Cell()
    {
        super();
    }
    
    public Cell(int x, int y)
    {
        super(x,y);
    }
    
    public Cell(Cell c)
    {
        super(c);
    }
    
    public Cell(Cell c, int fromDirection)
    {
        super(c);
        switch (fromDirection)
        {
        case NORTH:
            translate(0,+1);
            break;
        case SOUTH:
            translate(0,-1);
            break;
        case WEST:
            translate(+1,0);
            break;
        case EAST:
            translate(-1,0);
            break;
        }
    }
    
    public void translateInDirection(int dir)
    {
        switch (dir)
        {
        case NORTH:
            translate(0,-1);
            break;
        case SOUTH:
            translate(0,+1);
            break;
        case WEST:
            translate(-1,0);
            break;
        case EAST:
            translate(+1,0);
            break;
        }
    }
    
    public boolean isNeighbor(Cell c)
    {
        int dx=c.x-x;
        int dy=c.y-y;
        if (dx<0) dx=-dx;
        if (dy<0) dy=-dy;
        return (((dx==1) && (dy==0)) || ((dy==1) && (dx==0)));
    }
    
    @Override
    public String toString()
    {
        return "("+x+","+y+")";
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Cell)
        {
            Cell c=(Cell)o;
            return (this.x==c.x && this.y==c.y);
        }
        else
            return false;
    }
    
    public static int distance1(Cell c1, Cell c2)
    {
        return Math.abs(c1.x-c2.x)+Math.abs(c1.y-c2.y);
    }
}
