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
package allbinary.game.identification;

import abcs.logic.basic.string.StringMaker;
import allbinary.logic.math.SmallIntegerSingletonFactory;

public class Group implements GroupInterface
{
    public static final Integer ID = SmallIntegerSingletonFactory.getInstance().getInstance(10);
   
   private final String teamName;
   private final short teamId;

   private final String string;

   private static final String GROUP_NAME_LABEL = "Group Name: ";
   private static final String ID_LABEL = " Id: ";
   
   public Group(String teamName, short teamId)
   {
      this.teamName = teamName;
      this.teamId = teamId;

      StringMaker stringBuffer = new StringMaker();

      stringBuffer.append(GROUP_NAME_LABEL);
      stringBuffer.append(this.teamName);
      stringBuffer.append(ID_LABEL);
      stringBuffer.append(this.teamId);

      this.string = stringBuffer.toString();
   }

   public String getGroupName()
   {
      return teamName;
   }

   public short getGroupId()
   {
      return teamId;
   }

   public String toString()
   {
       return string;
   }
}
