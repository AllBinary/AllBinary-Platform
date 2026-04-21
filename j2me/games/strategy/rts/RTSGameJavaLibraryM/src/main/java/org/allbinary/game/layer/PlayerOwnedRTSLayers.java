/*
 * AllBinary Open License Version 1
 * Copyright (c) 2003 AllBinary
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

package org.allbinary.game.layer;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

public class PlayerOwnedRTSLayers
{
    public final BasicArrayList rtsLayerList = new BasicArrayListD();

    public String toString()
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("All: ");
        stringBuffer.appendint(this.rtsLayerList.size());

        return stringBuffer.toString();
    }

}
