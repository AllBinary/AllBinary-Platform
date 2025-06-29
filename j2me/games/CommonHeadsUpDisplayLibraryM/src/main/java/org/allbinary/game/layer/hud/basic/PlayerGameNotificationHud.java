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
package org.allbinary.game.layer.hud.basic;

import javax.microedition.lcdui.Graphics;

import org.allbinary.business.advertisement.GameAdStateFactory;
import org.allbinary.game.GameAdState;
import org.allbinary.util.CircularIndexUtil;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.layer.hud.event.GameNotificationEventHandler;
import org.allbinary.game.layer.hud.basic.notification.GameNotification;
import org.allbinary.game.layer.hud.basic.notification.GameNotificationHud;
import org.allbinary.graphics.CustomGPoint;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.time.GameTickTimeDelayHelper;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.TimeDelayHelper;

public class PlayerGameNotificationHud
extends GameNotificationHud
{
    private final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;
    private String string = this.EMPTY_STRING;

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
    private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(0);

    private final CircularIndexUtil circularIndexUtil;
    private final GameNotification gameNotification = new GameNotification();
    private final GameNotification permanentGameNotification = new GameNotification();
    
    private CustomGPoint point; 

    private final GameTickTimeDelayHelper gameTickTimeDelayHelper = GameTickTimeDelayHelperFactory.getInstance();

    public PlayerGameNotificationHud(
        int location, int direction,
        int maxHeight, int maxWidth,
        int bufferZone, BasicColor basicColor)
    throws Exception
    {
        super(location, direction, maxHeight, maxWidth, bufferZone, basicColor);

        this.circularIndexUtil = CircularIndexUtil.getInstance(0);

        final GameNotificationEventHandler gameNotificationEventHandler  = GameNotificationEventHandler.getInstance();
        gameNotificationEventHandler.removeAllListeners();
        gameNotificationEventHandler.addListener(this);
    }

    protected GPoint getPoint(int x, int y)
    {
        point = new CustomGPoint(0, 0);
        this.point.setX(x);
        this.point.setY(y);
        return point;
    }
    
    private final String PERMANENT_GAME_NOTIFICATION = "Permanent Game Notification: ";
    //private final String TEMP_GAME_NOTIFICATION = "Temp Game Notification: ";
    
    private String lastString = null;
    
    //Add event if it is not already there
    protected void add(final String string, final Integer seconds, final BasicColor basicColor, final Boolean permanent)
    {
        if (permanent.booleanValue())
        {
            if(lastString != string) {
                this.lastString = string;
                LogUtil.put(LogFactory.getInstance(new StringMaker().append(PERMANENT_GAME_NOTIFICATION).append(string).toString(), this, commonStrings.ADD));
            }
            this.permanentGameNotification.add(string, seconds, basicColor);
            this.circularIndexUtil.setSize(this.permanentGameNotification.getSize());
        }
        else
        {
            //if(seconds.intValue() > 0)
            //{
                //LogUtil.put(LogFactory.getInstance(TEMP_GAME_NOTIFICATION).append(string, this, commonStrings.ADD));
            //}

            this.gameNotification.add(string, seconds, basicColor);
        }
    }

    public void processTick() throws Exception
    {
        if (this.timeDelayHelper.isTime(gameTickTimeDelayHelper.startTime))
        {
            final GameAdState gameAdState = GameAdStateFactory.getInstance().getCurrentInstance();
            
            if(gameAdState.isShowingAt(this.getLocation()))
            {
                //PreLogUtil.put("Y: ").append(this.getY(), this, GameStrings.getInstance().PROCESS_TICK);
                this.offsetY = -54;
            }
            else
            {
                this.offsetY = 0;
            }

            if (this.gameNotification.getSize() > 0)
            {
                this.setAndRemove();
            }
            else
            if (this.permanentGameNotification.getSize() > 0)
            {
                this.setNextUnremoveable();
            }
            else
            {
                this.string = this.EMPTY_STRING;
            }
        }
    }
    
    private void setAndRemove()
        throws Exception
    {
        this.string = (String) this.gameNotification.stringList.remove(0);

        this.setX((displayInfo.getLastWidth() - (MyFont.getInstance().stringWidth2(this.string)) >> 1));
        //
        this.point.setX(this.getX());
        this.point.setY(this.getY());

        final Integer time = (Integer) this.gameNotification.timeList.remove(0);
        
        int iTime = time.intValue() * 1000;
        
        if(iTime == 0)
        {
             iTime = 500;
        }
        
        this.timeDelayHelper.delay = iTime;

        this.setBasicColor((BasicColor) this.gameNotification.colorList.remove(0));
    }

    private void setNextUnremoveable()
        throws Exception
    {
        final int index = this.circularIndexUtil.getIndex();

        this.string = (String) this.permanentGameNotification.stringList.objectArray[index];

        this.setX((displayInfo.getLastWidth() - (MyFont.getInstance().stringWidth2(this.string)) >> 1));
        //
        this.point.setX(this.getX());
        this.point.setY(this.getY());

        Integer time = (Integer) this.permanentGameNotification.timeList.objectArray[index];
        this.timeDelayHelper.delay = time.intValue() * 1000;

        this.setBasicColor((BasicColor)
            this.permanentGameNotification.colorList.objectArray[index]);

        this.circularIndexUtil.next();
    }

    public void clear()
    {
        this.gameNotification.clear();
        this.permanentGameNotification.clear();
    }

    public void paint(Graphics graphics)
    {
        //Could draw rectangle
        //this.getMaxWidth()
        super.paint(graphics, this.string);
    }
}