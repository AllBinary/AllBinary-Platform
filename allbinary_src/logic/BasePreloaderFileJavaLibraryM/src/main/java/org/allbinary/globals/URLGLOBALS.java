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
package org.allbinary.globals;

public class URLGLOBALS
{
   private static UrlGlobalsInterface urlGlobalsInterface = AppUrlGlobals.NULL_APP_URL_GLOBALS;
   
   public static void init(UrlGlobalsInterface urlGlobalsInterface)
   {
       URLGLOBALS.urlGlobalsInterface = urlGlobalsInterface;
   }
   
   public static boolean isTestingMode()
   {
      return urlGlobalsInterface.isTestingMode();
   }
   
   public static String getWebappPath()
   {
      return urlGlobalsInterface.getWebappPath();
   }
   
   public static String getTestHtmlPath()
   {
      return urlGlobalsInterface.getTestHtmlPath();
   }
      
   public static String getMainPath() throws Exception
   {
      return urlGlobalsInterface.getMainPath();
   }   
}
