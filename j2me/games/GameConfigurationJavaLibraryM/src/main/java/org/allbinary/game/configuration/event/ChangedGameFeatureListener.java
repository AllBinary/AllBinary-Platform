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

import jsinterop.annotations.JsType;

import org.allbinary.game.configuration.GameConfiguration;
import jsinterop.annotations.JsMethod;


@JsType
public class ChangedGameFeatureListener extends BaseChangedGameFeatureListener
{
    private static final ChangedGameFeatureListener gameFeatureListener = new ChangedGameFeatureListener();
    
    @JsMethod
    public static ChangedGameFeatureListener getInstance()
    {
        return ChangedGameFeatureListener.gameFeatureListener;
    }

    @JsMethod
    public boolean isChangedGameConfiguration(GameConfiguration gameConfiguration)
    {
        return this.list.contains(gameConfiguration);
    }    
}
