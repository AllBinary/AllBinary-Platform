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
package org.allbinary.logic.math.vector;

import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.math.BasicDecimal;
import org.allbinary.math.PositionStrings;

public class Vertex
{
   public BasicDecimal x = new BasicDecimal();
   public BasicDecimal y = new BasicDecimal();
   public BasicDecimal z = new BasicDecimal();

   public Vertex(int x, int y, int z)
   {
      this.x.set(x);
      this.y.set(y);
      this.z.set(z);
   }
   
   public Vertex(int x, int y)
   {
      this.x.set(x);
      this.y.set(y);
   }
   
   public Vertex()
   {
   }

   public String toString()
   {
      StringMaker stringBuffer = new StringMaker();
      
      PositionStrings positionStrings = 
          PositionStrings.getInstance();
      
      stringBuffer.append(positionStrings.X_LABEL);
      stringBuffer.append(x.toString());
      stringBuffer.append(CommonSeps.getInstance().SPACE);
      stringBuffer.append(positionStrings.Y_LABEL);
      stringBuffer.append(y.toString());
      stringBuffer.append(CommonSeps.getInstance().SPACE);
      stringBuffer.append(positionStrings.Z_LABEL);
      stringBuffer.append(z.toString());
      
      return stringBuffer.toString();
   }   
}
