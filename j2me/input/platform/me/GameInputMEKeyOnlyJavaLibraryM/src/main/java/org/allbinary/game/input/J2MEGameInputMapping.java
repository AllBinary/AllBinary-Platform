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
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

public class J2MEGameInputMapping extends PersistentInputMapping
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    public void init(final AbeClientInformationInterface abeClientInformation) throws Exception
    {
        logUtil.put(commonStrings.START, this, commonStrings.INIT);

        J2MEKeyFactory.getInstance().init();

        super.init(abeClientInformation);

        if(this.getTotalMapped() == 0 || this.isDefaultNew())
        {
            this.getInputMapping().add(this.getDefault());
            this.save(abeClientInformation);
        }
    }

    public boolean isDelete(Input input)
    {
        //if(input == J2MEKeyFactory.getInstance())
        if(input == GameKeyFactory.getInstance().GAME_D)
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
