/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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

import javax.microedition.lcdui.Graphics;
import org.allbinary.graphics.form.item.ABCustomItem;
import jsinterop.annotations.JsMethod;

/**
 *
 * @author User
 */

@JsType
public class ItemIndexPaintable {
    
    private static final ItemIndexPaintable instance = new ItemIndexPaintable();

    /**
     * @return the instance
     */
    @JsMethod
    public static ItemIndexPaintable getInstance() {
        return ItemIndexPaintable.instance;
    }
    
    @JsMethod
    public int paint(final Graphics graphics, final int index, final ABCustomItem item, int dx, final int dy) throws Exception {
        return 0;
    }
    
}
