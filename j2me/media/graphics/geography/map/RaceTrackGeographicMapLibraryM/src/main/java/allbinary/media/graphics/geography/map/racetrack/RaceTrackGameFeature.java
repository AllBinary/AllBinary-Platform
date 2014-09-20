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
package allbinary.media.graphics.geography.map.racetrack;

import org.allbinary.game.configuration.feature.GameFeature;

public class RaceTrackGameFeature extends GameFeature
{
    public static final GameFeature MINI_MAP = new RaceTrackGameFeature("Mini Map");
    public static final GameFeature AUTO_FINISH_AI = new RaceTrackGameFeature("Auto Finish AI");
    
    protected RaceTrackGameFeature(String name)
    {
        super(name);
    }
}
