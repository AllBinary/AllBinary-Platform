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
package org.allbinary.logic.system.os;

import org.allbinary.logic.java.character.CharacterSet;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.string.CommonSeps;

public class GenericOperatingSystem implements OperatingSystemInterface
{
   private final String osName;
   private final String osArch;
   private final String osVersion;
   private final CharacterSet characterSet = new CharacterSet();

   protected boolean scalable = false;
   
   public GenericOperatingSystem()
   {
       final SystemProperties systemProperties = SystemProperties.getInstance();

       final StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
       
       final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;
       
       if(stringValidationUtil.isEmpty(systemProperties.getName()))
       {
           this.osName = EMPTY_STRING;
       }
       else
       {
           this.osName = systemProperties.getName();
       }

       if(stringValidationUtil.isEmpty(systemProperties.getArch()))
       {
           this.osArch = EMPTY_STRING;
       }
       else
       {
           this.osArch = systemProperties.getArch();
       }


       if(stringValidationUtil.isEmpty(systemProperties.getVersion()))
       {
           this.osVersion = EMPTY_STRING;
       }
       else
       {
           this.osVersion = systemProperties.getVersion();
       }
       
   }
   
   @Override
   public String getName()
   {
      return this.osName;
   }
   
   @Override
   public String getVersion()
   {
      return this.osVersion;
   }
   
   @Override
   public String getArch()
   {
      return this.osArch;
   }

   @Override
   public CharacterSet getCharacterSet()
   {
      return this.characterSet;
   }
   
   @Override
   public boolean isOverScan()
   {
       return false;
   }

   @Override
   public int getOverScanXPercent()
   {
       return 100;
   }

   @Override
   public int getOverScanYPercent()
   {
       return 100;
   }
   
   @Override
   public boolean isScalable()
   {
       return this.scalable;
   }
   
   @Override
   public boolean isAutoHide() {
       return false;
   }

   public String toString()
   {
       final String NEW_LINE = CommonSeps.getInstance().NEW_LINE;

      final StringMaker osBuffer = new StringMaker();
      osBuffer.append("Operating System Info: \n");
      osBuffer.append(this.getName());
      osBuffer.append(NEW_LINE);
      osBuffer.append(this.getArch());
      osBuffer.append(NEW_LINE);
      osBuffer.append(this.getVersion());
      osBuffer.append(NEW_LINE);
      osBuffer.append(StringUtil.getInstance().toString(this.getCharacterSet()));
      osBuffer.append(NEW_LINE);

      return osBuffer.toString();
   }
 
}
