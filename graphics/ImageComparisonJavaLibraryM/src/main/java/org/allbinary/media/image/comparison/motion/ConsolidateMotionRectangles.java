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


import java.util.Vector;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.logic.communication.log.LogFactory;

public class ConsolidateMotionRectangles extends MotionRectangles
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final String NAME = "consolidatedMotionRectangles_";
    
    public ConsolidateMotionRectangles(
        MotionRectangles motionRectangles)
        throws Exception
    {
        super(NAME, motionRectangles.getImageComparisonResult());
        
        final Vector vector = motionRectangles.getVector();        
        final int size = vector.size();
        for (int index = 0; index < size; index++)
        {
            MotionRectangle motionRectangle = (MotionRectangle) vector.get(index);
            vector.add(motionRectangle.clone());
        }

        consolidateMotionRectangles();

        //logUtil.put(
          //  "After - Number Of Rectangles: " + vector.size(), this, this.commonStrings.CONSTRUCTOR);
    }

    private void consolidateMotionRectangles()
    {
        final Vector vector = this.getVector();
        logUtil.put("Start - Size Before: " + vector.size(),
            this, "consolidateMotionRectangleConstraints");
        
        final Vector removeVector = new Vector();
        
        final int size = vector.size();
        for (int index = 0; index < size; index++)
        {
            MotionRectangle motionRectangle = (MotionRectangle) vector.get(index);
            
            for(int i = index + 1; i < vector.size(); i++)
            {
                MotionRectangle motionRectangle2 = (MotionRectangle) vector.get(i);

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
        logUtil.put(new StringMaker().append("Should Consolidating: " + rectangle.x + "<" + rectangle2.x + " " + 
            rectangle.y + "<" + rectangle2.y + " " + x2 + ">" + rightX + " " + y2 + ">" + bottomY,
            this, "consolidateMotionRectangleConstraints");
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
        final Vector vector = this.getVector();
        
        //Remove the consolidated rectangles
        final int size = removeVector.size();
        for (int index = 0; index < size; index++)
        {
            vector.remove(removeVector.get(index));
        }
    }
}
