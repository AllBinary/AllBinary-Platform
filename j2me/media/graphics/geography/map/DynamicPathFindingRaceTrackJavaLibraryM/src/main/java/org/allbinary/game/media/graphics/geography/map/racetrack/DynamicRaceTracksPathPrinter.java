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
package org.allbinary.game.media.graphics.geography.map.racetrack;

import org.allbinary.util.BasicArrayList;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackGeographicMapInterfaceFactoryInterface;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackRoadsGeographicMapCellHistoryFactory;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTracksPathPrinter;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingInfo;
import org.allbinary.media.graphics.geography.pathfinding.PathGenerator;

/**
 *
 * @author user
 * Used for generating static paths.  Use in the build game method
 */
public class DynamicRaceTracksPathPrinter extends RaceTracksPathPrinter 
{
    public void printAllPaths(
       RaceTrackGeographicMapInterfaceFactoryInterface raceTrackGeographicMapInterfaceFactoryInterface,
       PathFindingInfo pathFindingInfo,
       int size)
    {
        try
        {
            //GameFeatures.getInstance().add(GameFeature.IMAGE_GRAPHICS);
            //GameFeatures.getInstance().add(GameFeature.SPRITE_FULL_GRAPHICS);

            //SmallIntegerSingletonFactory.init();
            //ImageCacheFactory.init();

            for (int index = 0; index < size; index++)
            {
                BasicGeographicMap geographicMapInterface =
                   raceTrackGeographicMapInterfaceFactoryInterface.getTrackInstance(
                   SmallIntegerSingletonFactory.getInstance().getInstance(index));
                
                BasicArrayList list = PathGenerator.getInstance().getInstance(
                   geographicMapInterface,
                   RaceTrackRoadsGeographicMapCellHistoryFactory.getInstance(), 
                   pathFindingInfo,
                   10);

                System.out.println("Track " + index);
                this.printPaths(list);
            }

        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().PROCESS, e));
        }
    }

}
