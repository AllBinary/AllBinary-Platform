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
package org.allbinary.graphics.j2me;

import org.allbinary.logic.communication.log.GuiLog;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class GraphicsException extends java.lang.Exception
{   
   public GraphicsException(String msg, Object obj, String method)
   {
      super(msg);
      try
      {
         GuiLog.showDialog(msg);
         LogUtil.put(LogFactory.getInstance(msg, obj, method, this));
      }
      catch(Exception e)
      {
      }
   }
   
   public GraphicsException(String msg, String className, String method)
   {
      super(msg);
      try
      {
         GuiLog.showDialog(msg);
         LogUtil.put(LogFactory.getInstance(msg, className, method, this));
      }
      catch(Exception e)
      {
      }
   }
}
