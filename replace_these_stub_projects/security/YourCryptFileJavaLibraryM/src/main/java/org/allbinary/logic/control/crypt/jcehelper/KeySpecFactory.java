/*
 *Copyright (c) 2002 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *Created By: Travis Berthelot
 *Date: 11/29/02
 *
 *
 *Modified By         When       ?
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
