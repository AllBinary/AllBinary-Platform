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
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class InputMappingHelpPaintable extends HelpPaintable 
{

    private static final String AND = " and ";
    private static final String SEP = ", ";
    private static final String MORE_THAN_TWO_IN_LIST_AND = ", and ";

    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected final CommonSeps commonSeps = CommonSeps.getInstance();
    protected final StringUtil stringUtil = StringUtil.getInstance();
    private final BasicColorFactory basicColorFactory = BasicColorFactory.getInstance();
    
    private final GameKey NONE = GameKeyFactory.getInstance().NONE;
    private final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;

    private GameInputMapping[] gameInputMappingArray;
    
    private BasicArrayList[] keyMappingArray = new BasicArrayList[0];
    private BasicColor[] actionBasicColor = new BasicColor[0];
    private BasicColor[][] inputBasicColorArray = new BasicColor[0][0];
    
    private BasicColor selectedBasicColor;

    private class UpdateMyFontProperties {
        String[] actionStringArray = StringUtil.getInstance().getArrayInstance();
        int[] keymappingBeginWidthArray = NullUtil.getInstance().NULL_INT_ARRAY;
        int[] actionStringDeltaXArray = NullUtil.getInstance().NULL_INT_ARRAY;
        int[][] inputDeltaXArray = NullUtil.getInstance().NULL_INT_ARRAY_ARRAY;
        String[][] sepArray = new String[0][0];
        int[][] sepWidthArray = NullUtil.getInstance().NULL_INT_ARRAY_ARRAY;
        int charHeight;
    }
    
    private UpdateMyFontProperties updateMyFontProperties = new UpdateMyFontProperties();
    
    protected InputMappingHelpPaintable(
            final GameInputMapping[] gameInputMappingArray, 
            final BasicColor backgroundBasicColor, final BasicColor basicColor)
    {        
        super("Input Mapping", backgroundBasicColor, basicColor);
    
        this.gameInputMappingArray = gameInputMappingArray;
        
        this.update(this.NONE, this.NONE);
        
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

    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();

        final UpdateMyFontProperties updateMyFontProperties = new UpdateMyFontProperties();
        updateMyFontProperties.charHeight = font.getHeight();
        
        final StringMaker stringMaker = new StringMaker();
        final String[] inputInfo = this.inputInfo;
        final int size = inputInfo.length;
        
        updateMyFontProperties.actionStringArray = new String[size];
        updateMyFontProperties.keymappingBeginWidthArray = new int[size];
        updateMyFontProperties.actionStringDeltaXArray = new int[size];
        updateMyFontProperties.inputDeltaXArray = new int[size][0];
        updateMyFontProperties.sepArray = new String[size][0];
        updateMyFontProperties.sepWidthArray = new int[size][0];
        
        BasicArrayList list;
        String keyMappings;
        String actionString;        
        Input input;
        int size2 = 0;
        
        for (int index = 0; index < size; index++)
        {            
            list = this.keyMappingArray[index];
            keyMappings = this.get(list);

            //For same line action and mappings
            stringMaker.delete(0, stringMaker.length());
            actionString = stringMaker.append(inputInfo[index]).append(commonSeps.COLON).append(commonSeps.SPACE).append(commonSeps.SPACE).toString();
            updateMyFontProperties.actionStringArray[index] = actionString;
            //For multiline
            //String actionString = inputInfo[index];

            //For same line action and mappings
            stringMaker.delete(0, stringMaker.length());
            updateMyFontProperties.keymappingBeginWidthArray[index] = (font.stringWidth(stringMaker.append(updateMyFontProperties.actionStringArray[index]).append(keyMappings).toString()) >> 1);
            updateMyFontProperties.actionStringDeltaXArray[index] = font.stringWidth(actionString);

            size2 = list.size();

            updateMyFontProperties.inputDeltaXArray[index] = new int[size2];
            updateMyFontProperties.sepArray[index] = new String[size2];
            updateMyFontProperties.sepWidthArray[index] = new int[size2];

            for(int index2 = 0; index2 < size2; index2++)
            {
                input = (Input) list.objectArray[index2];
                updateMyFontProperties.inputDeltaXArray[index][index2] = font.stringWidth(input.getName());

                updateMyFontProperties.sepArray[index][index2] = EMPTY_STRING;

                if(index2 + 1 < list.size())
                {
                    if(list.size() == 2)
                    {
                        updateMyFontProperties.sepArray[index][index2] = InputMappingHelpPaintable.AND;
                    }
                    else
                    {
                        if(index2 + 2 == list.size())
                        {
                            updateMyFontProperties.sepArray[index][index2] = InputMappingHelpPaintable.MORE_THAN_TWO_IN_LIST_AND;
                        }
                        else
                        {
                            updateMyFontProperties.sepArray[index][index2] = InputMappingHelpPaintable.SEP;
                        }
                    }
                }

                if(updateMyFontProperties.sepArray[index][index2] != EMPTY_STRING)
                {
                    updateMyFontProperties.sepWidthArray[index][index2] = font.stringWidth(updateMyFontProperties.sepArray[index][index2]);
                }
                
            }
        }

        this.updateMyFontProperties = updateMyFontProperties;
        super.updateMeasurement(graphics);
    }
    

    public void update(final GameKey selectedGameKey, final Input selectedInput)
    {
        final StringMaker stringMaker = new StringMaker();
        
        this.logUtil.putF(stringMaker.append(CommonLabels.getInstance().START_LABEL).append("selected GameKey: ").append(this.stringUtil.toString(selectedGameKey)).append(" Input: ").append(this.stringUtil.toString(selectedInput)).toString(), this, this.commonStrings.UPDATE);
        
        final PersistentInputMapping gameKeyMapping = 
            PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance();
        
        final int size = this.gameInputMappingArray.length;
        final String[] keyInfo = new String[size];
        final BasicArrayList[] keyMappingArray = new BasicArrayList[size];
        final BasicColor[] actionBasicColor = new BasicColor[size];
        final BasicColor[][] inputBasicColorArray = new BasicColor[size][];
        
        GameInputMapping gameInputMapping;
        GameKey gameKey;
        BasicArrayList list;
        for(int index = 0; index < size; index++)
        {
            gameInputMapping = this.gameInputMappingArray[index];
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
                this.logUtil.putF(stringMaker.append("Found: selected GameKey: ").append(this.stringUtil.toString(selectedGameKey)).toString(), this, this.commonStrings.UPDATE);
                actionBasicColor[index] = this.selectedBasicColor;
                int indexOfSelectedInput = list.indexOf(selectedInput);
                if(indexOfSelectedInput >= 0)
                {
                    stringMaker.delete(0, stringMaker.length());
                    this.logUtil.putF(stringMaker.append("Found: selected Input: ").append(this.stringUtil.toString(selectedInput)).toString(), this, this.commonStrings.UPDATE);
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

        super.setInputInfoP(keyInfo);
        this.myFontProcessor = this.updateMyFontProcessor;
    }
    
    private String get(BasicArrayList keyList)
    {
        final StringMaker stringBuffer = new StringMaker();

        Input key;
        final int size = keyList.size();
        for(int index = 0; index < size; index++)
        {
            key = (Input) keyList.objectArray[index];

            //Get system platform key(s) mapped to gamekey
            stringBuffer.append(key.getName());
            if(index + 1 < keyList.size())
            {
                if(keyList.size() == 2)
                {
                    stringBuffer.append(InputMappingHelpPaintable.AND);
                }
                else
                {
                    if(index + 2 == keyList.size())
                    {
                        stringBuffer.append(InputMappingHelpPaintable.MORE_THAN_TWO_IN_LIST_AND);
                    }
                    else
                    {
                        stringBuffer.append(InputMappingHelpPaintable.SEP);
                    }
                }
            }
        }
        
        return stringBuffer.toString();
    }

//    @Override    
//    public int getHeight()
//    {
//        final MyFont myFont = MyFont.getInstance();
//        final String[] inputInfo = this.inputInfo;
//        final int size = (inputInfo.length + 4);
//        return myFont.DEFAULT_CHAR_HEIGHT * size;
//    }
    
    @Override
    public void paint(final Graphics graphics)
    {
        this.myFontProcessor.process(graphics);

        //this.colorFillPaintable.paint(graphics);
        
        final int halfWidth = this.displayInfo.getLastHalfWidth();

        graphics.setColor(this.basicColor.intValue());
        
        graphics.drawString(this.title, halfWidth - this.titleBeginWidth, this.updateMyFontProperties.charHeight, this.anchor);

        final String[] inputInfo = this.inputInfo;
        final int size = inputInfo.length;
        int y = 0;
        int deltaX = 0;
        int size2 = 0;
        
        String actionString;
        Input input;
        BasicArrayList list;
        String sep;
        int beginWidth;
        for (int index = 0; index < size; index++)
        {
            //For same line action and mappings
            y = (index + 3) * this.updateMyFontProperties.charHeight;
            //For multiline
            //y = ((index * 2) + 3) * this.myFont.DEFAULT_CHAR_HEIGHT;
            
            deltaX = 0;
            list = this.keyMappingArray[index];
            size2 = list.size();

            beginWidth = this.updateMyFontProperties.keymappingBeginWidthArray[index];
            
            //For same line action and mappings
            //For multiline
            //beginWidth = (font.stringWidth(actionString) >> 1);
            
            graphics.setColor(this.actionBasicColor[index].intValue());

            actionString = this.updateMyFontProperties.actionStringArray[index];
            graphics.drawString(actionString, halfWidth - beginWidth + deltaX, y, this.anchor);
            
            //For same line action and mappings
            deltaX += this.updateMyFontProperties.actionStringDeltaXArray[index];
            //For multiline
            //y = ((index * 2) + 4) * myFont.DEFAULT_CHAR_HEIGHT;
            //beginWidth = (font.stringWidth(keyMappings) >> 1);

            for(int index2 = 0; index2 < size2; index2++)
            {
                input = (Input) list.objectArray[index2];

                graphics.setColor(this.inputBasicColorArray[index][index2].intValue());
                graphics.drawString(input.getName(), halfWidth - beginWidth + deltaX, y, this.anchor);

                deltaX += this.updateMyFontProperties.inputDeltaXArray[index][index2];

                sep = this.updateMyFontProperties.sepArray[index][index2];
                graphics.setColor(this.basicColor.intValue());
                graphics.drawString(sep, halfWidth - beginWidth + deltaX, y, this.anchor);
                deltaX += this.updateMyFontProperties.sepWidthArray[index][index2];

            }
        }
    }
    
}
