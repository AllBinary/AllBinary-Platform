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
package abcs.logic.basic.io;

import abcs.logic.basic.io.file.FileUtil;
import abcs.logic.communication.log.LogFactory;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

import abcs.logic.communication.log.LogUtil;

public class SmallInsert
{  
   private String fileName;
   private String string;
   
   public SmallInsert(String fileName)
   {
       this.fileName = fileName;
       string = FileUtil.getInstance().readAsString(fileName);
   }
   
   public boolean atBeginning(String text)
   {
      try
      {  
         FileOutputStream idFile = new FileOutputStream(this.fileName);
         DataOutputStream idOutData = new DataOutputStream(idFile);
         idOutData.writeBytes(text + this.string);
         //System.out.println();
         return true;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.IDLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed", this, "insertAtBeginning", e));
         }
         return false;
      }
   }

   public boolean after(String text, String after)
   {
      try
      {  
         int index = text.indexOf(after);
         
         if(index < 0)
         {
             throw new Exception("No such start: " + after);
         }
         
         String start = text.substring(0, index);
         
         String end = text.substring(index + 1, text.length());
         
         FileOutputStream idFile = new FileOutputStream(this.fileName);
         DataOutputStream idOutData = new DataOutputStream(idFile);
         idOutData.writeBytes(start + text + end);
         //System.out.println();
         return true;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.IDLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed", this, "after", e));
         }
         return false;
      }
   }
   
   public boolean atEnd(String text)
   {
      try
      {         
         FileOutputStream idFile = new FileOutputStream(this.fileName);
         DataOutputStream idOutData = new DataOutputStream(idFile);         
         idOutData.writeBytes(this.string + text);         
         return true;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.IDLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed", this, "insertAtEnd", e));
         }
         return false;
      }
   }

}
