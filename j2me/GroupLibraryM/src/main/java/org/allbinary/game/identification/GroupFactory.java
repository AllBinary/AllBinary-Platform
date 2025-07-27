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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.util.BasicArrayList;

public class GroupFactory {
    protected final LogUtil logUtil = LogUtil.getInstance();


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
      //logUtil.put(new StringMaker().append("group: ").append(group).append(" index: ").append(index).toString(), this, "getNextGroup");
      index++;
      return group;
   }

   public Group getNextGroup(final String name)
   {
      final Group group = this.getNextGroup();
      //logUtil.put(new StringMaker().append("group: ").append(group).append(" name: ").append(name).toString(), this, "getNextGroup");
      group.setName(name);
      return group;
   }
   
   public void init(short groups, String[] nameArray)
   {
       final StringMaker stringMaker = new StringMaker();
       final StringUtil stringUtil = StringUtil.getInstance();
       
      final String TEAM = "Team ";
    
      list.clear();
      index = 0;
      int size = list.size();
      while(size < groups)
      {
          String name = stringUtil.EMPTY_STRING;
          if(size < nameArray.length)
          {
              name = nameArray[size];
          }
          else
          {
              stringMaker.delete(0, stringMaker.length());
              name = stringMaker.append(TEAM).append(size).toString();
          }

         list.add(new Group(name, (short) (size + 3)));
         size++;
      }
      
      //logUtil.put("size: " + list.size(), this, this.commonStrings.INIT);
   }
   
}
