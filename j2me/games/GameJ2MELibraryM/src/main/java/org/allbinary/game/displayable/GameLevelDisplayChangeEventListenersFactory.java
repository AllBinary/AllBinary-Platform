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
package org.allbinary.game.displayable;

import org.allbinary.util.BasicArrayList;

import org.allbinary.graphics.displayable.event.DisplayChangeEventHandler;
import org.allbinary.graphics.displayable.event.DisplayChangeEventListener;
import org.allbinary.logic.util.event.handler.BasicEventHandler;

public class GameLevelDisplayChangeEventListenersFactory
{
    private static final GameLevelDisplayChangeEventListenersFactory instance = new GameLevelDisplayChangeEventListenersFactory();

    public static GameLevelDisplayChangeEventListenersFactory getInstance()
    {
        return instance;
    }

    private final BasicArrayList list = new BasicArrayList();

    public final void add(DisplayChangeEventListener displayChangeEventListener)
    {
        //PreLogUtil.put(commonStrings.START_LABEL).append(displayChangeEventListener, this, "add");

        this.list.add(displayChangeEventListener);
        DisplayChangeEventHandler.getInstance().addListener(displayChangeEventListener);
    }
    
    public final void clear()
    {
        BasicEventHandler displayChangeEventHandler = 
            DisplayChangeEventHandler.getInstance();
        
        DisplayChangeEventListener displayChangeEventListener;
        
        for(int index = this.list.size(); --index >= 0;)
        {
            displayChangeEventListener = (DisplayChangeEventListener) this.list.objectArray[index];
            
            displayChangeEventHandler.removeListener(displayChangeEventListener);
        }
        
        this.list.clear();
    }
}
