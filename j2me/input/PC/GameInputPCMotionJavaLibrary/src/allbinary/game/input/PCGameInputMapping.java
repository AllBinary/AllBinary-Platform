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
package allbinary.game.input;

//import org.allbinary.input.gyro.OrientationMotionGestureFactory;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.input.mapping.PersistentInputMapping;
import allbinary.input.motion.button.BasicTouchInputFactory;
import allbinary.input.motion.gesture.TouchMotionGestureFactory;
import allbinary.input.motion.gesture.TrackballMotionGestureFactory;

public class PCGameInputMapping extends PersistentInputMapping
{
    public void init()
    throws Exception
    {
        LogUtil.put(LogFactory.getInstance("Start", this, "init"));

        PCKeyFactory.getInstance();
        TouchMotionGestureFactory.getInstance();
        TrackballMotionGestureFactory.getInstance();
        //OrientationMotionGestureFactory.getInstance();
        BasicTouchInputFactory.getInstance();

        super.init();

        if(this.getTotalMapped() == 0 || this.isDefaultNew())
        {
            this.getInputMapping().add(this.getDefault());
            this.save();
        }
    }

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
