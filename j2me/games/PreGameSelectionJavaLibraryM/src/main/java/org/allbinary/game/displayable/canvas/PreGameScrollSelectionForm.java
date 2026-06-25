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
package org.allbinary.game.displayable.canvas;

import org.allbinary.graphics.form.item.ABCustomItem;

import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.form.FormTypeFactory;
import org.allbinary.graphics.form.ItemPaintableFactory;
import org.allbinary.graphics.form.ScrollCurrentSelectionForm;

/**
 *
 * @author user
 */
public class PreGameScrollSelectionForm
        extends ScrollCurrentSelectionForm
{
    public PreGameScrollSelectionForm(final String title, 
            final ABCustomItem[] items, 
            final ItemPaintableFactory formPaintableFactory,
            final BasicColor backgroundBasicColor, 
            final BasicColor foregroundBasicColor) 
        throws Exception
    {
        super(title, items, formPaintableFactory, 0, 3, false, backgroundBasicColor, foregroundBasicColor);
    }

    public void init(final Rectangle rectangle)
    throws Exception
    {
        super.init(rectangle, FormTypeFactory.getInstance().TEMP_HORIZONTAL_FORM);
    }
    
}
