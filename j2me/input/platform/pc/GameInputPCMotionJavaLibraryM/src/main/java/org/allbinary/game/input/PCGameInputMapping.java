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
package org.allbinary.game.input;

//import org.allbinary.input.gyro.OrientationMotionGestureFactory;

import org.allbinary.game.input.mapping.PersistentInputMapping;
import org.allbinary.input.motion.button.BasicTouchInputFactory;
import org.allbinary.input.motion.gesture.TouchMotionGestureFactory;
import org.allbinary.input.motion.gesture.TrackballMotionGestureFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

public class PCGameInputMapping extends PersistentInputMapping
{

    @Override
    public void init(final AbeClientInformationInterface abeClientInformation)
    throws Exception
    {
        logUtil.put(this.commonStrings.START, this, this.commonStrings.INIT);

        PCKeyFactory.getInstance();
        TouchMotionGestureFactory.getInstance();
        TrackballMotionGestureFactory.getInstance();
        //OrientationMotionGestureFactory.getInstance();
        BasicTouchInputFactory.getInstance();

        super.init(abeClientInformation);

        if(this.getTotalMapped() == 0 || this.isDefaultNew())
        {
            this.getInputMapping().add(this.getDefault());
            this.save(abeClientInformation);
        }
    }

    @Override
    public boolean isDelete(Input input)
    {
        if(input == PCKeyFactory.getInstance().DEL)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean isSystemInput(Input input)
    {
        return false;
    }

    /*
    public GameKey getInstance(int id)
    {
        if(id < 0)
        {
            id = -id;
        }
        return super.getInstance(id);
    }
     */

}
