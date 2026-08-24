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
package org.allbinary.view.event;

import jsinterop.annotations.JsType;

import org.allbinary.logic.util.event.AllBinaryEventObject;
import jsinterop.annotations.JsConstructor;


@JsType
public class ViewPositionEvent extends AllBinaryEventObject
{
    @JsConstructor
    public ViewPositionEvent(Object object)
    {
        super(object);
    }
}
