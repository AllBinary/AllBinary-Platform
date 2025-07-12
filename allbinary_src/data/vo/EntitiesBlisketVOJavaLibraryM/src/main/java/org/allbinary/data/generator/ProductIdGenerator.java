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

import org.allbinary.globals.PATH_GLOBALS;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.string.StringMaker;

public class ProductIdGenerator extends UniqueIdGenerator
{
    private final String IDFILE = "productId";

    public ProductIdGenerator() throws Exception
    {
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(URLGLOBALS.getMainPath());
        stringBuffer.append(PATH_GLOBALS.getInstance().INIT_PATH);
        stringBuffer.append(IDFILE);
        stringBuffer.append(UniqueIdGenerator.EXT);

        super.setFile(stringBuffer.toString(), IDFILE);
    }
}
