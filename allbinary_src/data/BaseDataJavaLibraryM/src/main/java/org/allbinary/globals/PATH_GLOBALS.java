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
import org.allbinary.string.CommonStrings;

public class PATH_GLOBALS
{
   private static final PATH_GLOBALS instance = new PATH_GLOBALS();

   public static PATH_GLOBALS getInstance()
   {
       return instance;
   }

   private PATH_GLOBALS()
   {
       final StringBuffer stringBuffer = new StringBuffer();
       final AbPathData abPathData = AbPathData.getInstance();

       stringBuffer.append("data");
       stringBuffer.append(abPathData.SEPARATOR);

       this.DATA_PATH = stringBuffer.toString();

       stringBuffer.delete(0, stringBuffer.length());

       stringBuffer.append(DATA_PATH);
       stringBuffer.append(CommonStrings.getInstance().INIT);
       stringBuffer.append(abPathData.SEPARATOR);

       this.INIT_PATH = stringBuffer.toString();

       stringBuffer.delete(0, stringBuffer.length());

       stringBuffer.append(DATA_PATH);
       stringBuffer.append("log");
       stringBuffer.append(abPathData.SEPARATOR);

       this.LOG_PATH = stringBuffer.toString();

       stringBuffer.delete(0, stringBuffer.length());

       stringBuffer.append(DATA_PATH);
       stringBuffer.append("backup");
       stringBuffer.append(abPathData.SEPARATOR);

       this.BACKUP_PATH = stringBuffer.toString();

       stringBuffer.delete(0, stringBuffer.length());

       stringBuffer.append(INIT_PATH);
       stringBuffer.append("views");
       stringBuffer.append(abPathData.SEPARATOR);

       this.VIEWS_PATH = stringBuffer.toString();
   }

   public final String DATA_PATH;
   public final String INIT_PATH;
   public final String VIEWS_PATH;
   public final String LOG_PATH;
   public final String BACKUP_PATH;
   
}
