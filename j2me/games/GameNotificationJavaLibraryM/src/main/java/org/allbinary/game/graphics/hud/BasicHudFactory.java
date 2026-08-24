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
package org.allbinary.game.graphics.hud;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class BasicHudFactory
{
    private static final BasicHudFactory instance = new BasicHudFactory();
    
    @JsMethod
    public static BasicHudFactory getInstance()
    {
        return BasicHudFactory.instance;
    }

    @JsConstructor
    private BasicHudFactory()
    {
        
    }
    
    @JsProperty
    public final String DIRECTION_EXCEPTION =
        "Only Horizontal Direction is Allowed Currently";
    
   //Direction
   @JsProperty
   public final int VERTICAL = 0;
   @JsProperty
   public final int HORIZONTAL = 1;
   
   //Location
   @JsProperty
   public final int BOTTOMLEFT = 0;
   @JsProperty
   public final int BOTTOMRIGHT = 1;
   @JsProperty
   public final int TOPLEFT = 2;
   @JsProperty
   public final int TOPRIGHT = 3;
   
   @JsProperty
   public final int BOTTOMCENTER = 4;
   @JsProperty
   public final int TOPCENTER = 5;
   @JsProperty
   public final int ABSOLUTE = 6;
}
