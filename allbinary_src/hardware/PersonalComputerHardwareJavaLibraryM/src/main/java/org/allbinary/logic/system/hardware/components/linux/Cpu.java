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

import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.FilePathData;
import org.allbinary.logic.io.file.directory.SubDirectory;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;
import org.allbinary.logic.system.hardware.components.interfaces.HardwareComponentInterface;
import org.allbinary.logic.system.hardware.components.interfaces.CpuInterface;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class Cpu implements CpuInterface, HardwareComponentInterface
{
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
            LogUtil.put(LogFactory.getInstance("Cpu Data: " + this.toString(), this, this.commonStrings.CONSTRUCTOR, e));
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
            final BasicArrayList fileVector = SubDirectory.getInstance().search(filePath, new AbFile(FilePathData.SEPARATOR));
            
            if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().OS))
            {
               LogUtil.put(LogFactory.getInstance("Cpu File Vector Size: " + fileVector.size(), this, this.commonStrings.CONSTRUCTOR));
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
      StringBuffer cpuBuffer = new StringBuffer();
      cpuBuffer.append(CpuInfo.PROCESSOR + ": " + this.getProcessor());
      cpuBuffer.append("\n");
      cpuBuffer.append(CpuInfo.VENDORID + ": " + this.getVendorId());
      cpuBuffer.append("\n");
      cpuBuffer.append(CpuInfo.CPUFAMILY + ": " + this.getCpuFamily());
      cpuBuffer.append("\n");
      cpuBuffer.append(CpuInfo.MODEL + ": " + this.getModel());
      cpuBuffer.append("\n");
      cpuBuffer.append(CpuInfo.MODELNAME + ": " + this.getModelName());
      cpuBuffer.append("\n");
      cpuBuffer.append(CpuInfo.STEPPING + ": " + this.getStepping());
      cpuBuffer.append("\n");
      cpuBuffer.append(CpuInfo.CPUMHZ + ": " + this.getCpuSpeed());
      cpuBuffer.append("\n");
      cpuBuffer.append(CpuInfo.CACHESIZE + ": " + this.getCacheSize());
      return cpuBuffer.toString();
   }
}
