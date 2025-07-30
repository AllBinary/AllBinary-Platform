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

import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GameFeatureControlledInterface;
import org.allbinary.game.configuration.feature.GraphicsFeatureFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

/**
 * 
 * @author user
 */
public class FeaturedResourceFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final BasicArrayList list = new BasicArrayList();

    public FeaturedResourceFactory()
    {
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
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
                (GameFeatureControlledInterface) this.list.objectArray[index];
            
            boolean isLoadingLevel = featureInterface.isLoadingLevel(level);
            boolean isFeature = featureInterface.isFeature();
            logUtil.put(
                    new StringMaker().append(this.GAME_FEATURE_CONTROLLED)
                            .append(featureInterface.toString())
                            .append(this.IS_LOADING_LEVEL_LABEL)
                            .append(ResourceLoadingLevelFactory.getInstance().getLevelString(level))
                            .append(CommonSeps.getInstance().COLON_SEP)
                            .append(isLoadingLevel)
                            .append(this.IS_FEATURE)
                            .append(isFeature).toString(), 
                    this, commonStrings.INIT);

            if (isLoadingLevel && isFeature)
            {
//                logUtil.put(new StringBuilder()
//                        .append(commonStrings.INIT)
//                        .append(CommonSeps.getInstance().SPACE)
//                        .append(this.GAME_FEATURE_CONTROLLED)
//                        .append(featureInterface.toString()).toString(), 
//                        this, commonStrings.INIT);
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
        
        logUtil.put(stringBuffer.toString(), this, commonStrings.INIT);

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

            logUtil.put(stringBuffer.toString(), this, commonStrings.INIT);
        }
    }
    
    public void clear()
    {
        this.list.clear();
    }

    public void add(GameFeatureControlledInterface featureInterface)
    {
        logUtil.put(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append(StringUtil.getInstance().toString(featureInterface)).toString(), this, commonStrings.ADD);
        
        this.list.add(featureInterface);
    }

    public BasicArrayList getList()
    {
        return list;
    }
}
