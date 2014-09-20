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
package org.allbinary.game.layer.resources;

import org.allbinary.game.resource.ResourceLoadingLevelFactory;

import org.allbinary.animation.resource.BaseResourceAnimationInterfaceFactoryInterfaceFactory;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.MainFeatureFactory;
import org.allbinary.game.layer.GameLayerPlacementInterfaceBasicArrayListFactoryInterfaceFactory;
import org.allbinary.game.layer.LayerPlacementInterfaceBasicArrayListFactoryInterface;
import org.allbinary.image.ImageCache;

public class OnDemandLoader
{
    private final OnDemandResourcesFactory onDemandResourcesFactory;
    private final GameLayerPlacementInterfaceBasicArrayListFactoryInterfaceFactory gameLayerPlacementInterfaceBasicArrayListFactoryInterfaceFactory;
    private final BaseResourceAnimationInterfaceFactoryInterfaceFactory resourceAnimationInterfaceFactoryInterfaceFactory;

    public OnDemandLoader(
            OnDemandResourcesFactory onDemandResourcesFactory,
            GameLayerPlacementInterfaceBasicArrayListFactoryInterfaceFactory gameLayerPlacementInterfaceBasicArrayListFactoryInterfaceFactory,
            BaseResourceAnimationInterfaceFactoryInterfaceFactory resourceAnimationInterfaceFactoryInterfaceFactory)
    {        
        this.onDemandResourcesFactory = onDemandResourcesFactory;
        this.gameLayerPlacementInterfaceBasicArrayListFactoryInterfaceFactory = gameLayerPlacementInterfaceBasicArrayListFactoryInterfaceFactory;
        this.resourceAnimationInterfaceFactoryInterfaceFactory = resourceAnimationInterfaceFactoryInterfaceFactory;
    }

    public void init(ImageCache imageCache, int level, String loadingString)
    throws Exception
    {
        ResourceLoadingLevelFactory resourceLoadingLevelFactory = 
            ResourceLoadingLevelFactory.getInstance();
        
        if (Features.getInstance().isFeature(
                MainFeatureFactory.getInstance().LOAD_ALL))
        {
            if (this.resourceAnimationInterfaceFactoryInterfaceFactory.isInitialized())
            {
                return;
            }

            this.initAll(imageCache, loadingString);
        }
        else if (level != resourceLoadingLevelFactory.LOAD_ALL.getLevel())
        {
            LayerPlacementInterfaceBasicArrayListFactoryInterface layerPlacementInterfaceBasicArrayListFactoryInterface = 
                this.gameLayerPlacementInterfaceBasicArrayListFactoryInterfaceFactory.getInstance(level);
            
            this.initLevel(
                    layerPlacementInterfaceBasicArrayListFactoryInterface, 
                    imageCache, level, loadingString);
        }
    }

    private void initAll(ImageCache imageCache, final String loadingString)
            throws Exception
    {
        int index = 0;
        final int portion = 120;

        OnDemandResources onDemandResources;
        
        for (int onDemandIndex = onDemandResourcesFactory.size(); --onDemandIndex >= 0;)
        {
            onDemandResources = onDemandResourcesFactory.remove(onDemandIndex);

            if (onDemandResources != null)
            {
                index = onDemandResources.init(imageCache,
                        this.resourceAnimationInterfaceFactoryInterfaceFactory,
                        portion, loadingString, index);
            }
        }
    }

    private void initLevel(
            LayerPlacementInterfaceBasicArrayListFactoryInterface layerPlacementInterfaceBasicArrayListFactoryInterface,
            ImageCache imageCache, final int level, final String loadingString)
            throws Exception
    {
        // PreLogUtil.put(CommonStrings.getInstance().START_LABEL + level, this, "initLevel");

        final int portion = 120;
        int index = 0;

        int[] onDemandArray = layerPlacementInterfaceBasicArrayListFactoryInterface.getOnDemandResources();

        OnDemandResources onDemandResources;
        
        for (int onDemandIndex = onDemandArray.length; --onDemandIndex >= 0;)
        {
            onDemandResources = onDemandResourcesFactory.remove(onDemandArray[onDemandIndex]);

            //if null then it is already loaded
            if (onDemandResources != null)
            {
                index = onDemandResources.init(imageCache,
                        this.resourceAnimationInterfaceFactoryInterfaceFactory,
                        portion, loadingString, index);
            }
        }
    }

}
