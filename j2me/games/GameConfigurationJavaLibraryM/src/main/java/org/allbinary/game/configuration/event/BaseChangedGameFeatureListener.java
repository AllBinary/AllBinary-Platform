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

import org.allbinary.game.configuration.feature.Feature;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GameFeatureUtil;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

public class BaseChangedGameFeatureListener implements GameFeatureListenerInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final GameFeatureUtil gameFeatureUtil = GameFeatureUtil.getInstance();

    protected final BasicArrayList list = new BasicArrayListD();
    private boolean changed = true;

    @Override
    public void onEvent(final AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(CommonStrings.getInstance().NOT_IMPLEMENTED, this);
    }

    @Override
    public void onGameFeatureChange(final GameFeatureEvent gameFeatureEvent)
    {
        this.logUtil.putF(new StringMaker().append(gameFeatureUtil.GAME_FEATURE_CHANGED).append(gameFeatureEvent.getWhatChanged()).toString(), this, gameFeatureUtil.ON_GAME_FEATURE_CHANGE);

       this.list.add(gameFeatureEvent.getGameOption());

       this.setChanged(true);
    }

    public void add(final Feature gameFeature)
    {
        this.list.add(gameFeature);
    }

    public void remove(final Feature gameFeature)
    {
        this.list.remove(gameFeature);
    }
    
    public void setChanged(final boolean initialized)
    {
        this.changed = initialized;
        
        if(!this.isChanged())
        {
            this.list.clear();
        }
    }

    public boolean isChangedFeature(final Feature gameFeature)
    {
        final boolean isChanged = this.list.contains(gameFeature);

        final StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append("GameFeature: ");
        stringBuffer.append(StringUtil.getInstance().toString(gameFeature));
        stringBuffer.append(" isFeature: ");
        stringBuffer.appendboolean(Features.getInstance().isFeature(gameFeature));
        stringBuffer.append(" isChanged: ");
        stringBuffer.appendboolean(isChanged);
        
        this.logUtil.putF(stringBuffer.toString(), this, "isChanged");
        
        return isChanged;
    }
    
    public boolean isChanged()
    {
        return this.changed;
    }
}
