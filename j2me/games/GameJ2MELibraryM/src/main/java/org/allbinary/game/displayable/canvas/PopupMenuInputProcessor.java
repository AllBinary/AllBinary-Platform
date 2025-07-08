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
package org.allbinary.game.displayable.canvas;

import javax.microedition.lcdui.Canvas;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.input.GameInputStrings;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.input.motion.gesture.MotionGestureInput;
import org.allbinary.input.motion.gesture.TouchMotionGestureFactory;
import org.allbinary.input.motion.gesture.observer.MotionGestureEvent;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.math.RectangleCollisionUtil;
import org.allbinary.time.TimeDelayHelper;

public class PopupMenuInputProcessor extends BasicMenuInputProcessor
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final RectangleCollisionUtil rectangleCollisionUtil = RectangleCollisionUtil.getInstance();
    private final GameInputStrings gameInputStrings = GameInputStrings.getInstance();
    
    private final int CLICK_DELAY = 120;
    private final TimeDelayHelper clickTimeHelper = new TimeDelayHelper(CLICK_DELAY);

    private Rectangle rectangle;

    public PopupMenuInputProcessor(
        final BasicArrayList gameKeyEventList,
        final int playerInputId, 
        final MyCanvas gameCanvas,
        final Rectangle rectangle)
    {
        super(gameKeyEventList, playerInputId, gameCanvas);

        this.rectangle = rectangle;
    }

    public void init(final Rectangle rectangle)
    {
        this.rectangle = rectangle;        
    }
    
    public int processInput(final int key) throws Exception
    {
        //logUtil.put(commonStrings.START_LABEL).append("Canvas.").append(CanvasUtil.getKeyName(key), this, GameInputStrings.getInstance());

        //4,8,KEY_STAR
        if (key == Canvas.KEY_STAR)
        {
            //logUtil.put("Key: ").append(key, this, GameInputStrings.getInstance());
            //PreLogUtil.put("Key: ").append(key, this, GameInputStrings.getInstance());
                        
            ((AllBinaryGameCanvas) this.getCanvas()).toggleMenu();
            
            return 1;
        }
        return 0;
    }

    public int processInput()
        throws Exception
    {
        try
        {
            final int motionInputsIndex = this.processMotionInputs();

            final BasicArrayList list = this.getGameKeyEventList();

            final int size = list.size();
            int key = 0;

            GameKeyEvent gameKeyEvent;

            for (int index = 0; index < size; index++)
            {
                gameKeyEvent = (GameKeyEvent) list.objectArray[index];

                if (gameKeyEvent != null)
                {
                    key = gameKeyEvent.getKey();

                    if (this.processInput(key) == 1)
                    {
                        break;
                    }
                }
            }

            this.clear();

            if (size > 0 || motionInputsIndex >= 0)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, GameInputStrings.getInstance().PROCESS_INPUT, e);
            return -1;
        }
    }

    //AllBinaryLayerManager layerManager
    public int processMotionInputs()
        throws Exception
    {
        final int lastIndex = this.motionGestureEventList.size() - 1;

        if (lastIndex >= 0)
        {
            final MotionGestureEvent motionGestureEvent =
                (MotionGestureEvent) this.motionGestureEventList.objectArray[lastIndex];

            this.processMotionInput(motionGestureEvent);
        }

        motionGestureEventList.clear();

        return lastIndex;
    }

    protected void processMotionInput(final MotionGestureEvent motionGestureEvent)
        throws Exception
    {
        //logUtil.put(commonStrings.START, this, gameInputStrings.PROCESS_MOTION_INPUT);

        if(motionGestureEvent == null)
        {
            logUtil.put("Exception: Bug", this, gameInputStrings.PROCESS_MOTION_INPUT);
            return;
        }
        
        final TouchMotionGestureFactory touchMotionGestureFactory = TouchMotionGestureFactory.getInstance();
        final MotionGestureInput motionGestureInput = motionGestureEvent.getMotionGesture();

        if (motionGestureInput == touchMotionGestureFactory.RELEASED)
        //||
        //motionGestureInput == touchMotionGestureFactory.PRESSED)
        {
            GPoint point = motionGestureEvent.getCurrentPoint();
            
            //PreLogUtil.put(new StringMaker().append("Rect: ").append(rectangle.toString()).append(CommonSeps.getInstance().SPACE).append(point.toString()).toString(), this, gameInputStrings.PROCESS_MOTION_INPUT);
            
            GPoint rectPoint = rectangle.getPoint();
            if (rectangleCollisionUtil.isInside(
                rectPoint.getX(),
                rectPoint.getY(),
                //fudge the edge of the button since touch screen calibration may suck
                rectangle.getMaxX() + 20, rectangle.getMaxY(),
                point.getX(), point.getY()))
            {
                if (this.clickTimeHelper.isTime())
                {
                    //PreLogUtil.put("Toggle Menu: ").append(motionGestureInput.toString(), this, gameInputStrings.PROCESS_MOTION_INPUT);

                    ((AllBinaryGameCanvas) this.getCanvas()).toggleMenu();
                }
            }
        }
    }
}
