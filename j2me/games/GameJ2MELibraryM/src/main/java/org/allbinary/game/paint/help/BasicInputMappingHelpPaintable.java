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

import org.allbinary.logic.basic.string.CommonSeps;
import org.allbinary.logic.basic.string.StringMaker;
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

    protected BasicInputMappingHelpPaintable(GameInputMapping[] gameInputMappingArray, BasicColor basicColor)
    {
        super(basicColor);
    
        this.gameInputMappingArray = gameInputMappingArray;
        
        this.update(NONE, NONE);
    }
    
    public void update(GameKey selectedGameKey, Input selectedInput)
    {
        PersistentInputMapping gameKeyMapping = 
            PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance();
        
        final StringMaker stringMaker = new StringMaker();
        final int size = gameInputMappingArray.length;
        final String[] keyInfo = new String[size];
        for(int index = 0; index < size; index++)
        {
            GameInputMapping gameInputMapping = gameInputMappingArray[index];
            GameKey gameKey = gameInputMapping.getGameKey();
            BasicArrayList list = gameKeyMapping.getInputMapping().getMappedInput(gameKey);
            
            stringMaker.delete(0, stringMaker.length());
            keyInfo[index] = stringMaker.append(gameInputMapping.getName()).append(" = ").append(this.get(list)).toString();
        }
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
                    stringBuffer.append(" and ");
                }
                else
                {
                    if(index + 2 == keyList.size())
                    {
                        stringBuffer.append(CommonSeps.getInstance().COMMA_SEP);
                        stringBuffer.append("and ");
                    }
                    else
                    {
                        stringBuffer.append(CommonSeps.getInstance().COMMA_SEP);
                    }
                }
            }
        }
        
        return stringBuffer.toString();
    }
}
