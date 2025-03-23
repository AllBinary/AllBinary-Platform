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

import org.allbinary.logic.io.file.FilePathData;
import org.allbinary.logic.io.file.FilePathUtil;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.StringValidationUtil;

public class AbPath
{
   protected String schema;
   private String path;
   //private int port;
   protected String name;
   
   private boolean hasSchema = false;
   private int numberOfSeps = 0;
   
   private final AbPathUtil abPathUtil = AbPathUtil.getInstance();
   
   public AbPath()
   {
      init();
   }
   
   private final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;
   
   public AbPath(String aPath) throws Exception
   {
       final StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
       
      if(!stringValidationUtil.isEmpty(aPath))
      {
         this.schema = this.getSchema(aPath);
         this.name = EMPTY_STRING;
         this.path = abPathUtil.adjustEnd(abPathUtil.adjust(this.getPath(aPath)));
         //this.port = AbPathUtil.getPort(aPath);
      }
      else
      {
         init();
      }
   }
   
   public AbPath(String aPath, String name) throws Exception
   {
       final StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
       
      if(!stringValidationUtil.isEmpty(aPath))
      {
         this.schema = this.getSchema(aPath);
         //this.name = AbPathUtil.getName(aPath);
         this.name = name;
         this.path = abPathUtil.adjustEnd(abPathUtil.adjust(this.getPath(aPath)));
         //this.port = AbPathUtil.getPort(aPath);
      }
      else
      {
         init();
         
         if(!stringValidationUtil.isEmpty(name))
         {   
            this.name = name;
         }
      }
   }
   
   private void init()
   {
      this.schema = EMPTY_STRING;
      this.setPath(AbPathData.getInstance().SEPARATOR);
      //this.port = -1;  //use the schemas default port
      this.name = EMPTY_STRING;
   }
   
   protected String getSchema(String aPath)
   {
      int beginIndex = aPath.indexOf(CommonSeps.getInstance().COLON);
      if(beginIndex >= 0)
      {
         this.hasSchema = true;
         return aPath.substring(0, beginIndex);
      }
      this.hasSchema = false;
      return EMPTY_STRING;
   }
   
   public boolean hasSchema()
   {
      return this.hasSchema;
   }
   
   public String getPath(String aPath) throws Exception
   {
      String tempPath = aPath;
      /*
      if(!this.hasName())
      {
         this.path = AbPathUtil.adjustEnd(this.path);
      }
       */
      
      if(!this.hasSchema())
      {
         tempPath = abPathUtil.adjustStart(tempPath);
      }
      else
      {
         int beginIndex = tempPath.indexOf(CommonSeps.getInstance().COLON);
         if(beginIndex >= 0)
         {
            beginIndex++;
            
            while(tempPath.charAt(beginIndex) == AbPathData.getInstance().SEPARATORCHAR ||
            tempPath.charAt(beginIndex) == FilePathData.SEPARATORCHAR)
            {
               beginIndex++;
               numberOfSeps++;
               if(numberOfSeps > 2)
               {
                  throw new Exception("Should Not Have More Than Two Seps");
               }
            }
            
            tempPath = tempPath.substring(beginIndex, tempPath.length());
            //without name if any;
         }
      }
      return tempPath;
      //without name if any;
   }

   public String getName()
   {
      return this.name;
   }

   private static final String NETWORK_SEP = ":/";
   public String toString()
   {
      StringMaker stringBuffer = new StringMaker();
      
      if(this.hasSchema())
      {
         stringBuffer.append(this.schema);
         //unix/networke schema sep
         stringBuffer.append(NETWORK_SEP);
         //TWB - Schema OS issue?
         //stringBuffer.append(FilePathData.SEPARATOR);

         //windows/dos style schema sep
      }
      
      stringBuffer.append(this.getPath());
      stringBuffer.append(this.name);
      
      /*
      if(this.hasName())
      {
      }
       */
      return stringBuffer.toString();
   }
   
   //Future Support
   //this.getSchema() + "://" + this.getServer() + CommonSeps.getInstance().COLON + this.getPort() + AbPathData.SEPARATOR;
   public String toFileSystemString()
   {
      if(this.hasSchema())
      {
         StringMaker stringBuffer = new StringMaker();
         stringBuffer.append(this.schema);
         stringBuffer.append(CommonSeps.getInstance().COLON);
         stringBuffer.append(FilePathData.SEPARATOR);
         stringBuffer.append(FilePathUtil.adjust(this.getPath()));
         stringBuffer.append(this.name);

         return stringBuffer.toString();
      }
      else
      {
         return FilePathUtil.adjust(this.getPath()) + this.name;
      }
   }

    /**
     * @return the path
     */
    public String getPath()
    {
        return path;
    }

    /**
     * @param path the path to set
     */
    protected void setPath(String path)
    {
        this.path = path;
    }
}
