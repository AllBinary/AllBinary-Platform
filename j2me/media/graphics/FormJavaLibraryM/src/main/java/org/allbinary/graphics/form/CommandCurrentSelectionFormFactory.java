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

import org.allbinary.graphics.form.item.CustomItem;

import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.TouchFeatureFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;

public class CommandCurrentSelectionFormFactory
{
    public static ScrollSelectionForm getInstance(
            String title, CustomItem[] items, 
            Rectangle rectangle, FormType formType, 
            int border, boolean moveForSmallScreen,
            BasicColor backgroundBasicColor, BasicColor foregroundBasicColor
            )
        throws Exception
    {
        if(Features.getInstance().isFeature(TouchFeatureFactory.getInstance().TOUCH_ENABLED))
        {
            return new CommandCurrentSelectionForm(
                    title, items, rectangle, formType, border, moveForSmallScreen,
                    backgroundBasicColor, foregroundBasicColor);
        }
        else
        {
            return ScrollSelectionFormNoneFactory.getInstance();
        }
    }
}
