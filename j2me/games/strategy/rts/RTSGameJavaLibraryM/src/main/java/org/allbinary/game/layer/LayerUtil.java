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

import java.util.Hashtable;

import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.math.PositionStrings;

public class LayerUtil
{
    private static final String HASHTABLE = "Hashtable: ";
    
    public static String toString(Hashtable hashtable, int x, int y, int z)
    {
        final CommonSeps commonSeps = CommonSeps.getInstance();
        final PositionStrings positionStrings = PositionStrings.getInstance();
        final StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append(HASHTABLE);
        stringBuffer.append(StringUtil.getInstance().toString(hashtable));
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(positionStrings.X_LABEL);
        stringBuffer.append(x);
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(positionStrings.Y_LABEL);
        stringBuffer.append(y);
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(positionStrings.Z_LABEL);
        stringBuffer.append(z);
        
        return stringBuffer.toString();
    }
}
