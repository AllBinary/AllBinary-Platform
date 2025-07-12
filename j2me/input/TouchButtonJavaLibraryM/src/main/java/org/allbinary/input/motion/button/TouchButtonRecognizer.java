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
package org.allbinary.input.motion.button;

import org.allbinary.game.input.event.DownGameKeyEventHandler;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.event.UpGameKeyEventHandler;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.Rectangle;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.math.RectangleCollisionUtil;
import org.allbinary.util.BasicArrayList;

public class TouchButtonRecognizer
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final RectangleCollisionUtil rectangleCollisionUtil = RectangleCollisionUtil.getInstance();
    
    private final UpGameKeyEventHandler upGameKeyEventHandler = UpGameKeyEventHandler.getInstance();
    private final DownGameKeyEventHandler downGameKeyEventHandler = DownGameKeyEventHandler.getInstance();
    
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
        
        public void release(TouchButtonInput touchButtonInput, int deviceId) throws Exception
        {
            for (int index = currentlyPressedTouchButtonSingleton.size() - 1; index >= 0; index--)
            {
                TouchButtonInput nextTouchButtonInput = currentlyPressedTouchButtonSingleton
                        .get(index);

                GameKeyEvent gameKeyEvent = nextTouchButtonInput.getGameKeyEvent();
                
                upGameKeyEventHandler.fireEvent(gameKeyEvent);
                upGameKeyEventHandler.getInstance(deviceId).fireEvent(gameKeyEvent);

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
        public void release(TouchButtonInput touchButtonInput, int deviceId) throws Exception
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
                    // logUtil.put(
                    // "Release Associated Button Press", this,
                    // "processTouchButtonInput");
                    
                    GameKeyEvent gameKeyEvent = nextTouchButtonInput.getGameKeyEvent();
                    
                    upGameKeyEventHandler.fireEvent(gameKeyEvent);
                    upGameKeyEventHandler.getInstance(deviceId).fireEvent(gameKeyEvent);

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
            //PreLogUtil.put("Multitouch Release Helper", this, commonStrings.CONSTRUCTOR);
            this.releaseHelper = new MultitouchReleaseHelper(this);
        }
        else
        {
            //PreLogUtil.put("Singletouch Release Helper", this, commonStrings.CONSTRUCTOR);
            this.releaseHelper = new ReleaseHelper(this);
        }
        
        /*
         * BasicArrayList list = TouchButtonFactory.getInstance().getList(); for
         * (int index = list.size() - 1; index >= 0; index--) { TouchButton
         * touchButton = (TouchButton) list.get(index);
         * 
         * logUtil.put(touchButton.toString(), this,
         * commonStrings.CONSTRUCTOR); }
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

            // logUtil.put(
            // "Release Associated Button Press", this,
            // "processTouchButtonInput");
            UpGameKeyEventHandler.getInstance().fireEvent(
                    nextTouchButtonInput.getGameKeyEvent());
        }
        this.currentlyPressedTouchButtonInputList.clear();
    }
    */

    private void processRelease(TouchButtonInput touchButtonInput, int deviceId)
    throws Exception
    {
        // Release associated button if not released
        this.releaseHelper.release(touchButtonInput, deviceId);

        //logUtil.put("Event Inside Button Area - Action: " + touchButtonInput.getGameKeyEvent(), this, "processTouchButtonInput");
        
        GameKeyEvent gameKeyEvent = touchButtonInput.getGameKeyEvent();

        upGameKeyEventHandler.fireEvent(gameKeyEvent);
        upGameKeyEventHandler.getInstance(deviceId).fireEvent(gameKeyEvent);
                    
        this.currentlyPressedTouchButtonSingleton.remove(touchButtonInput);
    }

    public synchronized boolean releaseTouchButtonInput(int x, int y, int deviceId)
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

            this.processRelease(touchButtonInput, deviceId);
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

                if (rectangleCollisionUtil.isInside(
                        point.getX(), point.getY(),
                        rectangle.getMaxX(), 
                        rectangle.getMaxY(), 
                        x, y))
                {
                    touchButtonInput = touchButton.getTouchButtonInput();
                    
                    this.processRelease(touchButtonInput, deviceId);
                    
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
    
    public synchronized boolean pressTouchButtonInput(int x, int y, int deviceId)
        throws Exception
    {
        //logUtil.put(CommonLabels.getInstance().CURRENT + x + commonStrings.SPACE + y, this, "processTouchButtonInput");
        
        /*
        StringMaker stringBuffer = new StringMaker();
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

            if (rectangleCollisionUtil.isInside(
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
                    this.releaseHelper.release(touchButtonInput, deviceId);

                    lastPressedTouchButtonInput = touchButtonInput;

                    currentlyPressedTouchButtonSingleton.add(touchButtonInput);

                  //TWB - Debugging
                    //currentlyPressedTouchButtonSingleton.pressedAndFired(touchButtonInput);

                    //logUtil.put("Event Inside Button Area - Action: " + touchButtonInput.getGameKey(), this, "processTouchButtonInput");
                    //PreLogUtil.put("Event Inside Button Area - Action: " + touchButtonInput.getGameKey(), this, "processTouchButtonInput");

                    GameKeyEvent gameKeyEvent = touchButtonInput.getGameKeyEvent();

                    downGameKeyEventHandler.fireEvent(gameKeyEvent);
                    downGameKeyEventHandler.getInstance(deviceId).fireEvent(gameKeyEvent);

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
