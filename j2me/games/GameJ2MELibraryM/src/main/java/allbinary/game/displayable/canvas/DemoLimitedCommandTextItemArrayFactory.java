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
package allbinary.game.displayable.canvas;

import allbinary.graphics.form.item.CommandTextItemArrayFactory;
import allbinary.graphics.form.item.LimitCommandsVisitor;

public class DemoLimitedCommandTextItemArrayFactory
{
    private static final DemoLimitedCommandTextItemArrayFactory instance = 
        new DemoLimitedCommandTextItemArrayFactory();
    
    private final CommandTextItemArrayFactory commandTextItemArrayFactory =
        new CommandTextItemArrayFactory(new LimitCommandsVisitor());

    public static DemoLimitedCommandTextItemArrayFactory getInstance()
    {
        return instance;
    }

    public CommandTextItemArrayFactory getCommandTextItemArrayFactory()
    {
        return commandTextItemArrayFactory;
    }
}
