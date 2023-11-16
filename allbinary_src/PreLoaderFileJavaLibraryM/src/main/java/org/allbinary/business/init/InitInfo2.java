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
package org.allbinary.business.init;

import org.allbinary.logic.io.file.directory.Directory;
import org.allbinary.logic.io.path.AbPath;

public class InitInfo2
{

    private static final InitInfo2 instance = new InitInfo2();

    public static InitInfo2 getInstance()
    {
        return instance;
    }

    public boolean isTestHtmlPathValid(AbPath value)
    {
        return Directory.create(value);
    }

    public boolean isMainPathValid(AbPath value)
    {
        return Directory.create(value);
    }
}
