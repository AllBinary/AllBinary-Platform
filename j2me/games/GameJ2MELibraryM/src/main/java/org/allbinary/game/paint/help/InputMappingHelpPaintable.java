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

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

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
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class InputMappingHelpPaintable extends HelpPaintable 
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected StringUtil stringUtil = StringUtil.getInstance();
    
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
            final GameInputMapping[] gameInputMappingArray, 
            final BasicColor backgroundBasicColor, final BasicColor basicColor)
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
    
    public void update(final GameKey selectedGameKey, final Input selectedInput)
    {
        final StringMaker stringMaker = new StringMaker();
        
        logUtil.put(stringMaker.append(CommonLabels.getInstance().START_LABEL).append("selected GameKey: ").append(this.stringUtil.toString(selectedGameKey)).append(" Input: ").append(this.stringUtil.toString(selectedInput)).toString(), this, commonStrings.UPDATE);
        
        final PersistentInputMapping gameKeyMapping = 
            PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance();
        
        final int size = gameInputMappingArray.length;
        final String[] keyInfo = new String[size];
        final BasicArrayList[] keyMappingArray = new BasicArrayList[size];
        final BasicColor[] actionBasicColor = new BasicColor[size];
        final BasicColor[][] inputBasicColorArray = new BasicColor[size][];
        
        GameInputMapping gameInputMapping;
        GameKey gameKey;
        BasicArrayList list;
        for(int index = 0; index < size; index++)
        {
            gameInputMapping = gameInputMappingArray[index];
            gameKey = gameInputMapping.getGameKey();
            list = gameKeyMapping.getInputMapping().getMappedInput(gameKey);
            
            int size2 = list.size();
            inputBasicColorArray[index] = new BasicColor[size2];
            
            for(int index2 = 0; index2 < size2; index2++)
            {
                inputBasicColorArray[index][index2] = this.basicColor;
            }
            
            if(gameKey == selectedGameKey)
            {
                stringMaker.delete(0, stringMaker.length());
                logUtil.put(stringMaker.append("Found: selected GameKey: ").append(this.stringUtil.toString(selectedGameKey)).toString(), this, commonStrings.UPDATE);
                actionBasicColor[index] = this.selectedBasicColor;
                int indexOfSelectedInput = list.indexOf(selectedInput);
                if(indexOfSelectedInput >= 0)
                {
                    stringMaker.delete(0, stringMaker.length());
                    logUtil.put(stringMaker.append("Found: selected Input: ").append(this.stringUtil.toString(selectedInput)).toString(), this, commonStrings.UPDATE);
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
        final StringMaker stringBuffer = new StringMaker();

        Input key;
        for(int index = 0; index < keyList.size(); index++)
        {
            key = (Input) keyList.objectArray[index];

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
        final MyFont myFont = MyFont.getInstance();
        return myFont.DEFAULT_CHAR_HEIGHT * (this.inputInfo.length + 4);
    }
    
    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(final Graphics graphics)
    {
        //this.colorFillPaintable.paint(graphics);
        
        final Font font = graphics.getFont();

        final CommonSeps commonSeps = CommonSeps.getInstance();
        
        final StringMaker stringMaker = new StringMaker();
        final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;
        
        final MyFont myFont = MyFont.getInstance();
        final int charHeight = myFont.DEFAULT_CHAR_HEIGHT;
        final int halfWidth = DisplayInfoSingleton.getInstance().getLastHalfWidth();

        int beginWidth = (font.stringWidth(this.TITLE) >> 1);

        graphics.setColor(this.basicColor.intValue());
        
        graphics.drawString(this.TITLE, halfWidth - beginWidth, charHeight, anchor);

        int size = inputInfo.length;
        int y = 0;
        int deltaX = 0;
        int size2 = 0;
        
        Input input;
        String actionString;
        BasicArrayList list;
        String keyMappings;
        String sep;
        for (int index = 0; index < size; index++)
        {
            //For same line action and mappings
            y = (index + 3) * charHeight;
            //For multiline
            //y = ((index * 2) + 3) * this.myFont.DEFAULT_CHAR_HEIGHT;
            
            deltaX = 0;
            list = this.keyMappingArray[index];
            size2 = list.size();
            keyMappings = this.get(list);

            //For same line action and mappings
            stringMaker.delete(0, stringMaker.length());
            actionString = stringMaker.append(inputInfo[index]).append(commonSeps.COLON).append(commonSeps.SPACE).append(commonSeps.SPACE).toString();
            //For multiline
            //String actionString = inputInfo[index];

            //For same line action and mappings
            stringMaker.delete(0, stringMaker.length());
            beginWidth = (font.stringWidth(stringMaker.append(actionString).append(keyMappings).toString()) >> 1);
            //For multiline
            //beginWidth = (font.stringWidth(actionString) >> 1);

            graphics.setColor(this.actionBasicColor[index].intValue());

            graphics.drawString(actionString, halfWidth - beginWidth + deltaX, y, anchor);
            
            //For same line action and mappings
            deltaX += font.stringWidth(actionString);
            //For multiline
            //y = ((index * 2) + 4) * myFont.DEFAULT_CHAR_HEIGHT;
            //beginWidth = (font.stringWidth(keyMappings) >> 1);

            for(int index2 = 0; index2 < size2; index2++)
            {
                input = (Input) list.objectArray[index2];

                graphics.setColor(this.inputBasicColorArray[index][index2].intValue());
                graphics.drawString(input.getName(), halfWidth - beginWidth + deltaX, y, anchor);

                deltaX += font.stringWidth(input.getName());

                sep = EMPTY_STRING;

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

                if(sep != EMPTY_STRING)
                {
                    graphics.setColor(this.basicColor.intValue());
                    graphics.drawString(sep, halfWidth - beginWidth + deltaX, y, anchor);
                    deltaX += font.stringWidth(sep);
                }
            }
        }
    }
    
}
