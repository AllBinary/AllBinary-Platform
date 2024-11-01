/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
package org.allbinary.media.graphics.geography.map;

import org.allbinary.game.layer.AllBinaryGameLayer;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class GeographicMapEventHandler extends BasicEventHandler {
    
    private static GeographicMapEventHandler instance = new GeographicMapEventHandler();

    public static GeographicMapEventHandler getInstance()
    {
        return GeographicMapEventHandler.instance;
    }

    private final BasicArrayList list = new BasicArrayList();
    
    private GeographicMapEventHandler()
    {
    }

    public void addListener(AllBinaryGameLayer gameLayer)
    {
        if(!list.contains(gameLayer))
        {
            list.add(gameLayer);
        }
    }

    public void removeAllListeners()
    {
        this.list.clear();
        super.removeAllListeners();
    }

    public void removeListener(EventListenerInterface eventListenerInterface)
    {
        this.list.remove(eventListenerInterface);
        super.removeListener(eventListenerInterface);
    }

    public void fireEvent() //AllBinaryEventObject eventObject) throws Exception
    {        
        for (int index = this.list.size(); --index >= 0;)
        {
            try
            {
                AllBinaryGameLayer gameLayer = (AllBinaryGameLayer) this.list.get(index);
                gameLayer.move();
            }
            catch (Exception e)
            {
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e));
            }
        }

        //super.fireEvent(eventObject);
    }
    
}
