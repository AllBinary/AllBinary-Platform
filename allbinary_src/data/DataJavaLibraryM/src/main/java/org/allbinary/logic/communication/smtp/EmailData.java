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
package org.allbinary.logic.communication.smtp;

public class EmailData
{
   private EmailData()
   {
   
   }

   public static final String SERVER = "EMAIL_SERVER";
   public static final String NAME = "EMAIL_NAME";
   
   public static final String FROM = "EMAIL_FROM";
   public static final String TO = "EMAIL_TO";
   public static final String CC = "EMAIL_CC";
   public static final String BCC = "EMAIL_BCC";
   public static final String SUBJECT = "EMAIL_SUBJECT";
   public static final String CONTENT = "EMAIL_CONTENT";
}
