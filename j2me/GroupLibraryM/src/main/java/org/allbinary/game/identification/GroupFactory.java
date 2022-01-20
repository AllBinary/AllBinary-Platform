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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.util.BasicArrayList;

public class GroupFactory {

   private static GroupFactory instance = new GroupFactory();

   public static GroupFactory getInstance()
   {
      return instance;
   }
   
   private final BasicArrayList list = new BasicArrayList();
   private int index = 0;
      
   public Group getNextGroup()
   {
      Group group = (Group) list.objectArray[index];
      index++;
      return group;
   }

   public Group getNextGroup(final String name)
   {
      final Group group = this.getNextGroup();
      group.setName(name);
      return group;
   }
   
   public void init(short groups, String[] nameArray)
   {
      final String TEAM = "Team ";
      
      index = 0;
      short size = (short) list.size();
      while(size < groups)
      {
          String name = null;
          if(size < nameArray.length)
          {
              name = nameArray[size];
          }
          else
          {
              name = TEAM + size;
          }

         list.add(new Group(name, (short) (size + 3)));
         size++;
      }
      
      //LogUtil.put(LogFactory.getInstance("size: " + list.objectArray.length, this, "init"));
   }
   
}
