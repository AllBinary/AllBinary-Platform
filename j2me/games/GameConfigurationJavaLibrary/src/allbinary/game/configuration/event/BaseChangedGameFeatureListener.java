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
package allbinary.game.configuration.event;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.NotImplemented;
import abcs.logic.communication.log.ForcedLogUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.configuration.feature.Feature;
import allbinary.game.configuration.feature.Features;
import allbinary.logic.basic.util.event.AllBinaryEventObject;

public class BaseChangedGameFeatureListener implements GameFeatureListenerInterface
{
    protected final BasicArrayList list = new BasicArrayList();
    private boolean changed = true;

    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(NotImplemented.NAME, this);
    }

    public void onGameFeatureChange(GameFeatureEvent gameFeatureEvent)
    {
        LogUtil.put(LogFactory.getInstance(
                "Game Feature Changed: " + gameFeatureEvent.getWhatChanged(), this, "onGameFeatureChange"));

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
        
        StringBuilder stringBuffer = new StringBuilder();
        
        stringBuffer.append("GameFeature: ");
        stringBuffer.append(gameFeature);
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
