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

import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.string.StringMaker;

public class FREEBLISKET_PATH_GLOBALS
{
   private static final FREEBLISKET_PATH_GLOBALS instance = new FREEBLISKET_PATH_GLOBALS();

   public static FREEBLISKET_PATH_GLOBALS getInstance()
   {
       return instance;
   }

   //public static final String SERIALPATH = "serializeddata" + PathData.SEPARATOR;

   public final String INSTALLPATH;

   public final String XSLPATH;

   public final String TEMPLATEPATH = "template" + AbPathData.getInstance().SEPARATOR;
   public final String STYLEPATH = TEMPLATEPATH + "style" + AbPathData.getInstance().SEPARATOR;
   public final String THEMEPATH = STYLEPATH + "theme"  + AbPathData.getInstance().SEPARATOR;

   public final String DBINITPATH = PATH_GLOBALS.getInstance().INIT_PATH + "db" + AbPathData.getInstance().SEPARATOR;
   public final String LINEDATAPATH = PATH_GLOBALS.getInstance().INIT_PATH + "lineData" + AbPathData.getInstance().SEPARATOR;

   public final String CONFIGURATIONPATH;
   public final String USERCONFIGURATIONPATH;
   public final String CONTEXTCONFIGURATIONPATH;

   public final String LICENSEERRORPAGE = "LicensingError.jsp";
   public final String ERRORPAGE = "Error.jsp";

   private FREEBLISKET_PATH_GLOBALS()
   {
       StringMaker stringBuffer = new StringMaker();

       stringBuffer.append("install");
       stringBuffer.append(AbPathData.getInstance().SEPARATOR);

       this.INSTALLPATH = stringBuffer.toString();

       stringBuffer.delete(0, stringBuffer.length());

       stringBuffer.append(PATH_GLOBALS.getInstance().INIT_PATH);
       stringBuffer.append("views");
       stringBuffer.append(AbPathData.getInstance().SEPARATOR);

       this.XSLPATH = stringBuffer.toString();

       stringBuffer.delete(0, stringBuffer.length());

       stringBuffer.append(XSLPATH);
       stringBuffer.append("configuration");
       stringBuffer.append(AbPathData.getInstance().SEPARATOR);

       this.CONFIGURATIONPATH = stringBuffer.toString();

       stringBuffer.delete(0, stringBuffer.length());

       stringBuffer.append(CONFIGURATIONPATH);
       stringBuffer.append("user");
       stringBuffer.append(AbPathData.getInstance().SEPARATOR);

       this.USERCONFIGURATIONPATH = stringBuffer.toString();

       stringBuffer.delete(0, stringBuffer.length());

       stringBuffer.append(CONFIGURATIONPATH);
       stringBuffer.append("context");
       stringBuffer.append(AbPathData.getInstance().SEPARATOR);

       this.CONTEXTCONFIGURATIONPATH = stringBuffer.toString();

       stringBuffer.delete(0, stringBuffer.length());


   }
}
