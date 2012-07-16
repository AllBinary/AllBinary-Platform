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
package allbinary.view;

public class StaticViewPosition extends ViewPosition
{
    public StaticViewPosition(int x, int y, int z)
    {
        super(x, y, z);
    }
    
   public int getX()
   {
       return this.x;
   }

   public int getY()
   {
       return this.y;
   }

   public int getZ()
   {
       return this.z;
   }

}