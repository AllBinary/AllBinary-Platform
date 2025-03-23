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

import java.security.spec.KeySpec;

import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.PreLogUtil;

public class KeySpecFactory
{
    private static final KeySpecFactory instance = new KeySpecFactory();
    
    public static KeySpecFactory getInstance()
    {
        return instance;
    }
    
   //tested
   public final String DES = "DES";   
   //requires 24byte key mininum
   public final String DESEDE = "DESede";
   
   //untested
   public final String BLOWFISH = "Blowfish";
   
   //public final String PBE = "PBEwithMD5AndDES";
   //public final String PBETRIPLE = "PBEwithMD5AndTripleDES";
   //public final String DH = "Diffie-Hellman";
   
   private KeySpecFactory()
   {
   }
   
   public KeySpec getInstance(String algorithm, byte[] keyData)
   {
      try
      {
         if(algorithm.compareTo(this.DES)==0)
         {
            return new DESKeySpec(keyData);
         }
         else
         if(algorithm.compareTo(this.DESEDE)==0)
         {
            return new DESedeKeySpec(keyData);
         }
         /*
         else
         if(algorithm.compareTo(KeySpecFactory.BLOWFISH)==0)
         {
            return (KeySpec) new BlowFishKeySpec(keyData);
         }           
         else
         if(algorithm.compareTo(KeySpecFactory.PBE)==0)
         {
            return (KeySpec) new PBEKeySpec(new String(keyData).toCharArray());
         }
         else
         if(algorithm.compareTo(KeySpecFactory.PBETRIPLE)==0)
         {
            return (KeySpec) new PBEKeySpec(new String(keyData).toCharArray());
         }
         else
         if(algorithm.compareTo(KeySpecFactory.DH)==0)
         {
            return (KeySpec) new DHPrivateKeySpec(new BigInteger(keyData),new BigInteger(keyData),new BigInteger(keyData));
         }*/
         else return null;
      }
      catch(Exception e)
      {
         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CRYPTERROR))
         //{
            PreLogUtil.put(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().GET_INSTANCE, e);
         //}
         return null;
      }
   }
}
