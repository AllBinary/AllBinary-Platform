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

import org.allbinary.logic.communication.log.Log;
import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.media.image.comparison.ImageComparisonResult;
import org.allbinary.media.image.comparison.pixel.PixelDelta;

public class AllMotionRectangles extends MotionRectangles
{
    private static final String NAME = "allMotionRectangles_";
    
    //GenerateImageWithBoundingBoxesAroundMovingArtifacts
    public AllMotionRectangles(
        ImageComparisonResult imageComparisonResult)
        throws Exception
    {
        super(NAME, imageComparisonResult);
        
        createMotionRectangles(imageComparisonResult);
        
        /*
        Iterator iterator = this.getVector().iterator();
        while(iterator.hasNext())
        {
            MotionRectangle motionRectangle = (MotionRectangle) iterator.next();
            LogUtil.put(new Log(
                "Rectangles: " + motionRectangle.getRectangle().toString(), this, "Constructor"));
        }
         */
        
        //LogUtil.put(new Log(
        //  "After - Number Of Rectangles: " + this.getVector().size(), this, "Constructor"));
    }
    
    private void createMotionRectangles(ImageComparisonResult imageComparisonInfo)
    throws Exception
    {
        Iterator iterator = imageComparisonInfo.getNonMatchingPixelVector().iterator();
        
        //Add First Motion Rectangle
        /*
        if(iterator.hasNext())
        {
            PixelDelta pixelDelta = (PixelDelta) iterator.next();
         
            Rectangle newRectangle =
                new Rectangle(pixelDelta.getPoint().x, pixelDelta.getPoint().y, 1, 1);
         
            MotionRectangle motionRectangle = new MotionRectangle(newRectangle);
            motionRectangle.getPixelDeltaVector().add(pixelDelta);
         
            this.getVector().add(motionRectangle);
        }*/
        
        while(iterator.hasNext())
        {
            PixelDelta pixelDelta = (PixelDelta) iterator.next();
            addPixelDeltaToAMotionRectangle(pixelDelta);
        }
    }
    
    private boolean addPixelDeltaToExistingMotionRectangle(PixelDelta pixelDelta)
    throws Exception
    {
        Iterator iterator = this.getVector().iterator();
        while(iterator.hasNext())
        {
            MotionRectangle motionRectangle = (MotionRectangle) iterator.next();
            
            //Should this area be added to the rectangle
            if(shouldPixelDeltaPartOfMotionRectangle(
                motionRectangle, pixelDelta))
            {
                /*
                if(pixelDelta.getPoint().x > 560 && pixelDelta.getPoint().y > 330 &&
                    pixelDelta.getPoint().x < 600 && pixelDelta.getPoint().y < 365)
                    LogUtil.put(new Log("Adjusting existing motionRectangle: \n" +
                        motionRectangle.getRectangle() + " for: " + pixelDelta.getPoint(), this, "addPixelDeltaToExistingMotionRectangle"));                
                */
                
                //ModifyRectangle - extend it to the lower and/or right
                int newWidth = pixelDelta.getPoint().x - motionRectangle.getRectangle().x;
                int newHeight = pixelDelta.getPoint().y - motionRectangle.getRectangle().y;
                
                if(newWidth < motionRectangle.getRectangle().width)
                {
                    newWidth = motionRectangle.getRectangle().width;
                }
                
                if(newHeight < motionRectangle.getRectangle().height)
                {
                    newHeight = motionRectangle.getRectangle().height;
                }
                
                motionRectangle.getRectangle().setSize(newWidth, newHeight);

                /*
                if(pixelDelta.getPoint().x > 560 && pixelDelta.getPoint().y > 330 &&
                    pixelDelta.getPoint().x < 600 && pixelDelta.getPoint().y < 365)
                    LogUtil.put(new Log("Adjusted existing motionRectangle: \n" +
                        motionRectangle.getRectangle() + " for: " + pixelDelta.getPoint(), this, "addPixelDeltaToExistingMotionRectangle"));                
                 */

                 return true;
            }
        }
        return false;
    }
    
    //Returns a new rectangle or modifies one
    private void addPixelDeltaToAMotionRectangle(
        PixelDelta pixelDelta)
        throws Exception
    {
        if(!addPixelDeltaToExistingMotionRectangle(pixelDelta))
        {
            Rectangle newRectangle = new Rectangle(
                pixelDelta.getPoint().x, pixelDelta.getPoint().y, 1, 1);
            
            MotionRectangle motionRectangle = new MotionRectangle(newRectangle);
            motionRectangle.getPixelDeltaVector().add(pixelDelta);

            /*
            if(pixelDelta.getPoint().x > 560 && pixelDelta.getPoint().y > 330 &&
                pixelDelta.getPoint().x < 600 && pixelDelta.getPoint().y < 365)
            {
                StringBuffer stringBuffer = new StringBuffer();
                Iterator iterator = this.getVector().iterator();
                while(iterator.hasNext())
                {
                    MotionRectangle nextMotionRectangle = (MotionRectangle) iterator.next();
                    
                    if(nextMotionRectangle.getRectangle().x > 560 && nextMotionRectangle.getRectangle().y > 330 &&
                        nextMotionRectangle.getRectangle().x < 600 && nextMotionRectangle.getRectangle().y < 365)
                    {
                        stringBuffer.append(nextMotionRectangle.getRectangle());
                        stringBuffer.append("\n");
                    }
                }
                LogUtil.put(new Log("Adding motionRectangle: " +
                    motionRectangle.getRectangle() + " since it could not be added to: " +
                    stringBuffer.toString(), this, "addPixelDeltaToAMotionRectangle"));
            }
            */

            this.getVector().add(motionRectangle);
        }
    }
    
    private boolean shouldPixelDeltaPartOfMotionRectangle(
        MotionRectangle motionRectangle, PixelDelta pixelDelta) throws Exception
    {
        boolean isPixelLeftOfTheRectangle = false;
        if(motionRectangle.getRectangle().y > pixelDelta.getPoint().y)
        {
            throw new Exception("PixelAboveTheRectangle");
        }
        
        if(motionRectangle.getRectangle().x > pixelDelta.getPoint().x)
        {
            isPixelLeftOfTheRectangle = true;
        }

/*
         boolean isPixelInRectangle =
            PixelDeltaInRectangleCollisionUtil.isCollision(rectangle, pixelDelta);
 
         if(!isPixelInRectangle)
         {
         }
         else
         {
            //ignore it is in a rectangle already
            break;
         }
 */
        
        if(!isPixelLeftOfTheRectangle)
        {
            //Is PixelDelta Already inside the rectangle
            /*
            int maxX =
                motionRectangle.getRectangle().x + motionRectangle.getRectangle().width;
            int maxY =
                motionRectangle.getRectangle().y + motionRectangle.getRectangle().height;
             
            if(maxX > pixelDelta.getPoint().x && maxY > pixelDelta.getPoint().y)
            {
//                LogUtil.put(new Log("Target is to big: " +
                //                  rectangle, this, "getTargetableRectangles"));
                throw new Exception("Does This Ever Happen");
             
                //return true;
            }
             */
            
            //Does PixelDelta seem to be part of rectangle
            return isPixelDeltaLikelyPartOfMotionRectangle(motionRectangle, pixelDelta);
        }
        
        return false;
    }
    
    private boolean isPixelDeltaLikelyPartOfMotionRectangle(
        MotionRectangle motionRectangle, PixelDelta pixelDelta) throws Exception
    {
        /*
        Vector pixelDeltasInTheSameRow = new Vector();
         
        Iterator iterator = motionRectangle.getPixelDeltaVector().iterator();
         
        while(iterator.hasNext())
        {
            PixelDelta pixelDeltaInMotionRectangle =
                (PixelDelta) iterator.next();
         
            if(pixelDeltaInMotionRectangle.getPoint().getY() ==
               pixelDelta.getPoint().getY())
            {
               pixelDeltasInTheSameRow.add(pixelDeltaInMotionRectangle);
            }
        }
         */
        
        int maxX =
            motionRectangle.getRectangle().x +
            motionRectangle.getRectangle().width;
        
        int maxY =
            motionRectangle.getRectangle().y +
            motionRectangle.getRectangle().height;
        
        int horizontalDistance = pixelDelta.getPoint().x - maxX;
        int verticalDistance = pixelDelta.getPoint().y - maxY;
        
                    /*
        if(pixelDelta.getPoint().x > 560 && pixelDelta.getPoint().y > 330 &&
            pixelDelta.getPoint().x < 600 && pixelDelta.getPoint().y < 365 &&
            (horizontalDistance < 25 && verticalDistance < 25))
            //&& this.getImageComparisonResult().getFrameTwo() == 19)
            LogUtil.put(new Log(
                pixelDelta.getPoint() +
                " motionRectangle: " + motionRectangle.getRectangle() +
                " MaxX: " + maxX + " MaxY: " + maxY +
                " horizontalDistance: " + horizontalDistance +
                " verticalDistance: " + verticalDistance,
                //" pixelDeltasInTheSameRow.size(): " + pixelDeltasInTheSameRow.size(),
                this, "isPixelDeltaLikelyPartOfMotionRectangle"));
                     */
        
        /*
        LogUtil.put(new Log(
            "MaxX: " + maxX +
            " horizontalDistance: " + horizontalDistance +
            " pixelDeltasInTheSameRow.size(): " + pixelDeltasInTheSameRow.size(),
            this, "isPixelDeltaLikelyPartOfMotionRectangle"));
         */
        
        if(horizontalDistance < MAX_PIXEL_DISTANCE_THRESHOLD &&
            verticalDistance < MAX_PIXEL_DISTANCE_THRESHOLD)
        {
            return true;
        }
        
        return false;
    }
}
