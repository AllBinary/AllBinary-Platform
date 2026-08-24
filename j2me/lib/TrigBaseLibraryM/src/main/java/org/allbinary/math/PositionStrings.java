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
package org.allbinary.math;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;


@JsType
public class PositionStrings
{
    private static final PositionStrings instance = new PositionStrings();

    @JsMethod
    public static PositionStrings getInstance()
    {
        return PositionStrings.instance;
    }

    @JsProperty
    public final String X = "x";
    @JsProperty
    public final String Y = "y";

    @JsProperty
    public final String X_LABEL = "x: ";
    @JsProperty
    public final String Y_LABEL = "y: ";
    @JsProperty
    public final String Z_LABEL = "z: ";

    @JsProperty
    public final String DX_LABEL = "dx: ";
    @JsProperty
    public final String DY_LABEL = "dy: ";
    @JsProperty
    public final String DZ_LABEL = "dz: ";
}
