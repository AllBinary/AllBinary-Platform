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
package allbinary.game.resource;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.configuration.feature.Features;
import allbinary.game.configuration.feature.GameFeatureControlledInterface;
import allbinary.game.configuration.feature.GraphicsFeatureFactory;

/**
 * 
 * @author user
 */
public class FeaturedResourceFactory
{
    private final BasicArrayList list = new BasicArrayList();

    public FeaturedResourceFactory()
    {
    }

    private final String ANIMATION_FEATURES = "Animation Features: Vector: ";
    private final String IMAGE_LABEL = " Image: ";
    
    private final String IMAGE_GRAPHICS_ARRAY = "Image Array: ";
    private final String IMAGE_GRAPHICS_ROTATION = "Image Rotate: ";
    private final String SPRITE_QUARTER = " Sprite Quarter: ";
    private final String SPRITE_FULL = " Sprite Full: ";
    
    public void init(int level) throws Exception
    {
        int size = this.list.size();
        for (int index = 0; index < size; index++)
        {
            GameFeatureControlledInterface featureInterface = 
                (GameFeatureControlledInterface) getList().get(index);
            if (featureInterface.isLoadingLevel(level) && featureInterface.isFeature())
            {
                featureInterface.init(level);
            }
        }

        Features features = Features.getInstance();
        
        GraphicsFeatureFactory graphicsFeatureFactory = 
            GraphicsFeatureFactory.getInstance();
        
        StringBuilder stringBuffer = new StringBuilder();
        
        stringBuffer.append(ANIMATION_FEATURES);
        stringBuffer.append(features.isFeature(graphicsFeatureFactory.VECTOR_GRAPHICS));
        stringBuffer.append(IMAGE_LABEL);
        stringBuffer.append(features.isFeature(graphicsFeatureFactory.IMAGE_GRAPHICS));
        
        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, CommonStrings.getInstance().INIT));

        if (features.isFeature(graphicsFeatureFactory.IMAGE_GRAPHICS))
        {
            stringBuffer.delete(0, stringBuffer.length());

            stringBuffer.append(IMAGE_GRAPHICS_ARRAY);
            stringBuffer.append(features.isFeature(graphicsFeatureFactory.IMAGE_TO_ARRAY_GRAPHICS));
            stringBuffer.append(IMAGE_GRAPHICS_ROTATION);
            stringBuffer.append(features.isFeature(graphicsFeatureFactory.IMAGE_TO_ARRAY_GRAPHICS));
            stringBuffer.append(SPRITE_QUARTER);
            stringBuffer.append(features.isFeature(graphicsFeatureFactory.SPRITE_QUARTER_ROTATION_GRAPHICS));
            stringBuffer.append(SPRITE_FULL);
            stringBuffer.append(features.isFeature(graphicsFeatureFactory.SPRITE_FULL_GRAPHICS));

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, CommonStrings.getInstance().INIT));
        }
    }
    
    public void clear()
    {
        this.list.clear();
    }

    public void add(GameFeatureControlledInterface featureInterface)
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_LABEL + featureInterface, this, CommonStrings.getInstance().ADD));
        
        this.list.add(featureInterface);
    }

    public BasicArrayList getList()
    {
        return list;
    }
}
