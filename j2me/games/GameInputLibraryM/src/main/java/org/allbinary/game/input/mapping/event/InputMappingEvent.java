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
package org.allbinary.game.input.mapping.event;

import org.allbinary.game.input.mapping.InputToGameKeyMapping;
import org.allbinary.logic.util.event.AllBinaryEventObject;

public class InputMappingEvent extends AllBinaryEventObject
{
    private InputToGameKeyMapping inputToGameKeyMapping = InputToGameKeyMapping.NULL_INPUT_TO_GAME_KEY_MAPPING;

    public InputMappingEvent(Object object)
    {
        super(object);
    }

    public void setInputToGameKeyMapping(
            InputToGameKeyMapping inputToGameKeyMapping)
    {
        this.inputToGameKeyMapping = inputToGameKeyMapping;
    }

    public InputToGameKeyMapping getInputToGameKeyMapping()
    {
        return inputToGameKeyMapping;
    }
}
