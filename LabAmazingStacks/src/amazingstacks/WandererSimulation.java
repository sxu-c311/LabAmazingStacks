//A simulation of an ant bringing food home to the nest
//Created by James Vanderhyde, 2 November 2011
//Modified by James Vanderhyde, 27 March 2015
//  The ant will not drop the food if it can see the nest

package amazingstacks;

import java.awt.Color;

/**
 *
 * @author jamesvanderhyde
 */
public class WandererSimulation extends AnimationComponent
{
    public static void main(String[] args)
    {
        AnimationComponent.launchViewerWindow(640, 480, "Wandering Ant", new WandererSimulation());
    }
    
    public static final double FRAME_RATE = 5; //frames per second
    private long elapsedTime;
    public static final int CELL_SIZE = 3;
    
    public static final double DROP_FOOD_PROB = 0.05;
    
    private WanderingAnt ant;
    private Cell foodLocation;
    private Cell nestLocation;
    
    public WandererSimulation()
    {
        nestLocation = new Cell(120,120);
        
        ant = new WanderingAnt(nestLocation);
        foodLocation = ant.getLocation();
        ant.pickUpFood();
        
        elapsedTime = 0;
    }
    
    public void simulateOneStep()
    {
        //With some probability, drop the food (unless the nest is in visual range)
        if (ant.hasFood())
            if (Math.random() < DROP_FOOD_PROB  && !ant.canSeeNest())
                ant.dropFood();

        //Ant makes one step
        ant.travelOneStep();
        
        //Move the food with the ant
        if (ant.hasFood())
            foodLocation = ant.getLocation();
        else
            if (foodLocation.equals(ant.getLocation()))
            {
                foodLocation = ant.getLocation();
                ant.pickUpFood();
            }
    }
    
    @Override
    public void paint(java.awt.Graphics2D g2)
    {
        //enlarge the graphics
        g2.scale(CELL_SIZE, CELL_SIZE);
        
        //draw the any
        ant.draw(g2);
        
        //draw the food
        g2.setColor(Color.RED);
        g2.fillRect(foodLocation.x, foodLocation.y, 1, 1);
        
        //draw the nest
        g2.setColor(new Color(96,96,0)); //brown
        g2.drawLine(nestLocation.x, nestLocation.y-2, nestLocation.x, nestLocation.y+2);
        g2.drawLine(nestLocation.x-2, nestLocation.y, nestLocation.x+2, nestLocation.y);
    }
    
    @Override
    public void updateAnimation(long timeSinceLastUpdate)
    {
        elapsedTime += timeSinceLastUpdate;
        
        if (elapsedTime >= 1000/FRAME_RATE)
        {
            elapsedTime -= (long)(1000/FRAME_RATE);
            
            simulateOneStep();
        }
    }
    
}
