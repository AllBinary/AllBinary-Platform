/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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
package org.allbinary.logic.system.security.crypt.jcehelper;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.java.byteutil.ByteUtil;

/**
 *
 * @author User
 */
public class SecretComposite extends BaseSecretComposite {
    
    private final ByteUtil byteUtil = ByteUtil.getInstance();
    
   private final Cipher cipher;
   private final SecretKey secretKey;
   private byte[] key;
   
   public SecretComposite(final SecretKey secretKey, final Cipher cipher, final byte[] key) {
       this.secretKey = secretKey;
       this.cipher = cipher;
       this.key = key;
   }
    
   @Override
   public byte[] encrypt(byte[] array) throws Exception {
       array = this.mutilate(array);
       cipher.init(Cipher.ENCRYPT_MODE, secretKey);
       return cipher.doFinal(array);
   }

   @Override
   public byte[] decrypt(byte[] array) throws Exception {
       cipher.init(Cipher.DECRYPT_MODE, secretKey);         
       return this.mutilate(cipher.doFinal(array));
   }
   
   private byte[] mutilate(byte[] array)
   {
      for(int index = 0; index < key.length; index++)
      {
         byte value = key[index];
         if(value < 8 && value > 0)
         {
            array = byteUtil.xorByte(array, (int) value);
         }
      }
      return array;
   }
   
}
