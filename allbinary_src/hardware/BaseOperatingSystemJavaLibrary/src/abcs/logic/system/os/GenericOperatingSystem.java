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
package abcs.logic.system.os;

import abcs.logic.basic.string.CommonSeps;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.java.character.CharacterSet;

public class GenericOperatingSystem implements OperatingSystemInterface
{
   private final String osName;
   private final String osArch;
   private final String osVersion;
   private final CharacterSet characterSet;

   public GenericOperatingSystem() throws Exception
   {
       final StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
       
       final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;
       
       if(stringValidationUtil.isEmpty(SystemProperties.getName()))
       {
           this.osName = EMPTY_STRING;
       }
       else
       {
           this.osName = SystemProperties.getName();
       }

       if(stringValidationUtil.isEmpty(SystemProperties.getArch()))
       {
           this.osArch = EMPTY_STRING;
       }
       else
       {
           this.osArch = SystemProperties.getArch();
       }


       if(stringValidationUtil.isEmpty(SystemProperties.getVersion()))
       {
           this.osVersion = EMPTY_STRING;
       }
       else
       {
           this.osVersion = SystemProperties.getVersion();
       }

      this.characterSet = new CharacterSet();
   }
   
   public String getName()
   {
      return this.osName;
   }
   
   public String getVersion()
   {
      return this.osVersion;
   }
   
   public String getArch()
   {
      return this.osArch;
   }

   public CharacterSet getCharacterSet()
   {
      return this.characterSet;
   }
   
   public String toString()
   {
       final String NEW_LINE = CommonSeps.getInstance().NEW_LINE;
       
      StringBuilder osBuffer = new StringBuilder();
      osBuffer.append("Operating System Info: \n");
      osBuffer.append(this.getName());
      osBuffer.append(NEW_LINE);
      osBuffer.append(this.getArch());
      osBuffer.append(NEW_LINE);
      osBuffer.append(this.getVersion());
      osBuffer.append(NEW_LINE);
      osBuffer.append(this.getCharacterSet());
      osBuffer.append(NEW_LINE);

      return osBuffer.toString();
   }
 
}
