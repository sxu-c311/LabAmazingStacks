//An abstract class used to make displaying a component in a window easy
//Created by James Vanderhyde, 2 November 2011

package amazingstacks;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;

/**
 *
 * @author jamesvanderhyde
 */
public abstract class AnimationComponent extends javax.swing.JComponent
{
    
    @Override
    public void paintComponent(Graphics g)
    {
        //g.setColor(Color.WHITE);
        //g.fillRect(0, 0, this.getWidth(), this.getHeight());
        this.paint((Graphics2D) g);
    }
    
    public void paint(Graphics2D g2)
    {
    }
    
    public void updateAnimation(long timeSinceLastUpdate)
    {
    }
    
    public static void launchViewerWindow(int width, int height, String title,
                                          AnimationComponent component)
    {
        JFrame frame = new JFrame();
        
        frame.setSize(width, height);
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        frame.add(component);
        
        frame.setVisible(true);
        
        //Loop while the window remains open
        long time=System.currentTimeMillis();
        while (frame.isVisible())
        {
            //Update the data for the next frame of the animation
            component.updateAnimation(System.currentTimeMillis()-time);
            time=System.currentTimeMillis();
            
            //Update the graphics on screen
            component.repaint();
            
            //Wait for 30 milliseconds
            try
            {
                Thread.sleep(30);
            }
            catch (InterruptedException e)
            {
            }
        }
        
    }

}
