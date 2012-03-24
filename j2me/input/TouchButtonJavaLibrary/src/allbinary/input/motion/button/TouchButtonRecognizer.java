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
package allbinary.input.motion.button;

import org.allbinary.util.BasicArrayList;

import allbinary.game.input.event.DownGameKeyEventHandler;
import allbinary.game.input.event.UpGameKeyEventHandler;
import allbinary.graphics.GPoint;
import allbinary.graphics.Rectangle;
import allbinary.math.RectangleCollisionUtil;

public class TouchButtonRecognizer
{
    private TouchButtonInput lastPressedTouchButtonInput;
    
    private final 
        CurrentlyPressedTouchButtonSingleton
        //CurrentlyPressedTouchButtonSingletonDebug
        currentlyPressedTouchButtonSingleton = 
        CurrentlyPressedTouchButtonSingleton.getInstance();
        //CurrentlyPressedTouchButtonSingletonDebug.getInstance();

    class ReleaseHelper
    {
        protected final TouchButtonRecognizer touchButtonRecognizer;
        
        public ReleaseHelper(TouchButtonRecognizer touchButtonRecognizer)
        {
            this.touchButtonRecognizer = touchButtonRecognizer;
        }
        
        public void release(TouchButtonInput touchButtonInput) throws Exception
        {
            for (int index = currentlyPressedTouchButtonSingleton.size() - 1; index >= 0; index--)
            {
                TouchButtonInput nextTouchButtonInput = currentlyPressedTouchButtonSingleton
                        .get(index);

                UpGameKeyEventHandler.getInstance().fireEvent(
                        nextTouchButtonInput.getGameKeyEvent());

                this.touchButtonRecognizer.currentlyPressedTouchButtonSingleton.remove(index);

              //TWB - Debugging
                //this.touchButtonRecognizer.currentlyPressedTouchButtonSingleton
                  //      .releaseAndFiredAssociated(nextTouchButtonInput);
            }
        }
    }
    
    class MultitouchReleaseHelper extends ReleaseHelper
    {
        public MultitouchReleaseHelper(TouchButtonRecognizer touchButtonRecognizer)
        {
            super(touchButtonRecognizer);
        }
        
        // This releases an associated button even if it has not been released
        public void release(TouchButtonInput touchButtonInput) throws Exception
        {
            TouchButtonInput cancelTouchButtonInput = CancelTouchButtonInputFactory.getInstance()
                    .getCancel(touchButtonInput);

            for (int index = currentlyPressedTouchButtonSingleton.size() - 1; index >= 0; index--)
            {
                TouchButtonInput nextTouchButtonInput = currentlyPressedTouchButtonSingleton
                        .get(index);

                // the following should never happen nextTouchButtonInput ==
                // touchButtonInput ||
                if (cancelTouchButtonInput == nextTouchButtonInput)
                {
                    // LogUtil.put(LogFactory.getInstance(
                    // "Release Associated Button Press", this,
                    // "processTouchButtonInput"));
                    UpGameKeyEventHandler.getInstance().fireEvent(
                            nextTouchButtonInput.getGameKeyEvent());

                    this.touchButtonRecognizer.currentlyPressedTouchButtonSingleton.remove(index);

                  //TWB - Debugging
                    //this.touchButtonRecognizer.currentlyPressedTouchButtonSingleton
                      //      .releaseAndFiredAssociated(nextTouchButtonInput);
                }
            }
        }
    }
    
    private final ReleaseHelper releaseHelper;
    
    public TouchButtonRecognizer()
    {
        if(TouchScreenFactory.getInstance().isMultiTouch())
        {
            //PreLogUtil.put("Multitouch Release Helper", this, CommonStrings.getInstance().CONSTRUCTOR);
            this.releaseHelper = new MultitouchReleaseHelper(this);
        }
        else
        {
            //PreLogUtil.put("Singletouch Release Helper", this, CommonStrings.getInstance().CONSTRUCTOR);
            this.releaseHelper = new ReleaseHelper(this);
        }
        
        /*
         * BasicArrayList list = TouchButtonFactory.getInstance().getList(); for
         * (int index = list.size() - 1; index >= 0; index--) { TouchButton
         * touchButton = (TouchButton) list.get(index);
         * 
         * LogUtil.put(LogFactory.getInstance(touchButton.toString(), this,
         * CommonStrings.getInstance().CONSTRUCTOR)); }
         */
    }

    //TWB - this could be called each game or each level just to deal with possibility
    /*
    public void releaseAll() throws Exception
    {
        for (int index = this.currentlyPressedTouchButtonInputList.size() - 1; index >= 0; index--)
        {
            TouchButtonInput nextTouchButtonInput = (TouchButtonInput)
               this.currentlyPressedTouchButtonInputList.get(index);

            // LogUtil.put(LogFactory.getInstance(
            // "Release Associated Button Press", this,
            // "processTouchButtonInput"));
            UpGameKeyEventHandler.getInstance().fireEvent(
                    nextTouchButtonInput.getGameKeyEvent());
        }
        this.currentlyPressedTouchButtonInputList.clear();
    }
    */

    private void processRelease(TouchButtonInput touchButtonInput)
    throws Exception
    {
        // Release associated button if not released
        this.releaseHelper.release(touchButtonInput);

        // LogUtil.put(LogFactory.getInstance(
        // "Event Inside Button Area - Action: " +
        // touchButtonInput.getGameKey(),
        // this, "processTouchButtonInput"));
        UpGameKeyEventHandler.getInstance().fireEvent(
                touchButtonInput.getGameKeyEvent()
                );

        this.currentlyPressedTouchButtonSingleton.remove(touchButtonInput);
    }

    public synchronized boolean releaseTouchButtonInput(int x, int y)
        throws Exception
    {
      //TWB - Debugging
        //this.currentlyPressedTouchButtonSingleton.clearLog(x, y);
        
        //TouchButtonInput touchButtonInput = null;
        
        TouchButtonInput touchButtonInput = lastPressedTouchButtonInput;
        // if already pressed then cancel associated button and release
        if (currentlyPressedTouchButtonSingleton.contains(touchButtonInput))
        {
            lastPressedTouchButtonInput = null;

            this.processRelease(touchButtonInput);
            //this.currentlyPressedTouchButtonSingleton.releaseAndFired(touchButtonInput);

            return true;
        }
      //TWB - Debugging
        else
        {
            BasicArrayList list = TouchButtonFactory.getInstance().getList();

            Rectangle rectangle;
            GPoint point;
            TouchButton touchButton;

            for (int index = list.size() - 1; index >= 0; index--)
            {
                touchButton = (TouchButton) list.objectArray[index];

                rectangle = touchButton.getRectangle();
                point = rectangle.getPoint();

                if (RectangleCollisionUtil.isInside(
                        point.getX(), point.getY(),
                        rectangle.getMaxX(), 
                        rectangle.getMaxY(), 
                        x, y))
                {
                    touchButtonInput = touchButton.getTouchButtonInput();
                    
                    this.processRelease(touchButtonInput);
                    
                  //TWB - Debugging
                    //this.currentlyPressedTouchButtonSingleton.releaseAndFired2(touchButtonInput);

                    return true;
                }
            }

          //TWB - Debugging
            //currentlyPressedTouchButtonSingleton.releaseAndNotFired();
        }
        
        return false;
    }
    
    public synchronized boolean pressTouchButtonInput(int x, int y)
        throws Exception
    {
        //LogUtil.put(LogFactory.getInstance("Current: " + x + CommonStrings.getInstance().SPACE + y, this, "processTouchButtonInput"));
        
        /*
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("pressed ");
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(PositionStrings.getInstance().X_LABEL);
        stringBuffer.append(x);
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(PositionStrings.getInstance().Y_LABEL);
        stringBuffer.append(y);
        
        final String string = stringBuffer.toString();
        */
        
        //TWB - Debugging
        //currentlyPressedTouchButtonSingleton.clearLog(x, y);
        
        //PreLogUtil.put(string, this, "processTouchButtonInput");

        BasicArrayList list = TouchButtonFactory.getInstance().getList();

        Rectangle rectangle;
        GPoint point;
        TouchButton touchButton;
        TouchButtonInput touchButtonInput;
        
        for (int index = list.size() - 1; index >= 0; index--)
        {
            touchButton = (TouchButton) list.objectArray[index];

            rectangle = touchButton.getRectangle();
            point = rectangle.getPoint();

            if (RectangleCollisionUtil.isInside(
                    point.getX(), point.getY(),
                    rectangle.getMaxX(), 
                    rectangle.getMaxY(), 
                    x, y))
            {
                touchButtonInput = touchButton.getTouchButtonInput();
                
                // Process new button press
                
                //if not already pressed then cancel associated button and press
                if (!currentlyPressedTouchButtonSingleton.contains(touchButtonInput))
                {
                    //Release associated button if not released
                    this.releaseHelper.release(touchButtonInput);

                    lastPressedTouchButtonInput = touchButtonInput;

                    currentlyPressedTouchButtonSingleton.add(touchButtonInput);

                  //TWB - Debugging
                    //currentlyPressedTouchButtonSingleton.pressedAndFired(touchButtonInput);

                    //LogUtil.put(LogFactory.getInstance("Event Inside Button Area - Action: " + touchButtonInput.getGameKey(), this, "processTouchButtonInput"));
                    //PreLogUtil.put("Event Inside Button Area - Action: " + touchButtonInput.getGameKey(), this, "processTouchButtonInput");

                    DownGameKeyEventHandler.getInstance().fireEvent(
                            touchButtonInput.getGameKeyEvent());
                }
              //TWB - Debugging
                /*
                else
                {
                    currentlyPressedTouchButtonSingleton.pressedAndNotFired(touchButtonInput);
                }
                */

                return true;
            }
        }
        return false;
    }

}
