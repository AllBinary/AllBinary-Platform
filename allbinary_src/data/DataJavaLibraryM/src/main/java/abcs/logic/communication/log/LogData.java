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
package abcs.logic.communication.log;

import abcs.logic.basic.path.AbPathData;

public class LogData
{
   public final long MAX = 5000000;  // 5MB
   public final String extension = "log";
   public final String backupFileExt = 
       AbPathData.getInstance().EXTENSION_SEP + "bak" +
       AbPathData.getInstance().EXTENSION_SEP + "txt";

   public final String ALLBINARY = "allbinary";
         
   public final String NAME = "LOG_NAME";
   public final String DESCRIPTION = "LOG_DESCRIPTION";
}
