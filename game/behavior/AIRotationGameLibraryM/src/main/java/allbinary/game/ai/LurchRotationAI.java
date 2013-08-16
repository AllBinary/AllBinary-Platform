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
package allbinary.game.ai;

import allbinary.animation.IndexedAnimation;
import allbinary.animation.RotationAnimationInterface;
import allbinary.animation.RotationAnimationInterfaceCompositeInterface;
import allbinary.game.ai.tactical.LurchAI;
import allbinary.game.input.GameInput;
import allbinary.layer.AllBinaryLayer;
import allbinary.math.AngleIncrementInfo;

public class LurchRotationAI extends LurchAI {

    private IndexedAnimation rotationAnimationInterface;

    public LurchRotationAI(AllBinaryLayer ownerLayerInterface, GameInput gameInput) {
        super(ownerLayerInterface, gameInput);

        RotationAnimationInterfaceCompositeInterface rotationAnimationInterfaceCompositeInterface = (RotationAnimationInterfaceCompositeInterface) this.getOwnerLayerInterface();
        this.rotationAnimationInterface = rotationAnimationInterfaceCompositeInterface.getRotationAnimationInterface();

        this.update();
    }

    protected void reverse() {
        super.reverse();
        this.update();
    }
    
    private void update()
    {
       //LogUtil.put(LogFactory.getInstance("Angle: " + currentAngle, this, "reverse"));
        if (currentRelativeAngle == 0) {
            
            AngleIncrementInfo angleIncrementInfo = ((RotationAnimationInterface) this.rotationAnimationInterface).getAngleInfo().getAngleIncrementInfo();
            
            this.rotationAnimationInterface.setFrame(angleIncrementInfo.RIGHT_FRAME.intValue());
        } else if (currentRelativeAngle == 180) {
            
            AngleIncrementInfo angleIncrementInfo = ((RotationAnimationInterface) this.rotationAnimationInterface).getAngleInfo().getAngleIncrementInfo();

            this.rotationAnimationInterface.setFrame(angleIncrementInfo.LEFT_FRAME.intValue());
        }
    }
}