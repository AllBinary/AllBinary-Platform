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

import org.allbinary.graphics.opengles.OpenGLFeatureUtil;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GraphicsFeature;
import org.allbinary.game.configuration.feature.GraphicsFeatureFactory;
import org.allbinary.graphics.displayable.CanvasStrings;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class GameGraphicsResourceUtil
{
    private static final GameGraphicsResourceUtil instance = new GameGraphicsResourceUtil();

    public static GameGraphicsResourceUtil getInstance()
    {
        return instance;
    }
    
    public final String SPRITE = "_sprite";
    public final String QUARTER = "_quarter";

    private String name;

    public GameGraphicsResourceUtil()
    {
        try
        {
            this.name = this.getString();
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().CONSTRUCTOR, e));
        }
    }
    
    private String getString()
    throws Exception
    {
        GraphicsFeatureFactory graphicsFeatureFactory = 
            GraphicsFeatureFactory.getInstance();

        GraphicsFeature graphicsFeature = null;
        
        Features features = Features.getInstance();
                
        if (features.isFeature(graphicsFeatureFactory.IMAGE_TO_ARRAY_GRAPHICS))
        {
            graphicsFeature = graphicsFeatureFactory.IMAGE_TO_ARRAY_GRAPHICS;
        }
        else 
            if (features.isFeature(graphicsFeatureFactory.SPRITE_FULL_GRAPHICS))
        {
            graphicsFeature = graphicsFeatureFactory.SPRITE_FULL_GRAPHICS;
        }
        else if (features.isFeature(
                graphicsFeatureFactory.SPRITE_QUARTER_ROTATION_GRAPHICS))
        {
            graphicsFeature = graphicsFeatureFactory.SPRITE_QUARTER_ROTATION_GRAPHICS;
        }
        else
        if (features.isFeature(graphicsFeatureFactory.IMAGE_ROTATION_ON_THE_FLY))
        {
            graphicsFeature = graphicsFeatureFactory.IMAGE_ROTATION_ON_THE_FLY;
        }
        return getString(graphicsFeature);
    }

    public String getString(GraphicsFeature graphicsFeature)
    throws Exception
    {
        final StringMaker stringBuffer = new StringMaker();
        
        GraphicsFeatureFactory graphicsFeatureFactory = 
            GraphicsFeatureFactory.getInstance();
        
        stringBuffer.delete(0, stringBuffer.length());
        
        Features features = Features.getInstance();
                
        if (features.isFeature(graphicsFeatureFactory.VECTOR_GRAPHICS))
        {
        }
        else if (features.isFeature(graphicsFeatureFactory.IMAGE_GRAPHICS))
        {
            if (graphicsFeature == graphicsFeatureFactory.IMAGE_TO_ARRAY_GRAPHICS ||
                    graphicsFeature == graphicsFeatureFactory.IMAGE_ROTATION_ON_THE_FLY)
            {
            }
            else if (graphicsFeature == graphicsFeatureFactory.SPRITE_FULL_GRAPHICS)
            {
                stringBuffer.append(SPRITE);
            }
            else if (graphicsFeature == graphicsFeatureFactory.SPRITE_QUARTER_ROTATION_GRAPHICS)
            {
                stringBuffer.append(QUARTER);
                stringBuffer.append(SPRITE);
            }
            else
            {
                throw new Exception("None/Unknown Sub Image Resource Type Specified");
            }
        }
        else
            if(OpenGLFeatureUtil.getInstance().isAnyThreed())
        {
                stringBuffer.append(CanvasStrings.getInstance().OBJ_MODEL);
        }
            else
            {
                throw new Exception("None/Unknown Main Image Resource Type Specified");
            }
        return stringBuffer.toString();
    }

    public String getName()
    {
        return name;
    }
}
