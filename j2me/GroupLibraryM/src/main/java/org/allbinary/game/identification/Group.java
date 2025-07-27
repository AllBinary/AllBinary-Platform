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
package org.allbinary.game.identification;

import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;

public class Group implements GroupInterface
{
    public static final Integer ID = SmallIntegerSingletonFactory.getInstance().getInstance(10);
   
   private String name = StringUtil.getInstance().EMPTY_STRING;
   private final short teamId;

   private String string = StringUtil.getInstance().EMPTY_STRING;

   private static final String GROUP_NAME_LABEL = "Group Name: ";
   private static final String ID_LABEL = " Id: ";
   
   public Group(String teamName, short teamId)
   {
      this.setName(teamName);
      this.teamId = teamId;

   }

   @Override
   public String getGroupName()
   {
      return name;
   }

   public void setName(final String name)
   {
      this.name = name;
      
      final StringMaker stringBuffer = new StringMaker();

      stringBuffer.append(GROUP_NAME_LABEL);
      stringBuffer.append(this.name);
      stringBuffer.append(ID_LABEL);
      stringBuffer.append(this.teamId);

      this.string = stringBuffer.toString();
   }
   
   @Override
   public short getGroupId()
   {
      return teamId;
   }

   public String toString()
   {
       return string;
   }
}
