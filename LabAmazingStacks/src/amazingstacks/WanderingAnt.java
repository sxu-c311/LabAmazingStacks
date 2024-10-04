//An ant that carries food back to the nest. If it doesn't have food it wanders randomly.
//Created by James Vanderhyde, 2 November 2011
//Modified by James Vanderhyde, 29 October 2013
//  Improved ant movement toward nest.
//Modified by James Vanderhyde, 27 March 2015
//  Introduced canSeeNest method
//Modified by James Vanderhyde, 15 April 2015
//  Incorporated an old chage, the foundFoodOnLastMove functionality.

package amazingstacks;

/**
 *
 * @author jamesvanderhyde
 */
public class WanderingAnt
{
    public static final double NOTICE_FOOD_PROB = 0.06;
    public static final double CHANGE_DIRECTION_PROB = 0.1;
    public static final int SIGHT_DISTANCE = 8;
    
    private Cell location;
    private Cell nest;
    
    private boolean carryingFood, thinksItHasFood;
    private int lastMovedDirection;
    private boolean foundFoodOnLastMove;
    
    /**
     * Initializes a new WanderingAnt.
     *   The ant knows its nest location so it can get home.
     * @param nestLocation 
     *   The location of the ant's home.
     */
    public WanderingAnt(Cell nestLocation)
    {
        location = new Cell(40,50);
        nest = new Cell(nestLocation);
        carryingFood = false;
        thinksItHasFood = false;
        lastMovedDirection = Cell.EAST;
        foundFoodOnLastMove = false;
    }
    
    /**
     * Returns the current location of the ant.
     * @return a new Cell that contains the location of the ant.
     */
    public Cell getLocation()
    {
        return new Cell(location);
    }
    
    
    //Methods for movement
    
    /**
     * Moves this ant one step in the indicated direction.
     *   This method is called by the other move methods.
     *   It may be overridden in a subclass, but be sure to call 
     *   the superclass implementation.
     * @param direction 
     *   the direction this ant is to move.
     */
    public void move(int direction)
    {
        location.translateInDirection(direction);
        lastMovedDirection = direction;
        foundFoodOnLastMove = false;
    }
    
    /**
     * Moves this ant one step toward its nest.
     */
    public void moveTowardNest()
    {
        //With some probablity, 
        // or if the nest is in visual range,
        // or if the ant just found food,
        // change direction toward the nest. Otherwise keep going forward.
        if (Math.random() < CHANGE_DIRECTION_PROB || canSeeNest() || foundFoodOnLastMove)
        {
            move(towardNestDirection());
            System.out.println("Step directly towards nest");
        }
        else
        {
            move(lastMovedDirection);
            System.out.println("Step forward towards nest");
        }
    }

    /**
     * Moves this ant to look for food.
     *   Since this is a wandering ant, the ant moves in a random direction.
     */
    public void moveLookingForFood()
    {
        move(randomDirection());
        System.out.println("Step random");
    }
    
    private int randomDirection()
    {
        int r=(int)(Math.random()*4);
        switch (r)
        {
        case 0:
            return Cell.NORTH;
        case 1:
            return Cell.SOUTH;
        case 2:
            return Cell.EAST;
        case 3:
            return Cell.WEST;
        }
        return 0; //should never be reached
    }
    
    private int towardNestDirection()
    {
        if (Math.abs(location.x-nest.x) > Math.abs(location.y-nest.y))
        {
            //nest is in X direction
            if (location.x < nest.x)
                return Cell.EAST;
            else
                return Cell.WEST;
        }
        else
        {
            //nest is in Y direction
            if (location.y < nest.y)
                return Cell.SOUTH;
            else
                return Cell.NORTH;
        }
    }
    
    
    //Methods for the simulation
    
    /**
     * Tells this ant to move one step for simulation.
     *   How the ant moves depends on whether it thinks it has food,
     *   and what its behavior is for looking for food.
     *   There is also a random chance that if the ant thinks it has
     *   food but does not actually have food, it will notice.
     */
    public void travelOneStep()
    {
        if (!location.equals(nest))
        {
            if (thinksItHasFood)
                moveTowardNest();
            else
                moveLookingForFood();
            
            //With some probability, check whether carrying food
            if (Math.random()<NOTICE_FOOD_PROB)
                if (!carryingFood && thinksItHasFood)
                {
                    thinksItHasFood = false;
                    System.out.println("Where's the food?");
                }
        }
    }
    
    /**
     * Tells this ant that it has picked up food.
     */
    public void pickUpFood()
    {
        carryingFood = true;
        thinksItHasFood = true;
        foundFoodOnLastMove = true;
        System.out.println("Found food");
    }
    
    /**
     * Tells the ant to drop the food.
     */
    public void dropFood()
    {
        carryingFood = false;
        System.out.println("Dropped food");
    }
    
    /**
     * Indicates that this ant is actually carrying food.
     * @return true if the ant is carrying food, false otherwise.
     */
    public boolean hasFood()
    {
        return carryingFood;
    }
    
    /**
     * Indicates that this ant think it is carrying food.
     *   It may or may not actually have the food.
     * @return true if the ant thinks it has food, false otherwise.
     */
    public boolean thinksItHasFood()
    {
        return thinksItHasFood;
    }
    
    /**
     * Indicates that the ant is within visual range of the nest.
     * @return true if the ant is within SIGHT_DISTANCE of the nest,
     *   false otherwise.
     */
    public boolean canSeeNest()
    {
        return Cell.distance1(location, nest) <= SIGHT_DISTANCE;
    }
        
    
    //Methods for the animation
    
    /**
     * Draws the ant on a given graphics context.
     * @param g2 a graphics context
     */
    public void draw(java.awt.Graphics2D g2)
    {
        g2.setColor(java.awt.Color.BLACK);
        if (lastMovedDirection==Cell.NORTH || lastMovedDirection==Cell.SOUTH)
            g2.drawLine(location.x, location.y-1, location.x, location.y+1);
        else
            g2.drawLine(location.x-1, location.y, location.x+1, location.y);
    }
}
