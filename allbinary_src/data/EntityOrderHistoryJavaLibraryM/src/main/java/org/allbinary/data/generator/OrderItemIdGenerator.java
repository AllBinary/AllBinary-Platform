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
package org.allbinary.data.generator;

import org.allbinary.data.generator.UniqueIdGenerator;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.globals.PATH_GLOBALS;

public class OrderItemIdGenerator extends UniqueIdGenerator
{
    private final String IDFILE = "orderitemId.unq";

    public OrderItemIdGenerator() throws Exception
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(URLGLOBALS.getMainPath());
        stringBuffer.append(PATH_GLOBALS.getInstance().INIT_PATH);
        stringBuffer.append(IDFILE);
        stringBuffer.append(UniqueIdGenerator.EXT);

        super.setFile(stringBuffer.toString(), IDFILE);
   }
}
