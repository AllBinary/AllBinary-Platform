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
package org.allbinary.game.paint.help;

import javax.microedition.lcdui.Graphics;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.input.GameKey;
import org.allbinary.game.input.GameKeyFactory;
import org.allbinary.game.input.Input;
import org.allbinary.game.input.PlatformInputMappingFactory;
import org.allbinary.game.input.mapping.GameInputMapping;
import org.allbinary.game.input.mapping.PersistentInputMapping;
import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.logic.string.CommonLabels;

public class InputMappingHelpPaintable extends HelpPaintable 
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private GameInputMapping[] gameInputMappingArray;
    
    private BasicArrayList[] keyMappingArray = new BasicArrayList[0];
    private BasicColor[] actionBasicColor = new BasicColor[0];
    private BasicColor[][] inputBasicColorArray = new BasicColor[0][0];

    private static final String AND = " and ";
    private static final String SEP = ", ";
    private static final String MORE_THAN_TWO_IN_LIST_AND = ", and ";
    
    private BasicColor selectedBasicColor;
    
    private final GameKey NONE = GameKeyFactory.getInstance().NONE;

    private final BasicColorFactory basicColorFactory = BasicColorFactory.getInstance();
    
    protected InputMappingHelpPaintable(
            GameInputMapping[] gameInputMappingArray, 
            BasicColor backgroundBasicColor, BasicColor basicColor)
    {        
        super("Input Mapping", backgroundBasicColor, basicColor);
    
        this.gameInputMappingArray = gameInputMappingArray;
        
        this.update(NONE, NONE);
        
        if(backgroundBasicColor == this.basicColorFactory.WHITE ||
            basicColor == this.basicColorFactory.WHITE)
        {
            this.selectedBasicColor = this.basicColorFactory.RED;
        }
        else
        {
            this.selectedBasicColor = this.basicColorFactory.WHITE;
        }
    }
    
    public void update(GameKey selectedGameKey, Input selectedInput)
    {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append("selected GameKey: ").append(selectedGameKey).append(" Input: ").append(selectedInput).toString(), this, commonStrings.UPDATE));
        
        PersistentInputMapping gameKeyMapping = 
            PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance();
        
        int size = gameInputMappingArray.length;
        String[] keyInfo = new String[size];
        BasicArrayList[] keyMappingArray = new BasicArrayList[size];
        BasicColor[] actionBasicColor = new BasicColor[size];
        BasicColor[][] inputBasicColorArray = new BasicColor[size][];
        
        for(int index = 0; index < size; index++)
        {
            GameInputMapping gameInputMapping = gameInputMappingArray[index];
            GameKey gameKey = gameInputMapping.getGameKey();
            BasicArrayList list = gameKeyMapping.getInputMapping().getMappedInput(gameKey);
            
            int size2 = list.size();
            inputBasicColorArray[index] = new BasicColor[size2];
            
            for(int index2 = 0; index2 < size2; index2++)
            {
                inputBasicColorArray[index][index2] = this.basicColor;
            }
            
            if(gameKey == selectedGameKey)
            {
                LogUtil.put(LogFactory.getInstance(new StringMaker().append("Found: selected GameKey: ").append(selectedGameKey).toString(), this, commonStrings.UPDATE));
                actionBasicColor[index] = this.selectedBasicColor;
                int indexOfSelectedInput = list.indexOf(selectedInput);
                if(indexOfSelectedInput >= 0)
                {
                    LogUtil.put(LogFactory.getInstance(new StringMaker().append("Found: selected Input: ").append(selectedInput).toString(), this, commonStrings.UPDATE));
                    inputBasicColorArray[index][indexOfSelectedInput] = this.selectedBasicColor; 
                }
            }
            else
            {
                actionBasicColor[index] = this.basicColor;
            }
            
            //keyInfo[index] = gameInputMapping.getName()).append(" = ").append(this.get(list);
            keyInfo[index] = gameInputMapping.getName();
            keyMappingArray[index] = list;
            //keyInfo[index] = this.get(list);
        }
        this.keyMappingArray = keyMappingArray;
        this.actionBasicColor = actionBasicColor;
        this.inputBasicColorArray = inputBasicColorArray;

        super.setInputInfo(keyInfo);
    }
    
    private String get(BasicArrayList keyList)
    {
        StringMaker stringBuffer = new StringMaker();

        for(int index = 0; index < keyList.size(); index++)
        {
            Input key = (Input) keyList.objectArray[index];

            //Get system platform key(s) mapped to gamekey
            stringBuffer.append(key.getName());
            if(index + 1 < keyList.size())
            {
                if(keyList.size() == 2)
                {
                    stringBuffer.append(AND);
                }
                else
                {
                    if(index + 2 == keyList.size())
                    {
                        stringBuffer.append(MORE_THAN_TWO_IN_LIST_AND);
                    }
                    else
                    {
                        stringBuffer.append(SEP);
                    }
                }
            }
        }
        
        return stringBuffer.toString();
    }
    
    public int getHeight()
    {
        return MyFont.getInstance().DEFAULT_CHAR_HEIGHT * (this.getInputInfo().length + 4);
    }
    
    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(Graphics graphics)
    {
        //this.colorFillPaintable.paint(graphics);
        
        final int charHeight = MyFont.getInstance().DEFAULT_CHAR_HEIGHT;
        
        int halfWidth = DisplayInfoSingleton.getInstance().getLastHalfWidth();

        int beginWidth = (graphics.getFont().stringWidth(this.TITLE) >> 1);

        graphics.setColor(this.basicColor.intValue());
        
        graphics.drawString(this.TITLE, halfWidth - beginWidth, charHeight, anchor);

        int size = getInputInfo().length;
        int y = 0;
        int deltaX = 0;
        int size2 = 0;
        
        for (int index = 0; index < size; index++)
        {
            //For same line action and mappings
            y = (index + 3) * charHeight;
            //For multiline
            //y = ((index * 2) + 3) * MyFont.MYFONT.DEFAULT_CHAR_HEIGHT;
            
            deltaX = 0;
            BasicArrayList list = this.keyMappingArray[index];
            size2 = list.size();
            String keyMappings = this.get(list);

            //For same line action and mappings
            final String actionString = new StringMaker().append(getInputInfo()[index]).append(": ").toString();
            //For multiline
            //String actionString = getInputInfo()[index];

            //For same line action and mappings
            beginWidth = (graphics.getFont().stringWidth(new StringMaker().append(actionString).append(keyMappings).toString()) >> 1);
            //For multiline
            //beginWidth = (graphics.getFont().stringWidth(actionString) >> 1);

            graphics.setColor(this.actionBasicColor[index].intValue());

            graphics.drawString(actionString,
                    halfWidth - beginWidth + deltaX, y, anchor);

            //For same line action and mappings
            deltaX += graphics.getFont().stringWidth(actionString);
            //For multiline
            //y = ((index * 2) + 4) * MyFont.MYFONT.DEFAULT_CHAR_HEIGHT;
            //beginWidth = (graphics.getFont().stringWidth(keyMappings) >> 1);

            for(int index2 = 0; index2 < size2; index2++)
            {
                Input input = (Input) list.objectArray[index2];

                graphics.setColor(this.inputBasicColorArray[index][index2].intValue());
                graphics.drawString(input.getName(), 
                        halfWidth - beginWidth + deltaX, y, anchor);

                deltaX += graphics.getFont().stringWidth(input.getName());

                String sep = StringUtil.getInstance().EMPTY_STRING;

                if(index2 + 1 < list.size())
                {
                    if(list.size() == 2)
                    {
                        sep = AND;
                    }
                    else
                    {
                        if(index2 + 2 == list.size())
                        {
                            sep = MORE_THAN_TWO_IN_LIST_AND;
                        }
                        else
                        {
                            sep = SEP;
                        }
                    }
                }

                if(sep != StringUtil.getInstance().EMPTY_STRING)
                {
                    graphics.setColor(this.basicColor.intValue());
                    graphics.drawString(sep, halfWidth - beginWidth + deltaX, y, anchor);
                    deltaX += graphics.getFont().stringWidth(sep);
                }
            }
        }
    }
    
}
