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
   private final int teamId;

   private final String string;

   public Group(String teamName, int teamId)
   {
      this.teamName = teamName;
      this.teamId = teamId;

      StringMaker stringBuffer = new StringMaker();

      stringBuffer.append("Group Name: ");
      stringBuffer.append(this.getGroupName());
      stringBuffer.append(" ID: ");
      stringBuffer.append(this.getGroupId());

      this.string = stringBuffer.toString();
   }

   public String getGroupName()
   {
      return teamName;
   }

   public int getGroupId()
   {
      return teamId;
   }

   public String toString()
   {
       return string;
   }
}
