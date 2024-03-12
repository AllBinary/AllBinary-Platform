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
package org.allbinary.game.init;

import org.allbinary.game.resource.ResourceLoadingLevelFactory;

import org.allbinary.game.configuration.event.ChangedGameFeatureListener;
import org.allbinary.game.configuration.event.GameInitializedEvent;
import org.allbinary.game.configuration.event.GameInitializedEventHandler;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.MainFeatureFactory;
import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.logic.system.SoftwareInformation;

public class GameInitializationUtil
{
    private static final GameInitializationUtil instance = new GameInitializationUtil();
    
    public final GameInitializedEvent EVENT = new GameInitializedEvent(this);
    
    public final void initDemo(
        final AbeClientInformationInterface abeClientInformation,
        final MyCanvas canvas,
        final BasicBuildGameInitializerFactory gameInitializationInterfaceFactoryInterface)
    throws Exception
    {
        final MainFeatureFactory mainFeatureFactory = MainFeatureFactory.getInstance();
        
        if (ChangedGameFeatureListener.getInstance().isChanged(mainFeatureFactory.STATIC))
        {
            GameInitializationInterface gameInitializationInterface = 
                gameInitializationInterfaceFactoryInterface.getInstance();
            
            Features features = Features.getInstance();
            
            //When debugging is on go ahead and load all resources
            if(features.isFeature(mainFeatureFactory.LOAD_ALL))
            {
                this.initGame(abeClientInformation, canvas, gameInitializationInterfaceFactoryInterface);
            }
            else
                if(features.isFeature(mainFeatureFactory.LOAD_ONDEMAND))
            {
                    ResourceLoadingLevelFactory resourceLoadingLevelFactory = 
                        ResourceLoadingLevelFactory.getInstance();

                    gameInitializationInterface.init(
                        abeClientInformation,
                        canvas.getCustomCommandListener(),
                        resourceLoadingLevelFactory.LOAD_EARLY.getLevel());
                    
                this.EVENT.setResourceLoadingLevel(resourceLoadingLevelFactory.LOAD_EARLY);
            }
                else
                {
                    throw new Exception("No Loading Feature Available");
                }
            
            GameInitializedEventHandler.getInstance().fireEvent(EVENT);
        }
    }

    public final void initGame(
        final AbeClientInformationInterface abeClientInformation,
        final MyCanvas canvas,
        final BasicBuildGameInitializerFactory gameInitializationInterfaceFactoryInterface)
    throws Exception
    {
        if (ChangedGameFeatureListener.getInstance().isChanged(MainFeatureFactory.getInstance().STATIC))
        {
            GameInitializationInterface gameInitializationInterface = 
                gameInitializationInterfaceFactoryInterface.getInstance();
            
            ResourceLoadingLevelFactory resourceLoadingLevelFactory = 
                ResourceLoadingLevelFactory.getInstance();
            
            gameInitializationInterface.init(
                    abeClientInformation,
                    canvas.getCustomCommandListener(),
                    resourceLoadingLevelFactory.LOAD_ALL.getLevel());
                
            this.EVENT.setResourceLoadingLevel(resourceLoadingLevelFactory.LOAD_ALL);
            
            GameInitializedEventHandler.getInstance().fireEvent(EVENT);
            
            ChangedGameFeatureListener.getInstance().remove(MainFeatureFactory.getInstance().STATIC);
        }
    }
    
    public static GameInitializationUtil getInstance()
    {
        return instance;
    }
}
