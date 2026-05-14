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
package org.allbinary.game;

import org.allbinary.business.advertisement.GameAdStateBase;
import org.allbinary.business.advertisement.AdConfiguration;
import org.allbinary.input.event.VirtualKeyboardEvent;
import org.allbinary.input.event.VirtualKeyboardEventHandler;
import org.allbinary.input.event.VirtualKeyboardEventListenerInterface;

public class GameAdState extends GameAdStateBase implements VirtualKeyboardEventListenerInterface
{

    public GameAdState(AdConfiguration adConfiguration)
    {   
        super(adConfiguration);

        VirtualKeyboardEventHandler.getInstance().addListenerInterface(this);
    }

    @Override
    public void onVirtualKeyboardEvent(VirtualKeyboardEvent virtualKeyboardEvent) throws Exception {
    }
    
}
