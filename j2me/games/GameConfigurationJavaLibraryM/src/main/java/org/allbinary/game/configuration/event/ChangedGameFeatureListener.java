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

import org.allbinary.game.configuration.GameConfiguration;

public class ChangedGameFeatureListener extends BaseChangedGameFeatureListener
{
    private static final ChangedGameFeatureListener gameFeatureListener = new ChangedGameFeatureListener();
    
    public static ChangedGameFeatureListener getInstance()
    {
        return gameFeatureListener;
    }

    public boolean isChanged(GameConfiguration gameConfiguration)
    {
        return list.contains(gameConfiguration);
    }    
}
