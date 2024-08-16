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

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.game.input.GameKey;
import org.allbinary.game.input.GameKeyFactory;
import org.allbinary.game.input.Input;
import org.allbinary.game.input.PlatformInputMappingFactory;
import org.allbinary.game.input.mapping.GameInputMapping;
import org.allbinary.game.input.mapping.PersistentInputMapping;
import org.allbinary.graphics.color.BasicColor;

public class BasicInputMappingHelpPaintable extends HelpPaintable 
{
    private GameInputMapping[] gameInputMappingArray;

    private final GameKey NONE = GameKeyFactory.getInstance().NONE;

    protected BasicInputMappingHelpPaintable(final GameInputMapping[] gameInputMappingArray, final BasicColor basicColor)
    {
        super(basicColor);
    
        this.gameInputMappingArray = gameInputMappingArray;
        
        this.update(NONE, NONE);
    }
    
    public void update(final GameKey selectedGameKey, final Input selectedInput)
    {
        final PersistentInputMapping gameKeyMapping = 
            PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance();
        
        final String EQUAL = " = ";
        
        final StringMaker stringMaker = new StringMaker();
        final int size = gameInputMappingArray.length;
        final String[] keyInfo = new String[size];
        GameInputMapping gameInputMapping;
        GameKey gameKey;
        BasicArrayList list;
        for(int index = 0; index < size; index++)
        {
            gameInputMapping = gameInputMappingArray[index];
            gameKey = gameInputMapping.getGameKey();
            list = gameKeyMapping.getInputMapping().getMappedInput(gameKey);
            
            stringMaker.delete(0, stringMaker.length());
            keyInfo[index] = stringMaker.append(gameInputMapping.getName()).append(EQUAL).append(this.get(list)).toString();
        }
        super.setInputInfo(keyInfo);
    }
    
    private String get(final BasicArrayList keyList)
    {
        final CommonSeps commonSeps = CommonSeps.getInstance();

        final StringMaker stringBuffer = new StringMaker();

        final String AND = "and ";

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
                    stringBuffer.append(commonSeps.SPACE).append(AND);
                }
                else
                {
                    if(index + 2 == keyList.size())
                    {
                        stringBuffer.append(commonSeps.COMMA_SEP);
                        stringBuffer.append(AND);
                    }
                    else
                    {
                        stringBuffer.append(commonSeps.COMMA_SEP);
                    }
                }
            }
        }
        
        return stringBuffer.toString();
    }
}
