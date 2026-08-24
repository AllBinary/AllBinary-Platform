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
package org.allbinary.graphics.form;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;


@JsType
public class ItemPaintableFactory extends ItemPaintable
{

    private static final ItemPaintableFactory instance = new ItemPaintableFactory();

    @JsMethod
    public static ItemPaintableFactory getInstance()
    {
        return ItemPaintableFactory.instance;
    }

    @JsMethod
    public ItemPaintable getInstanceItemPaintable(final PaintableForm paintableForm)
            throws Exception
    {
        return ItemPaintableFactory.instance;
    }

}
