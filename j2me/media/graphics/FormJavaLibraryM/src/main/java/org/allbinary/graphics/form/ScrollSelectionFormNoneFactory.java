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

import org.allbinary.graphics.RectangleFactory;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.form.item.CustomItem;
import org.allbinary.logic.string.StringUtil;

public class ScrollSelectionFormNoneFactory
{
    private static final ScrollSelectionForm instance = 
        new ScrollSelectionForm(
                StringUtil.getInstance().EMPTY_STRING, 
                new CustomItem[0], 
                RectangleFactory.SINGLETON, 
                FormTypeFactory.getInstance().HORIZONTAL_FORM, 0, 
                BasicColorFactory.getInstance().BLACK, 
                BasicColorFactory.getInstance().WHITE
                );

    public static ScrollSelectionForm getInstance()
    {
        return instance;
    }
}
