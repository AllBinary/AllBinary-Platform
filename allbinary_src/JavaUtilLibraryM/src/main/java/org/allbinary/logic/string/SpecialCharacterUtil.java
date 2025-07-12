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

import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.string.CommonSeps;

/**
 *
 * @author user
 */
public class SpecialCharacterUtil {

   public static HashMap getHashMap()
   {
      final StringUtil stringUtil = StringUtil.getInstance();
      final HashMap hashMap = new HashMap();
      hashMap.put("!",stringUtil.EMPTY_STRING);
      hashMap.put("@",stringUtil.EMPTY_STRING);
      hashMap.put("#",stringUtil.EMPTY_STRING);
      hashMap.put("$",stringUtil.EMPTY_STRING);
      hashMap.put("%",stringUtil.EMPTY_STRING);
      hashMap.put("^",stringUtil.EMPTY_STRING);
      hashMap.put(CommonSeps.getInstance().AMP,stringUtil.EMPTY_STRING);
      hashMap.put("*",stringUtil.EMPTY_STRING);
      hashMap.put("(",stringUtil.EMPTY_STRING);
      hashMap.put(")",stringUtil.EMPTY_STRING);
      hashMap.put("-",stringUtil.EMPTY_STRING);
      hashMap.put("_",stringUtil.EMPTY_STRING);
      hashMap.put("+",stringUtil.EMPTY_STRING);
      hashMap.put("=",stringUtil.EMPTY_STRING);
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
