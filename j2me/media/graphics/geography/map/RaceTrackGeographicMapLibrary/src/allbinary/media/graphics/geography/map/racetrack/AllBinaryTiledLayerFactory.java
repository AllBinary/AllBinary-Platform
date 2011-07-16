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

import javax.microedition.lcdui.game.TiledLayer;

import org.allbinary.graphics.opengles.OpenGLFeatureUtil;
import org.allbinary.media.graphics.geography.map.racetrack.threed.AllBinaryThreedTiledLayerFactory;

import allbinary.game.layer.AllBinaryJ2METiledLayer;
import allbinary.game.layer.AllBinaryTiledLayer;
import allbinary.logic.math.SmallIntegerSingletonFactory;

/**
 *
 * @author user
 */
public class AllBinaryTiledLayerFactory
{
    public AllBinaryTiledLayer getInstance(
            RaceTrackInfo raceTrackInfo,
            RaceTrackData raceTrackData)
            throws Exception
    {
        AllBinaryTiledLayer allbinaryTiledLayer;
        
        if(OpenGLFeatureUtil.getInstance().isAnyThreed())
        {
            allbinaryTiledLayer = 
                AllBinaryThreedTiledLayerFactory.getInstance().getInstance(raceTrackInfo, raceTrackData);
        }
        else
        {
            TiledLayer tiledLayer = new TiledLayerFactory().getInstance(raceTrackData);

            allbinaryTiledLayer = new AllBinaryJ2METiledLayer(
                    raceTrackData.getId(), tiledLayer, raceTrackData.getMapArray());

            allbinaryTiledLayer.setCells(raceTrackData.getMapArray());            
        }
        
        return allbinaryTiledLayer;
    }

    public AllBinaryTiledLayer getMiniInstance(
            RaceTrackData raceTrackData)
            throws Exception
    {
        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        TiledLayer tiledLayer = new TiledLayerFactory().getMiniInstance(raceTrackData);

        AllBinaryTiledLayer allbinaryTiledLayer = new AllBinaryJ2METiledLayer(
                smallIntegerSingletonFactory.getInstance(raceTrackData.getId().intValue() + 100 + 1),
                tiledLayer, raceTrackData.getMapArray());

        allbinaryTiledLayer.setCells(raceTrackData.getMapArray());

        return allbinaryTiledLayer;
    }
}
