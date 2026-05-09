/*
 * AllBinary Open License Version 1
 * Copyright (c) 2003 AllBinary
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

package org.allbinary.game.layer.capital.event;

import org.allbinary.game.identification.Group;

/**
 *
 * @author user
 */
public class CapitalEventHandlerFactory {

    //One for each Group
    private static final CapitalEventHandler[] capitalEventHandlerArray;
    
    static
    {
        capitalEventHandlerArray = new CapitalEventHandler[10];
        
        for(int index = CapitalEventHandlerFactory.capitalEventHandlerArray.length - 1; index >= 0; index--)
        {
            CapitalEventHandlerFactory.capitalEventHandlerArray[index] = new CapitalEventHandler();
        }
    }

    public static CapitalEventHandler getInstance(Group groupInterface)
    {
        return CapitalEventHandlerFactory.capitalEventHandlerArray[(int) groupInterface.getGroupId()];
    }

    public static void removeAll()
    {
        for(int index = CapitalEventHandlerFactory.capitalEventHandlerArray.length - 1; index >= 0; index--)
        {
            CapitalEventHandlerFactory.capitalEventHandlerArray[index].removeAllListeners();
        }
    }
}
