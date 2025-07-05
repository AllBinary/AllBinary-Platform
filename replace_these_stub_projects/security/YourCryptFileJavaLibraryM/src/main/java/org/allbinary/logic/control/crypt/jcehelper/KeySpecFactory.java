/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
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
package org.allbinary.logic.control.crypt.jcehelper;

import java.security.spec.KeySpec;

public class KeySpecFactory
{
   public static final String DES = "DES";
   
   //requires 24byte key mininum
   public static final String DESEDE = "DESede";
   
   private KeySpecFactory()
   {
   }
   
   public static KeySpec getInstance(String algorithm, byte[] keyData)
   {
       return null;
   }
}
