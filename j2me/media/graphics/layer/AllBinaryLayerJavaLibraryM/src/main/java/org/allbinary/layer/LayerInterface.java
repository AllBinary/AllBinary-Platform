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

import jsinterop.annotations.JsType;

import org.allbinary.game.identification.GroupInterfaceCompositeInterface;
import org.allbinary.graphics.paint.PaintableInterface;
import jsinterop.annotations.JsMethod;


@JsType
public interface LayerInterface
    extends NamedInterface, PaintableInterface, GroupInterfaceCompositeInterface, PositionInterface {

   @JsMethod
   int getHeight();

   @JsMethod
   int getWidth();

   @JsMethod
   int getHalfHeight();

   @JsMethod
   int getHalfWidth();
   
   @JsMethod
   int getXP();

   @JsMethod
   int getYP();
   
   @JsMethod
   int getZP();

   @JsMethod
   int getX2();

   @JsMethod
   int getY2();
   
   @JsMethod
   int getZ2();

   @JsMethod
   boolean isVisible();

   @JsMethod
   void moveDXYZ(int dx, int dy, int dz);

   @JsMethod
   void setVisible(boolean visible);

   @JsMethod
   boolean implmentsTickableInterface();
   @JsMethod
   boolean implmentsCollidableInterface();
   @JsMethod
   boolean implmentsGameInputInterface();
   @JsMethod
   boolean implmentsArtificialIntelligenceCompositeInterface();
   @JsMethod
   int getType();
}
