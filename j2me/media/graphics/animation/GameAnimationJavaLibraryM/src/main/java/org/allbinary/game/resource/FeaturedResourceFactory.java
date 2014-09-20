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
package org.allbinary.game.resource;

import org.allbinary.logic.basic.string.CommonSeps;
import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.basic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GameFeatureControlledInterface;
import org.allbinary.game.configuration.feature.GraphicsFeatureFactory;
import org.allbinary.util.BasicArrayList;

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
    
    private final String IS_LOADING_LEVEL_LABEL = " isLoadingLevel ";
    private final String IS_FEATURE = " isFeature: ";
    private final String GAME_FEATURE_CONTROLLED = "GameFeatureControlledInterface: ";
    
    public void init(int level) throws Exception
    {
        int size = this.list.size();
        for (int index = 0; index < size; index++)
        {
            GameFeatureControlledInterface featureInterface = 
                (GameFeatureControlledInterface) getList().objectArray[index];
            
            boolean isLoadingLevel = featureInterface.isLoadingLevel(level);
            boolean isFeature = featureInterface.isFeature();
            LogUtil.put(LogFactory.getInstance(
                    new StringMaker().append(this.GAME_FEATURE_CONTROLLED)
                            .append(featureInterface.toString())
                            .append(this.IS_LOADING_LEVEL_LABEL)
                            .append(ResourceLoadingLevelFactory.getInstance().getLevelString(level))
                            .append(CommonSeps.getInstance().COLON_SEP)
                            .append(isLoadingLevel)
                            .append(this.IS_FEATURE)
                            .append(isFeature).toString(), 
                    this, CommonStrings.getInstance().INIT));

            if (isLoadingLevel && isFeature)
            {
//                LogUtil.put(LogFactory.getInstance(new StringBuilder()
//                        .append(CommonStrings.getInstance().INIT)
//                        .append(CommonSeps.getInstance().SPACE)
//                        .append(this.GAME_FEATURE_CONTROLLED)
//                        .append(featureInterface.toString()).toString(), 
//                        this, CommonStrings.getInstance().INIT));
                featureInterface.init(level);
            }
        }

        Features features = Features.getInstance();
        
        GraphicsFeatureFactory graphicsFeatureFactory = 
            GraphicsFeatureFactory.getInstance();
        
        StringMaker stringBuffer = new StringMaker();
        
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
