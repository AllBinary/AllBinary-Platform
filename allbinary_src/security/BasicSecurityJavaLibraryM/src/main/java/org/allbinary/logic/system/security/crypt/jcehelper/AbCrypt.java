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
package org.allbinary.logic.system.security.crypt.jcehelper;

import java.security.Security;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import org.allbinary.init.crypt.jcehelper.CryptInterface;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.java.byteutil.ByteUtil;

public class AbCrypt implements CryptInterface
{
   private final ByteUtil byteUtil = ByteUtil.getInstance();
    
   private Cipher cipher;
   private SecretKey secretKey;
   private String algorithm;
   private byte[] key;
   
   public AbCrypt(String algorithm, String key)
   {
      try
      {
         this.algorithm = algorithm;
         this.key = key.getBytes();
         this.init();
      }
      catch(Exception e)
      {
         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CRYPTERROR))
         //{
            PreLogUtil.put("constructor Failed",this,"AbCrypt(alg,key)",e);
         //}
      }
   }
   
   private void init()
   {
       final CommonStrings commonStrings = CommonStrings.getInstance();
      try
      {
         //Provider sunJce = new com.sun.crypto.provider.SunJCE();
         //Security.addProvider(new com.sun.crypto.provider.SunJCE( ));
         //Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider( ));
         //Security.addProvider(sunJce);
         
          try
          {
              Security.addProvider(new BouncyCastleProvider());
          }
          catch(Exception e)
          {
              PreLogUtil.put(commonStrings.EXCEPTION, this, commonStrings.INIT, e);
          }
          
         KeySpec keySpec = KeySpecFactory.getInstance().getInstance(this.algorithm, this.key);
         SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
         this.secretKey = keyFactory.generateSecret(keySpec);
         this.cipher = Cipher.getInstance(algorithm);
      }
      catch(Exception e)
      {
         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CRYPTERROR))
         //{
          PreLogUtil.put("init Failed",this, commonStrings.INIT, e);
         //}
      }
   }
   
   public byte[] encrypt(byte [] array)
   {
      try
      {         
         cipher.init(Cipher.ENCRYPT_MODE, secretKey);
         array = this.mutilate(array);
         return cipher.doFinal(array);
      }
      catch(Exception e)
      {
         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CRYPTERROR))
         //{
            PreLogUtil.put("Encrypt Failed",this,"encrypt",e);
         //}
         return null;
      }
   }
   
   public byte[] decrypt(byte [] array)
   {
      try
      {
         cipher.init(Cipher.DECRYPT_MODE, secretKey);         
         return this.mutilate(cipher.doFinal(array));
      }
      catch(Exception e)
      {
         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CRYPTERROR))
         //{
            PreLogUtil.put("decrypt Failed",this,"decrypt",e);
         //}
         return null;
      }
   }
   
   public byte[] mutilate(byte [] array)
   {
      for(int index=0; index<key.length; index++)
      {
         byte val = key[index];
         if(val < 8 && val >0)
         {
            array = byteUtil.xorByte(array, val);
         }
      }
      return array;
   }
}
