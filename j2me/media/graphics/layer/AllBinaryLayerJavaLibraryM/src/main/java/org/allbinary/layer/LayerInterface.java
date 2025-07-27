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
package org.allbinary.layer;

import org.allbinary.game.identification.GroupInterfaceCompositeInterface;
import org.allbinary.graphics.paint.PaintableInterface;

public interface LayerInterface
    extends NamedInterface, PaintableInterface, GroupInterfaceCompositeInterface, PositionInterface {

   int getHeight();

   int getWidth();

   int getHalfHeight();

   int getHalfWidth();
   
   int getXP();

   int getYP();
   
   int getZP();

   int getX2();

   int getY2();
   
   int getZ2();

   boolean isVisible();

   void move(int dx, int dy, int dz);

   void setVisible(boolean visible);

   boolean implmentsTickableInterface();
   boolean implmentsCollidableInterface();
   boolean implmentsGameInputInterface();
   boolean implmentsArtificialIntelligenceCompositeInterface();
   int getType();
}
