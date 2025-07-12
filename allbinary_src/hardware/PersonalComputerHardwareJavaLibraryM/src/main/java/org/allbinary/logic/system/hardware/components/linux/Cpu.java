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
package org.allbinary.logic.system.hardware.components.linux;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.HashMap;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.FilePathData;
import org.allbinary.logic.io.file.directory.SubDirectory;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.hardware.components.interfaces.CpuInterface;
import org.allbinary.logic.system.hardware.components.interfaces.HardwareComponentInterface;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class Cpu implements CpuInterface, HardwareComponentInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private final String CPUFILE = "/proc/cpuinfo";
   private HashMap cpuHashMap;
   
   public Cpu(String filePath) throws Exception
   {
      this.init(filePath);
   }
   
   public Cpu() throws Exception
   {
      this.init(CPUFILE);
   }
   
   private void init(String filePath) throws Exception
   {
      LineNumberReader lineNumberReader = null;
      try
      {
         init(lineNumberReader, filePath);
      }
      catch(Exception e)
      {
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().OS))
         {
            logUtil.put(new StringMaker().append("Cpu Data: ").append(this.toString()).toString(), this, this.commonStrings.CONSTRUCTOR, e);
         }
         throw e;
      }
   }
   
   private void init(LineNumberReader lineNumberReader, String filePath) throws Exception
   {
      try
      {
         FileReader fileReader = new FileReader(filePath);
         lineNumberReader = new LineNumberReader(fileReader);
         
         cpuHashMap = new HashMap();
         
         if(lineNumberReader == null)
         {
            //Find file
            final BasicArrayList fileVector = SubDirectory.getInstance().search(filePath, new AbFile(FilePathData.getInstance().SEPARATOR));
            
            if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().OS))
            {
               logUtil.put(new StringMaker().append("Cpu File Vector Size: ").append(fileVector.size()).toString(), this, this.commonStrings.CONSTRUCTOR);
            }
            
            //size() > 0
            if(!fileVector.isEmpty())
            {
               AbFile file = (AbFile) fileVector.get(0);
               lineNumberReader = new LineNumberReader(new FileReader(file.getPath()));
            }
         }
         
         if(lineNumberReader != null)
         {
            String nextLine = lineNumberReader.readLine();
            
            while(lineNumberReader != null && nextLine != null)
            {
               int index = nextLine.indexOf(':');
               
               if(index>=0)
               {
                  String key = nextLine.substring(0,index);
                  int valueIndex = index+1;
                  String value = nextLine.substring(valueIndex,nextLine.length());
                  if(key.indexOf(CpuInfo.PROCESSOR.toString())>=0)
                     cpuHashMap.put(CpuInfo.PROCESSOR,value);
                  if(key.indexOf(CpuInfo.VENDORID.toString())>=0)
                     cpuHashMap.put(CpuInfo.VENDORID,value);
                  if(key.indexOf(CpuInfo.CPUFAMILY.toString())>=0)
                     cpuHashMap.put(CpuInfo.CPUFAMILY,value);
                  if(key.indexOf(CpuInfo.MODEL.toString())>=0)
                     cpuHashMap.put(CpuInfo.MODEL,value);
                  if(key.indexOf(CpuInfo.MODELNAME.toString())>=0)
                     cpuHashMap.put(CpuInfo.MODELNAME,value);
                  if(key.indexOf(CpuInfo.STEPPING.toString())>=0)
                     cpuHashMap.put(CpuInfo.STEPPING,value);
                  if(key.indexOf(CpuInfo.CPUMHZ.toString())>=0)
                     cpuHashMap.put(CpuInfo.CPUMHZ,value);
                  if(key.indexOf(CpuInfo.CACHESIZE.toString())>=0)
                     cpuHashMap.put(CpuInfo.CACHESIZE,value);
               }
               nextLine = lineNumberReader.readLine();
            }
         }
         
      }
      finally
      {
         if(lineNumberReader != null)
            lineNumberReader.close();
      }
   }
   
   public String getProcessor()
   {
      return (String) cpuHashMap.get(CpuInfo.PROCESSOR);
   }
   
   public String getVendorId()
   {
      return (String) cpuHashMap.get(CpuInfo.VENDORID);
   }
   
   public String getCpuFamily()
   {
      return (String) cpuHashMap.get(CpuInfo.CPUFAMILY);
   }
   
   public String getModel()
   {
      return (String) cpuHashMap.get(CpuInfo.MODEL);
   }
   
   public String getModelName()
   {
      return (String) cpuHashMap.get(CpuInfo.MODELNAME);
   }
   
   public String getStepping()
   {
      return (String) cpuHashMap.get(CpuInfo.STEPPING);
   }
   
   public String getCpuSpeed()
   {
      return (String) cpuHashMap.get(CpuInfo.CPUMHZ);
   }
   
   public String getCacheSize()
   {
      return (String) cpuHashMap.get(CpuInfo.CACHESIZE);
   }
   
   public boolean compareTo(CpuInterface cpuInterface)
   {
      return true;
   }
   
   public boolean compareTo(HardwareComponentInterface componentInterface)
   {
      return true;
   }
   
   public String toString()
   {
       final CommonLabels commonLabels = CommonLabels.getInstance();
       final CommonSeps commonSeps = CommonSeps.getInstance();
      final StringMaker stringMaker = new StringMaker();
      stringMaker.append(CpuInfo.PROCESSOR.toString()).append(commonLabels.COLON_SEP).append(this.getProcessor());
      stringMaker.append(commonSeps.NEW_LINE);
      stringMaker.append(CpuInfo.VENDORID.toString()).append(commonLabels.COLON_SEP).append(this.getVendorId());
      stringMaker.append(commonSeps.NEW_LINE);
      stringMaker.append(CpuInfo.CPUFAMILY.toString()).append(commonLabels.COLON_SEP).append(this.getCpuFamily());
      stringMaker.append(commonSeps.NEW_LINE);
      stringMaker.append(CpuInfo.MODEL.toString()).append(commonLabels.COLON_SEP).append(this.getModel());
      stringMaker.append(commonSeps.NEW_LINE);
      stringMaker.append(CpuInfo.MODELNAME.toString()).append(commonLabels.COLON_SEP).append(this.getModelName());
      stringMaker.append(commonSeps.NEW_LINE);
      stringMaker.append(CpuInfo.STEPPING.toString()).append(commonLabels.COLON_SEP).append(this.getStepping());
      stringMaker.append(commonSeps.NEW_LINE);
      stringMaker.append(CpuInfo.CPUMHZ.toString()).append(commonLabels.COLON_SEP).append(this.getCpuSpeed());
      stringMaker.append(commonSeps.NEW_LINE);
      stringMaker.append(CpuInfo.CACHESIZE.toString()).append(commonLabels.COLON_SEP).append(this.getCacheSize());
      return stringMaker.toString();
   }
}
