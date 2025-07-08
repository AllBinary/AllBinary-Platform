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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.init.Init;

/**
 *
 * @author user
 */
public class KeyFactoryInitializer extends Init
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    public void init()
    {
        logUtil.put(this.commonStrings.START, this, this.commonStrings.INIT);
    }
}
