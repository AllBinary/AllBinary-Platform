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
package org.allbinary.game.ai;

import java.util.Hashtable;

import org.allbinary.ai.ArtificialIntelligenceInterface;
import org.allbinary.bounds.TopULayerBounds;
import org.allbinary.game.input.GameInput;
import org.allbinary.game.physics.velocity.VelocityInterface;
import org.allbinary.game.physics.velocity.VelocityInterfaceCompositeInterface;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.layer.AllBinaryLayer;

public class TopUBoundBounceAIFactory
    implements ArtificialIntelligenceInterfaceFactoryInterface
{
    @Override
    public ArtificialIntelligenceInterface getInstance(
          Hashtable hashtable, AllBinaryLayer ownerLayerInterface, GameInput gameInput)
          throws Exception
    {
      VelocityInterfaceCompositeInterface velocityInterfaceCompositeInterface =
              (VelocityInterfaceCompositeInterface) ownerLayerInterface;

      VelocityInterface velocityInterface = (VelocityInterface)
          velocityInterfaceCompositeInterface.getVelocityProperties();

      int maxDistancePerTick = 
          (velocityInterface.getMaxForwardVelocity() >> velocityInterface.getVelocityXBasicDecimalP().getScaledFactor());
      int halfWidth = ownerLayerInterface.getWidth() * 2 + maxDistancePerTick + 1;// / 2;
      int halfHeight = ownerLayerInterface.getHeight() * 2 + maxDistancePerTick + 1;// / 2;

      // + 90 is for DoNotLoseYourBalls
      GPoint point = PointFactory.getInstance().getInstance(halfWidth, halfHeight + 60);

      DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();

      Rectangle rectangle = new Rectangle(point,
              (displayInfo.getLastWidth() - halfWidth - point.getX()),
              (displayInfo.getLastHeight() - halfHeight - point.getY()));

        return new BoundBounceAI(ownerLayerInterface, gameInput, 
           new TopULayerBounds(rectangle),
           new ReverseVelocityBoundsVisitor(ownerLayerInterface));
    }
}
