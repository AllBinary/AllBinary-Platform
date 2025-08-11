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
package org.allbinary.logic.system.security.crypt;

import org.allbinary.logic.NullUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.tokens.Tokenizer;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class DatabaseEncoder
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   
   private DatabaseEncoder()
   {
   }
   
   public static String encode(byte[] value)
   {
      try
      {
          
         byte[] array = value;
         final StringMaker stringBuffer = new StringMaker();
         
         for(int index =0; index < array.length; index++)
         {            
            stringBuffer.append(new Byte(array[index]).toString());
            if(index < array.length-1)
            {
                stringBuffer.append(CommonSeps.getInstance().SPACE);
            }
         }
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CRYPTERROR))
         //{
            final CommonStrings commonStrings = CommonStrings.getInstance();
            PreLogUtil.put(commonStrings.EXCEPTION, "DatabaseEncoder", "decode", e);
         //}
         return StringUtil.getInstance().EMPTY_STRING;
      }
   }
   
   public static byte[] decode(String value)
   {
      try
      {
         final Tokenizer tokenizer = new Tokenizer(CommonSeps.getInstance().SPACE);
         final BasicArrayList vector = tokenizer.getTokens(value, new BasicArrayList());
         final BasicArrayList byteVector = new BasicArrayList();
         final int size = vector.size();

         String byteOfData;
         for(int index = 0; index< size; index++)
         {
            byteOfData = (String) vector.objectArray[index];
            byteVector.add(new Byte(byteOfData));
         }

         final byte[] decode = new byte[byteVector.size()];
         int decodeIndex = 0;

         Byte aByte;
         for(int index = 0; index < size; index++)
         {
            aByte = (Byte) byteVector.objectArray[index];            
            decode[decodeIndex] = aByte.byteValue();
            decodeIndex++;
         }
         return decode;
      }
      catch(Exception e)
      {
         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CRYPTERROR))
         //{
            final CommonStrings commonStrings = CommonStrings.getInstance();
            PreLogUtil.put(commonStrings.EXCEPTION, "DatabaseEncoder", "decode", e);
         //}
         return NullUtil.getInstance().NULL_BYTE_ARRAY;
      }
   }
}
