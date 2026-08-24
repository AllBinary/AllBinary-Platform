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

import org.allbinary.graphics.form.item.ABCustomItem;
import jsinterop.annotations.JsMethod;

/**
 *
 * @author User
 */

@JsType
public class ItemIndexDx {

    private static final ItemIndexDx instance = new ItemIndexDx();
    
    @JsMethod
    public static ItemIndexDx getInstance() {
        return ItemIndexDx.instance;
    }
    
    @JsMethod
    public int getDx(final int index, final ABCustomItem item, int dx, final int dy) throws Exception {
        return 0;
    }

}
