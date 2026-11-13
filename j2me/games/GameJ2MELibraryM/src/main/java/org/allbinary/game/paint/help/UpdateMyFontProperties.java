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
package org.allbinary.game.paint.help;

import org.allbinary.logic.NullUtil;
import org.allbinary.logic.string.StringUtil;

class UpdateMyFontProperties {
    String[] actionStringArray = StringUtil.getInstance().getArrayInstance();
    int[] keymappingBeginWidthArray = NullUtil.getInstance().NULL_INT_ARRAY;
    int[] actionStringDeltaXArray = NullUtil.getInstance().NULL_INT_ARRAY;
    int[][] inputDeltaXArray = NullUtil.getInstance().NULL_INT_ARRAY_ARRAY;
    String[][] sepArray = StringUtil.getInstance().ONE_EMPTY_STRING_ARRAY_OF_ARRAYS;
    int[][] sepWidthArray = NullUtil.getInstance().NULL_INT_ARRAY_ARRAY;
    int charHeight;
}
