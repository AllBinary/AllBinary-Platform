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
package org.allbinary.logic.io.path;

public class AbFilePath extends AbPath
{
   public AbFilePath(String aPath) throws Exception
   {
      super();
      
      final PathUtil abPathUtil = PathUtil.getInstance();
      
      this.schema = this.getSchema(aPath);
      this.setName(abPathUtil.getNameFromPath(aPath));
      this.setPath(abPathUtil.adjust(
         this.getPath(abPathUtil.removeNameFromPath(aPath).toString()))
         );
      //this.port = AbPathUtil.getPort(aPath);
   }
}
