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

import org.allbinary.init.crypt.jcehelper.CryptInterface;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.java.byteutil.ByteUtil;
import org.allbinary.string.CommonStrings;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AbCrypt implements CryptInterface
{
    //protected final LogUtil logUtil = LogUtil.getInstance();
    
   private BaseSecretComposite secretComposite = BaseSecretComposite.NULL_SECRET_COMPOSITE;
   private String algorithm;
   
   public AbCrypt(final String algorithm)
   {
         this.algorithm = algorithm;
   }
   
   public void init(final String keyAsString)
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
          
         final byte[] key = keyAsString.getBytes();
         final KeySpec keySpec = KeySpecFactory.getInstance().getInstance(this.algorithm, key);
         final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
         
         final SecretKey secretKey = keyFactory.generateSecret(keySpec);
         final Cipher cipher = Cipher.getInstance(algorithm);         
         this.secretComposite = new SecretComposite(secretKey, cipher, key);
      }
      catch(Exception e)
      {
         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CRYPTERROR))
         //{
          PreLogUtil.put("init Failed",this, commonStrings.INIT, e);
         //}
      }
   }

   @Override   
   public byte[] encrypt(final byte [] array)
   {
      try
      {
         return this.secretComposite.encrypt(array);
      }
      catch(Exception e)
      {
         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CRYPTERROR))
         //{
            PreLogUtil.put("Encrypt Failed",this,"encrypt",e);
         //}
         return NullUtil.getInstance().NULL_BYTE_ARRAY;
      }
   }
   
   @Override
   public byte[] decrypt(final byte [] array)
   {
      try
      {
          return this.secretComposite.decrypt(array);
      }
      catch(Exception e)
      {
         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CRYPTERROR))
         //{
            PreLogUtil.put("decrypt Failed",this,"decrypt",e);
         //}
         return NullUtil.getInstance().NULL_BYTE_ARRAY;
      }
   }
   
}
