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
package org.allbinary.game.input;

public class GameInputProcessorUtil
{
    public static void init(GameInputProcessor[] inputProcessorArray)
    {
        GameInputProcessor inputProcessor;
        final GameInputProcessor noInputProcessor = GameInputProcessor.getInstance();
        
        int total = InputFactory.getInstance().MAX;
        for(int index = 0; index < total; index++)
        {
            inputProcessor = inputProcessorArray[index];
            if(inputProcessor == null)
            {
                inputProcessorArray[index] = noInputProcessor;
            }
        }
    }
}
