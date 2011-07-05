/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: 11/19/02
 *
 *
 * Modified By         When       ?
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
      Group group = (Group) list.get(index);
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
