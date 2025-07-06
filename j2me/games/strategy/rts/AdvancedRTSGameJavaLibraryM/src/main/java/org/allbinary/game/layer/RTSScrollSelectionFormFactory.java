/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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
package org.allbinary.game.layer;

import org.allbinary.graphics.form.item.CustomItem;

import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.form.FormTypeFactory;
import org.allbinary.graphics.form.MultipleScrollSelectionHorizontalForm;
import org.allbinary.input.motion.button.CommonButtons;
import org.allbinary.input.motion.button.TouchButtonInput;
import org.allbinary.input.motion.button.TouchButtonLocationHelper;

public class RTSScrollSelectionFormFactory
{
    public static MultipleScrollSelectionHorizontalForm getInstance(
            final String formLabel, final CustomItem[] items)
    throws Exception
    {
        final BasicColorFactory basicColorFactory = BasicColorFactory.getInstance();
        
        final TouchButtonLocationHelper touchButtonLocationHelper = new TouchButtonLocationHelper();

        final DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();

        final int menuX = CommonButtons.getInstance().STANDARD_BUTTON_SIZE + 
                touchButtonLocationHelper.getColumnsRemainderHalf();
        final int width = displayInfo.getLastWidth() - (menuX * 2);// - TouchButtonInput.STANDARD_BUTTON_SIZE;

        final Rectangle menuRectangle = new Rectangle(
                PointFactory.getInstance().getInstance(menuX, 16), width, 
                CommonButtons.getInstance().STANDARD_BUTTON_SIZE);

        return
            new MultipleScrollSelectionHorizontalForm(
            formLabel, items, menuRectangle, FormTypeFactory.getInstance().HORIZONTAL_FORM, 0,
            basicColorFactory.BLACK, basicColorFactory.WHITE);
    }
}
