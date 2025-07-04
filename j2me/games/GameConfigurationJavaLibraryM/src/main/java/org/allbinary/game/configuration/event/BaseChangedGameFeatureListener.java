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

import org.allbinary.util.BasicArrayList;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.configuration.feature.Feature;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GameFeatureUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;

public class BaseChangedGameFeatureListener implements GameFeatureListenerInterface
{
    protected final GameFeatureUtil gameFeatureUtil = GameFeatureUtil.getInstance();

    protected final BasicArrayList list = new BasicArrayList();
    private boolean changed = true;

    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(CommonStrings.getInstance().NOT_IMPLEMENTED, this);
    }

    public void onGameFeatureChange(GameFeatureEvent gameFeatureEvent)
    {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(gameFeatureUtil.GAME_FEATURE_CHANGED).append(gameFeatureEvent.getWhatChanged()).toString(), this, gameFeatureUtil.ON_GAME_FEATURE_CHANGE));

       list.add(gameFeatureEvent.getGameOption());

       setChanged(true);
    }

    public void add(Feature gameFeature)
    {
        list.add(gameFeature);
    }

    public void remove(Feature gameFeature)
    {
        list.remove(gameFeature);
    }
    
    public void setChanged(boolean initialized)
    {
        this.changed = initialized;
        
        if(!this.isChanged())
        {
            list.clear();
        }
    }

    public boolean isChanged(Feature gameFeature)
    {
        boolean isChanged = list.contains(gameFeature);
        
        StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append("GameFeature: ");
        stringBuffer.append(StringUtil.getInstance().toString(gameFeature));
        stringBuffer.append(" isFeature: ");
        stringBuffer.append(Features.getInstance().isFeature(gameFeature));
        stringBuffer.append(" isChanged: ");
        stringBuffer.append(isChanged);
        
        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "isChanged"));
        
        return isChanged;
    }
    
    public boolean isChanged()
    {
        return this.changed;
    }
}
