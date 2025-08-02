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
package org.allbinary.physics.movement;

import org.allbinary.game.layer.AllBinaryGameLayer;
import org.allbinary.game.physics.velocity.VelocityProperties;
import org.allbinary.logic.math.BasicDecimal;

public class XYOscillateVelocityMovement 
extends BasicConstantVelocityMovement
{
    private int oscillateMin;
    private int oscillateMax;
    private int oscillate;
    
    private boolean oscillatePositive;

    public XYOscillateVelocityMovement(
       BasicDecimal speedBasicDecimal, int oscillateMin, int oscillateMax)
    {
        super(speedBasicDecimal, new VelocityProperties(
                (int) speedBasicDecimal.getUnscaled(), 
                (int) speedBasicDecimal.getUnscaled()
        ));
        
        this.oscillateMin = oscillateMin;
        this.oscillateMax = oscillateMax;
    }
    
    @Override
    public void process(AllBinaryGameLayer layer)
    throws Exception
    {
        super.process(layer);
        
        if(this.oscillate < this.oscillateMin)
        {
            this.oscillatePositive = true;
            this.getVelocityProperties().getVelocityYBasicDecimalP().add(3000);
        }
        else
        if(this.oscillate > this.oscillateMax)
        {
            this.oscillatePositive = false;
            this.getVelocityProperties().getVelocityYBasicDecimalP().subtract(3000);
        }

        if(this.oscillatePositive)
        {
            this.oscillate++;
            this.getVelocityProperties().getVelocityXBasicDecimalP().add(5000);
            
        }
        else
        {
            this.oscillate--;
            this.getVelocityProperties().getVelocityXBasicDecimalP().subtract(5000);
        }
    }
}
