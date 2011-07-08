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
package abcs.logic.java.byteutil;

public class ByteUtil
{
   public ByteUtil()
   {
   }

   public byte[] xor(byte array[], byte mask)
   {
      byte bytes[] = new byte[array.length];
      
      int index = 0;
      int size = array.length;
      while(index < size)
      {
         byte currentByte = array[index];
         //System.out.print("Dec: " + currentByte);
         bytes[index] = (byte) (currentByte ^ mask);         
         //System.out.println(" Crp: " + bytes[index]);
         index++;
      }
      return bytes;
   }
   
   public byte[] not(byte array[])
   {
      byte bytes[] = new byte[array.length];
      
      int index = 0;
      int size = array.length;
      while(index < size)
      {
         bytes[index] = (byte) ~array[index];
         index++;
      }
      return bytes;
      //return this.xor(array, Byte.MAX_VALUE);
   }
   
   public byte[] xorByte(byte array[], byte index)
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
}
