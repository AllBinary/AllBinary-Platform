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
package allbinary.graphics.j2me.workarea.tools;

import org.allbinary.util.BasicArrayList;

/**
 *
 * @author user
 */
public class Points implements PointsInterface
{
    private final BasicArrayList pointVector;

    public Points()
    {
        this.pointVector = new BasicArrayList();
    }

    public void init()
    {
        this.pointVector.clear();
    }

    public BasicArrayList getPoints()
    {
        return this.pointVector;
    }

    public void addPoints(BasicArrayList vector)
    {
        this.pointVector.addAll2(vector);
    }

   public boolean isValid()
   {
      if (this.pointVector.size() > 1)
      {
         return true;
      } else
      {
         return false;
      }
   }

   public int getSize()
   {
      return this.pointVector.size();
   }
}
