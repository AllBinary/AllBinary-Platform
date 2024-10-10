/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package org.allbinary.media.image.comparison.motion;

import java.awt.Rectangle;

import java.util.Iterator;
import java.util.Vector;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.logic.communication.log.LogFactory;

public class ConsolidateMotionRectangles extends MotionRectangles
{
    private static final String NAME = "consolidatedMotionRectangles_";
    
    public ConsolidateMotionRectangles(
        MotionRectangles motionRectangles)
        throws Exception
    {
        super(NAME, motionRectangles.getImageComparisonResult());
        
        Iterator iterator = motionRectangles.getVector().iterator();
        while(iterator.hasNext())
        {
            MotionRectangle motionRectangle = (MotionRectangle) iterator.next();
            this.getVector().add(motionRectangle.clone());
        }

        consolidateMotionRectangles();

        //LogUtil.put(LogFactory.getInstance(
          //  "After - Number Of Rectangles: " + this.getVector().size(), this, this.commonStrings.CONSTRUCTOR));
    }

    private void consolidateMotionRectangles()
    {
        LogUtil.put(LogFactory.getInstance("Start - Size Before: " + this.getVector().size(),
            this, "consolidateMotionRectangleConstraints"));
        
        Vector removeVector = new Vector();
        
        Iterator iterator = this.getVector().iterator();
        int index = 0;
        while(iterator.hasNext())
        {
            MotionRectangle motionRectangle = (MotionRectangle) iterator.next();
            
            index++;
            for(int i = index; i < this.getVector().size(); i++)
            {
                MotionRectangle motionRectangle2 = (MotionRectangle) this.getVector().get(i);

                //Only remove encapsulated rectangles
                    Rectangle rectangle = motionRectangle.getRectangle();
                    Rectangle rectangle2 = motionRectangle2.getRectangle();

                    int x2 = rectangle.x + rectangle.width;
                    int y2 = rectangle.y + rectangle.height;
                    int rightX = rectangle2.x + rectangle2.width;
                    int bottomY = rectangle2.y + rectangle2.height;
                    
                    if(rectangle.x <= rectangle2.x && rectangle.y <= rectangle2.y &&
                        x2 >= rightX && y2 >= bottomY)
                    {
                        /*
        LogUtil.put(LogFactory.getInstance(new StringMaker().append("Should Consolidating: " + rectangle.x + "<" + rectangle2.x + " " + 
            rectangle.y + "<" + rectangle2.y + " " + x2 + ">" + rightX + " " + y2 + ">" + bottomY,
            this, "consolidateMotionRectangleConstraints"));
                        */
                        removeVector.add(motionRectangle2);
                    }

                    /*
                if(RectangleCollisionUtil.isCollision(motionRectangle.getRectangle(), motionRectangle2.getRectangle()))
                {
                    Rectangle rectangle = motionRectangle.getRectangle();
                    Rectangle rectangle2 = motionRectangle2.getRectangle();

                    int x2 = rectangle.x + rectangle.width;
                    int y2 = rectangle.y + rectangle.height;
                    int rightX = rectangle2.x + rectangle2.width;
                    int bottomY = rectangle2.y + rectangle2.height;

                    if(rectangle.x > rectangle2.x)
                    {
                        rectangle.x = rectangle2.x;
                    }

                    if(rectangle.y < rectangle2.y)
                    {
                        rectangle.y = rectangle2.y;
                    }
                    
                    if(x2 < rightX)
                    {
                        //rectangle.width = rectangle.width + rightX - x2;
                        rectangle.width = rightX - rectangle.x;
                    }

                    if(y2 < bottomY)
                    {
                        //rectangle.height = rectangle.height + bottomY - y2;
                        rectangle.height = bottomY - rectangle.y;
                    }
                    
                    removeVector.add(motionRectangle2);
                }
                     */

            }
        }

        this.remove(removeVector);
    }

    private void remove(Vector removeVector)
    {
        //Remove the consolidated rectangles
        Iterator iterator = removeVector.iterator();
        while(iterator.hasNext())
        {
            this.getVector().remove(iterator.next());
        }
    }
}
