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
package org.allbinary.business.category.store.theme;

import java.util.Comparator;

public class ThemeComparator implements Comparator
{
   public ThemeComparator()
   {
   }
   
   public int compare(Object object, Object object2)
   {
      String string = (String) object;
      String string2 = (String) object2;
      return string.compareTo(string2);
   }
}
