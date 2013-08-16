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

   public void init(int groups, String[] nameArray)
   {
      final String TEAM = "Team ";
      
      index = 0;
      int size = list.size();
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

         list.add(new Group(name, size + 3));
         size++;
      }
   }
   
}
