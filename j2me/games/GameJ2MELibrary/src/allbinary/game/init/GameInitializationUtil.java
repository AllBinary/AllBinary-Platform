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
package allbinary.game.init;

import org.allbinary.game.resource.ResourceLoadingLevelFactory;

import allbinary.game.configuration.event.ChangedGameFeatureListener;
import allbinary.game.configuration.event.GameInitializedEvent;
import allbinary.game.configuration.event.GameInitializedEventHandler;
import allbinary.game.configuration.feature.Features;
import allbinary.game.configuration.feature.MainFeatureFactory;
import allbinary.graphics.displayable.MyCanvas;

public class GameInitializationUtil
{
    private static final GameInitializationUtil instance = new GameInitializationUtil();
    
    public final GameInitializedEvent EVENT = new GameInitializedEvent(this);
    
    public final void initDemo(
            MyCanvas canvas,
            BasicBuildGameInitializerFactory gameInitializationInterfaceFactoryInterface)
    throws Exception
    {
        MainFeatureFactory mainFeatureFactory = MainFeatureFactory.getInstance();
        
        if (ChangedGameFeatureListener.getInstance().isChanged(mainFeatureFactory.STATIC))
        {
            GameInitializationInterface gameInitializationInterface = 
                gameInitializationInterfaceFactoryInterface.getInstance();
            
            Features features = Features.getInstance();
            
            //When debugging is on go ahead and load all resources
            if(features.isFeature(mainFeatureFactory.LOAD_ALL))
            {
                this.initGame(canvas, gameInitializationInterfaceFactoryInterface);
            }
            else
                if(features.isFeature(mainFeatureFactory.LOAD_ONDEMAND))
            {
                    ResourceLoadingLevelFactory resourceLoadingLevelFactory = 
                        ResourceLoadingLevelFactory.getInstance();

                    gameInitializationInterface.init(
                        canvas.getCustomCommandListener(),
                        resourceLoadingLevelFactory.LOAD_EARLY.getLevel());
                    
                this.EVENT.setResourceLoadingLevel(resourceLoadingLevelFactory.LOAD_EARLY);
            }
                else
                {
                    throw new Exception("No Loading Feature Available");
                }
            
            GameInitializedEventHandler.getInstance().fireEvent(this.EVENT);          
        }
    }

    public final void initGame(
            MyCanvas canvas,
            BasicBuildGameInitializerFactory gameInitializationInterfaceFactoryInterface)
    throws Exception
    {
        if (ChangedGameFeatureListener.getInstance().isChanged(
                MainFeatureFactory.getInstance().STATIC))
        {
            GameInitializationInterface gameInitializationInterface = 
                gameInitializationInterfaceFactoryInterface.getInstance();
            
            ResourceLoadingLevelFactory resourceLoadingLevelFactory = 
                ResourceLoadingLevelFactory.getInstance();
            
            gameInitializationInterface.init(
                    canvas.getCustomCommandListener(),
                    resourceLoadingLevelFactory.LOAD_ALL.getLevel());
                
            this.EVENT.setResourceLoadingLevel(resourceLoadingLevelFactory.LOAD_ALL);
            
            GameInitializedEventHandler.getInstance().fireEvent(this.EVENT);
            
            ChangedGameFeatureListener.getInstance().remove(
                    MainFeatureFactory.getInstance().STATIC);
        }
    }
    
    public static GameInitializationUtil getInstance()
    {
        return instance;
    }
}
