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
package org.allbinary.logic.java.byteutil;

public class ByteUtil
{
   private static final ByteUtil instance = new ByteUtil();
   
   public static final ByteUtil getInstance() {
       return instance;
   }

   private ByteUtil()
   {
   }

   public byte[] xor(final byte[] array, final byte mask)
   {
      final byte[] bytes = new byte[array.length];
      
      final int size = array.length;
      int index = 0;
      byte currentByte;
      while(index < size)
      {
         currentByte = array[index];
         //System.out.print("Dec: " + currentByte);
         bytes[index] = (byte) (currentByte ^ mask);         
         //System.out.println(" Crp: " + bytes[index]);
         index++;
      }
      return bytes;
   }
   
   public byte[] not(final byte[] array)
   {
      final byte bytes[] = new byte[array.length];
      
      final int size = array.length;
      int index = 0;
      while(index < size)
      {
         bytes[index] = (byte) ~array[index];
         index++;
      }
      return bytes;
      //return this.xor(array, Byte.MAX_VALUE);
   }
   
   public byte[] xorByte(final byte[] array, final byte index)
   {
      byte mask = 0;
      if(index==0) mask = 1;
      if(index==1) mask = 2;
      if(index==2) mask = 4;
      if(index==3) mask = 8;
      if(index==4) mask = 16;
      if(index==5) mask = 32;
      if(index==6) mask = 64;
      if(index==7) mask = -127;
      
      return this.xor(array,mask);
   }
   
    public boolean compare(final byte[] newByteArray, final byte[] oldByteArray) {

        if(newByteArray.length != oldByteArray.length) {
            return false;
        } else {
        }

        final int size = newByteArray.length;
        for(int index = 0; index < size; index++) {
            if(newByteArray[index] != oldByteArray[index]) {
                return false;
            }
        }
        
        return true;
    }

    public boolean compare(final byte[] newByteArray, final int newSize, final byte[] oldByteArray, final int[] stats) {

        if(newSize != oldByteArray.length) {
            stats[0] = -1;
            stats[2] = newSize;
            stats[3] = oldByteArray.length;
            return false;
        } else {
            stats[0] = newSize;
        }

        stats[1] = 0;

        final int size = newSize;
        for(int index = 0; index < size; index++) {
            if(newByteArray[index] != oldByteArray[index]) {
                return false;
            }
            stats[1]++;
        }
        
        return true;
    }

}
