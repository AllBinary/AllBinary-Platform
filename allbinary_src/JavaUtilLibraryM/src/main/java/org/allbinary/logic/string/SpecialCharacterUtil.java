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
package org.allbinary.logic.string;

import java.util.HashMap;
import org.allbinary.logic.StdUtil;

import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.string.CommonPhoneStrings;
import org.allbinary.string.CommonSeps;

/**
 *
 * @author user
 */
public class SpecialCharacterUtil {

   public static HashMap getHashMap()
   {
       final CommonSeps commonSeps = CommonSeps.getInstance();
      final CommonPhoneStrings commonPhoneStrings = CommonPhoneStrings.getInstance();
      final StringUtil stringUtil = StringUtil.getInstance();
      final HashMap hashMap = StdUtil.getInstance().createHashMap();
      hashMap.put("!",stringUtil.EMPTY_STRING);
      hashMap.put("@",stringUtil.EMPTY_STRING);
      hashMap.put(commonPhoneStrings.POUND,stringUtil.EMPTY_STRING);
      hashMap.put("$",stringUtil.EMPTY_STRING);
      hashMap.put("%",stringUtil.EMPTY_STRING);
      hashMap.put("^",stringUtil.EMPTY_STRING);
      hashMap.put(commonSeps.AMPERSAND,stringUtil.EMPTY_STRING);
      hashMap.put(commonPhoneStrings.STAR,stringUtil.EMPTY_STRING);
      hashMap.put(commonSeps.PARENTHESIS_OPEN,stringUtil.EMPTY_STRING);
      hashMap.put(commonSeps.PARENTHESIS_CLOSE,stringUtil.EMPTY_STRING);
      hashMap.put(commonSeps.DASH,stringUtil.EMPTY_STRING);
      hashMap.put(commonSeps.UNDERSCORE,stringUtil.EMPTY_STRING);
      hashMap.put("+",stringUtil.EMPTY_STRING);
      hashMap.put(commonSeps.EQUALS,stringUtil.EMPTY_STRING);
      hashMap.put("\\",stringUtil.EMPTY_STRING);
      hashMap.put("|",stringUtil.EMPTY_STRING);
      hashMap.put(AbPathData.getInstance().EXTENSION_SEP,stringUtil.EMPTY_STRING);
      hashMap.put(",",stringUtil.EMPTY_STRING);
      hashMap.put("<",stringUtil.EMPTY_STRING);
      hashMap.put(">",stringUtil.EMPTY_STRING);
      hashMap.put("?",stringUtil.EMPTY_STRING);
      hashMap.put(AbPathData.getInstance().SEPARATOR,stringUtil.EMPTY_STRING);
      hashMap.put("~",stringUtil.EMPTY_STRING);
      hashMap.put("`",stringUtil.EMPTY_STRING);
      return hashMap;
   }
   
}
