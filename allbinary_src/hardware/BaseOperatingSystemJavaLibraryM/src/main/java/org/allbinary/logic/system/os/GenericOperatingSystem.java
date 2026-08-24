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

import jsinterop.annotations.JsType;

import org.allbinary.logic.java.character.CharacterSet;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.string.CommonSeps;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class GenericOperatingSystem implements OperatingSystemInterface
{
   private final String osName;
   private final String osArch;
   private final String osVersion;
   private final CharacterSet characterSet = new CharacterSet();

   @JsProperty
   protected boolean scalable = false;
   
   @JsConstructor
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
   @JsMethod
   public String getName()
   {
      return this.osName;
   }
   
   @Override
   @JsMethod
   public String getVersion()
   {
      return this.osVersion;
   }
   
   @Override
   @JsMethod
   public String getArch()
   {
      return this.osArch;
   }

   @Override
   @JsMethod
   public CharacterSet getCharacterSet()
   {
      return this.characterSet;
   }
   
   @Override
   @JsMethod
   public boolean isOverScan()
   {
       return false;
   }

   @Override
   @JsMethod
   public int getOverScanXPercent()
   {
       return 100;
   }

   @Override
   @JsMethod
   public int getOverScanYPercent()
   {
       return 100;
   }
   
   @Override
   @JsMethod
   public boolean isScalable()
   {
       return this.scalable;
   }
   
   @Override
   @JsMethod
   public boolean isAutoHide() {
       return false;
   }

   @JsMethod
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
