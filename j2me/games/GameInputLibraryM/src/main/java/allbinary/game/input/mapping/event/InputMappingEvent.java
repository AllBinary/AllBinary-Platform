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
package allbinary.game.input.mapping.event;

import allbinary.game.input.mapping.InputToGameKeyMapping;
import allbinary.logic.basic.util.event.AllBinaryEventObject;

public class InputMappingEvent extends AllBinaryEventObject
{
    private InputToGameKeyMapping inputToGameKeyMapping;

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
