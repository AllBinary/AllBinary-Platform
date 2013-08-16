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
package org.allbinary.animation.image;

import org.allbinary.game.resource.ResourceLoadingLevelFactory;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;

import allbinary.animation.resource.BaseResourceAnimationInterfaceFactoryInterfaceFactory;
import allbinary.game.configuration.feature.Features;
import allbinary.game.configuration.feature.GraphicsFeatureFactory;
import allbinary.image.ImageCache;
import allbinary.image.ImageCacheFactory;

public class TestGameDemoGameResourcesImageBasedAnimationInterfaceFactoryInterfaceFactory
        extends BaseResourceAnimationInterfaceFactoryInterfaceFactory
{
    public TestGameDemoGameResourcesImageBasedAnimationInterfaceFactoryInterfaceFactory()
    {
        super("Game Image Animations");
    }

    public TestGameDemoGameResourcesImageBasedAnimationInterfaceFactoryInterfaceFactory(String name)
    {
        super(name);
    }
    
    public void init(int level)
    throws Exception
    {
        this.init(ImageCacheFactory.getInstance(), level);
    }

    protected void init(ImageCache imageCache, int level)
    throws Exception
    {
        if(this.isInitialized())
        {
            return;
        }

        //final int portion = 120;
        //final String loadingString = this.toString() + " Loading: ";
        
        //int index = 0;

        //ProgressCanvas progressCanvas = 
          //  ProgressCanvasFactory.getInstance();

        //Add Resources here - like weapons and such

        super.init(level);
    }
    
    public boolean isLoadingLevel(int level)
    {
        if(level == ResourceLoadingLevelFactory.getInstance().LOAD_GAME.getLevel())
        {
            return true;
        }
        else
        {
            return super.isLoadingLevel(level);
        }
    }
    
    public boolean isFeature()
    {
        Features features = Features.getInstance();
        
        if (features.isFeature(
                GraphicsFeatureFactory.getInstance().IMAGE_GRAPHICS) &&
            features.isFeature(
                GraphicsFeatureFactory.getInstance().IMAGE_TO_ARRAY_GRAPHICS) &&
            !features.isDefault(
                OpenGLFeatureFactory.getInstance().OPENGL))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private void addRectangles() throws Exception
    {
       //this.addRectangle(BossOneShipResources.getInstance().RESOURCE, new Rectangle(PointFactory
         //       .ZERO_ZERO, 52, 52));
    }
}