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

import jsinterop.annotations.JsType;

import org.allbinary.logic.string.StringUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class GraphicsFeatureFactory
{
    private static final GraphicsFeatureFactory instance = new GraphicsFeatureFactory();

    @JsMethod
    public static GraphicsFeatureFactory getInstance()
    {
        return GraphicsFeatureFactory.instance;
    }

    @JsConstructor
    private GraphicsFeatureFactory()
    {
        
    }

    @JsProperty
    public final GraphicsFeature NONE = 
        new GraphicsFeature(StringUtil.getInstance().NULL_STRING);
    
    @JsProperty
    public final GraphicsFeature TRANSPARENT_IMAGE_CREATION = 
        new GraphicsFeature("Transparent Image Creation");
    
    @JsProperty
    public final GraphicsFeature IMAGE_GRAPHICS = 
        new GraphicsFeature("Image Graphics");

    @JsProperty
    public final GraphicsFeature IMAGE_ROTATION_ON_THE_FLY = 
        new GraphicsFeature("Image Rotated On The Fly");
    
    @JsProperty
    public final GraphicsFeature IMAGE_TO_ARRAY_GRAPHICS = 
        new GraphicsFeature("Image To Array Graphics");
    
    @JsProperty
    public final GraphicsFeature SPRITE_QUARTER_ROTATION_GRAPHICS = 
        new GraphicsFeature("Sprite Quarter Rotation Graphics");

    @JsProperty
    public final GraphicsFeature SPRITE_FULL_GRAPHICS = 
        new GraphicsFeature("Sprite Full Rotation Graphics");

    @JsProperty
    public final GraphicsFeature VECTOR_GRAPHICS = 
        new GraphicsFeature("Vector Graphics");
    
    @JsProperty
    public final GraphicsFeature VECTOR_TO_IMAGE_GRAPHICS = 
        new GraphicsFeature("Vector To Image Graphics");
}
