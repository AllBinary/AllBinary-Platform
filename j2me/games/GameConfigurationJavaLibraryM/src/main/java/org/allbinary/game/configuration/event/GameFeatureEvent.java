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
package org.allbinary.game.configuration.event;

import org.allbinary.logic.util.event.AllBinaryEventObject;

public class GameFeatureEvent extends AllBinaryEventObject
{
    private final String whatChanged;
    
    public GameFeatureEvent(Object object, String whatChanged)
    {
        super(object);

        this.whatChanged = whatChanged;
    }

    public String getWhatChanged()
    {
        return whatChanged;
    }

    public Object getGameOption()
    {
        return this.getSource();
    }
}
