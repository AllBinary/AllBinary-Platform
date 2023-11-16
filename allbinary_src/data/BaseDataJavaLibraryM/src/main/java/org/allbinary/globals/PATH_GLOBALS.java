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

public class PATH_GLOBALS
{
   private static final PATH_GLOBALS instance = new PATH_GLOBALS();

   public static PATH_GLOBALS getInstance()
   {
       return instance;
   }

   private PATH_GLOBALS()
   {
       StringBuffer stringBuffer = new StringBuffer();

       stringBuffer.append("data");
       stringBuffer.append(AbPathData.getInstance().SEPARATOR);

       this.DATA_PATH = stringBuffer.toString();

       stringBuffer.delete(0, stringBuffer.length());

       stringBuffer.append(DATA_PATH);
       stringBuffer.append("init");
       stringBuffer.append(AbPathData.getInstance().SEPARATOR);

       this.INIT_PATH = stringBuffer.toString();

       stringBuffer.delete(0, stringBuffer.length());

       stringBuffer.append(DATA_PATH);
       stringBuffer.append("log");
       stringBuffer.append(AbPathData.getInstance().SEPARATOR);

       this.LOG_PATH = stringBuffer.toString();

       stringBuffer.delete(0, stringBuffer.length());

       stringBuffer.append(DATA_PATH);
       stringBuffer.append("backup");
       stringBuffer.append(AbPathData.getInstance().SEPARATOR);

       this.BACKUP_PATH = stringBuffer.toString();

       stringBuffer.delete(0, stringBuffer.length());

       stringBuffer.append(INIT_PATH);
       stringBuffer.append("views");
       stringBuffer.append(AbPathData.getInstance().SEPARATOR);

       this.VIEWS_PATH = stringBuffer.toString();
   }

   public final String DATA_PATH;
   public final String INIT_PATH;
   public final String VIEWS_PATH;
   public final String LOG_PATH;
   public final String BACKUP_PATH;
   
}
