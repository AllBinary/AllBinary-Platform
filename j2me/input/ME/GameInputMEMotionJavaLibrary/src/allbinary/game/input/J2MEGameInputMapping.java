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

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.input.mapping.PersistentInputMapping;

public class J2MEGameInputMapping extends PersistentInputMapping
{
    public void init() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().INIT));

        J2MEKeyFactory.getInstance().init();

        super.init();

        if(this.getTotalMapped() == 0 || this.isDefaultNew())
        {
            this.getInputMapping().add(this.getDefault());
            this.save();
        }
    }

    public boolean isDelete(Input input)
    {
        //if(input == J2MEKeyFactory.getInstance())
        if(input == GameKey.GAME_D)
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
}
