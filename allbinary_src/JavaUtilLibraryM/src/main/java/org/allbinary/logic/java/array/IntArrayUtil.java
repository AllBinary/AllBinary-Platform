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
package org.allbinary.logic.java.array;

import org.allbinary.logic.string.StringMaker;

public class IntArrayUtil
{

   private IntArrayUtil()
   {
   }

   public static String toString(int[] array)
   {
      StringMaker stringBuffer = new StringMaker();
      for (int index = 0; index < array.length; index++)
      {
         stringBuffer.append(array[index]);

         if (index < array.length)
         {
            stringBuffer.append(", ");
         }
      }

      return stringBuffer.toString();
   }

   public static String toString(int[][] array)
   {
      StringMaker stringBuffer = new StringMaker();
      for (int index = 0; index < array.length; index++)
      {
         stringBuffer.append("{");
         for (int index2 = 0; index2 < array[index].length; index2++)
         {
            stringBuffer.append(array[index][index2]);

            if (index2 < array[index].length - 1)
            {
               stringBuffer.append(", ");
            }
         }
         stringBuffer.append("}");

         if (index < array.length - 1)
         {
            stringBuffer.append(", \n");
         }
      }
      return stringBuffer.toString();
   }

   public static String toString(int[][][] array)
   {
      StringMaker stringBuffer = new StringMaker();
      for (int index = 0; index < array.length; index++)
      {
         stringBuffer.append("{");
         for (int index2 = 0; index2 < array[index].length; index2++)
         {
            stringBuffer.append("{");

            for (int index3 = 0; index3 < array[index][index2].length; index3++)
            {
               stringBuffer.append(array[index][index2][index3]);

               if (index3 < array[index][index2].length - 1)
               {
                  stringBuffer.append(", ");
               }
            }
            stringBuffer.append("}");

            if (index2 < array[index].length - 1)
            {
               stringBuffer.append(", ");
            }
         }
         stringBuffer.append("}");

         if (index < array.length - 1)
         {
            stringBuffer.append(", \n");
         }
      }
      return stringBuffer.toString();
   }
}