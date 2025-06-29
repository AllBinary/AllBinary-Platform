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
package org.allbinary.graphics.form.item;

import java.util.Vector;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.ImageItem;

import org.allbinary.graphics.form.item.CustomItem;
import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.string.StringUtil;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.form.ScreenInfo;
import org.allbinary.logic.util.visitor.Visitor;

public class CommandTextItemArrayFactory
{    
    private final BasicArrayList list = new BasicArrayList();
 
    private final Visitor visitorInterface;

    public CommandTextItemArrayFactory( 
            Visitor visitorInterface)
    {
        this.visitorInterface = visitorInterface;
    }

    public final CustomItem[] getInstance(Vector vector, 
            BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        int size = vector.size();

        list.clear();
        
        CommandTextItem textItem;
        
        int priorityLimit = 7;
        
        DisplayInfoSingleton displayInfo =
            DisplayInfoSingleton.getInstance();
        
        boolean isLargeEnoughDisplayForBigMenu =
            (displayInfo.isPortrait() && displayInfo.getLastHeight() >= ScreenInfo.getInstance().MEDIUM_WIDTH) ||
            (!displayInfo.isPortrait() && displayInfo.getLastWidth() >= ScreenInfo.getInstance().MEDIUM_WIDTH);
        
        if(!isLargeEnoughDisplayForBigMenu && size > 3)
        {
            priorityLimit = 3;
        }
        
        for (int index = 0; index < size; index++)
        {
            Command command = (Command) vector.elementAt(index);

            //isNotPriority || command.getPriority() == 3
            if (command.getPriority() < priorityLimit)
            {
                //LogUtil.put(LogFactory.getInstance("Label: "
                  //      + command.getLabel(), this, commonStrings.GET_INSTANCE));

                //PreLogUtil.put("Label: "
                  //      + command.getLabel(), this, commonStrings.GET_INSTANCE);
                
                Boolean aBoolean = (Boolean) this.visitorInterface.visit(command);
                if(aBoolean.booleanValue())
                {
                    textItem = new CommandTextItem(
                            command, ImageItem.LAYOUT_DEFAULT, 
                            StringUtil.getInstance().EMPTY_STRING, 
                            backgroundBasicColor, foregroundBasicColor);
                    
                    list.add(textItem);
                }
            }
        }

        CustomItem[] textItemArray = new CustomItem[list.size()];
        
        int size2 = textItemArray.length;
        for (int index = 0; index < size2; index++)
        {
            textItemArray[index] = (CustomItem) list.objectArray[index];
        }

        return textItemArray;
    }
}
