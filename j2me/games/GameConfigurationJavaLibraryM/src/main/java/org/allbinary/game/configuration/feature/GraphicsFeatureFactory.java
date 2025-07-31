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
package org.allbinary.game.configuration.feature;

import org.allbinary.logic.string.StringUtil;

public class GraphicsFeatureFactory
{
    private static final GraphicsFeatureFactory instance = new GraphicsFeatureFactory();

    public static GraphicsFeatureFactory getInstance()
    {
        return instance;
    }

    private GraphicsFeatureFactory()
    {
        
    }

    public final GraphicsFeature NONE = 
        new GraphicsFeature(StringUtil.getInstance().NULL_STRING);
    
    public final GraphicsFeature TRANSPARENT_IMAGE_CREATION = 
        new GraphicsFeature("Transparent Image Creation");
    
    public final GraphicsFeature IMAGE_GRAPHICS = 
        new GraphicsFeature("Image Graphics");

    public final GraphicsFeature IMAGE_ROTATION_ON_THE_FLY = 
        new GraphicsFeature("Image Rotated On The Fly");
    
    public final GraphicsFeature IMAGE_TO_ARRAY_GRAPHICS = 
        new GraphicsFeature("Image To Array Graphics");
    
    public final GraphicsFeature SPRITE_QUARTER_ROTATION_GRAPHICS = 
        new GraphicsFeature("Sprite Quarter Rotation Graphics");

    public final GraphicsFeature SPRITE_FULL_GRAPHICS = 
        new GraphicsFeature("Sprite Full Rotation Graphics");

    public final GraphicsFeature VECTOR_GRAPHICS = 
        new GraphicsFeature("Vector Graphics");
    
    public final GraphicsFeature VECTOR_TO_IMAGE_GRAPHICS = 
        new GraphicsFeature("Vector To Image Graphics");
}
