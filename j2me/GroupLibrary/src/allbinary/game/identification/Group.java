/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: Oct 7, 2007, 10:49:35 PM
 *
 *
 * Modified By         When       ?
 *
 */
package allbinary.game.identification;

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

      StringBuilder stringBuffer = new StringBuilder();

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
