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
package org.allbinary.android.input.motion.button;

import org.allbinary.AndroidResources;
import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.init.Init;
import org.allbinary.input.motion.button.CommonButtons;
import org.allbinary.input.motion.button.TouchButtonBlankResource;
import org.allbinary.input.motion.button.TouchButtonDownResource;
import org.allbinary.input.motion.button.TouchButtonGenericActionResource;
import org.allbinary.input.motion.button.TouchButtonResource;
import org.allbinary.input.motion.button.TouchButtonStartResource;
import org.allbinary.input.motion.button.TouchButtonStrafeLeftResource;
import org.allbinary.input.motion.button.TouchButtonStrafeRightResource;
import org.allbinary.input.motion.button.TouchButtonTurnLeftResource;
import org.allbinary.input.motion.button.TouchButtonTurnRightResource;
import org.allbinary.input.motion.button.TouchButtonUpResource;
import org.allbinary.logic.system.os.OperatingSystemFactory;

public class TouchButtonAndroidResources extends Init
{
    @Override
    public void init()
    {
        final ResourceUtil resourceUtil = ResourceUtil.getInstance();
        
        final AndroidResources androidResources = AndroidResources.getInstance();
        
        final TouchButtonResource[] RESOURCE_STRING_ARRAY = {
        TouchButtonBlankResource.getInstance(),
            TouchButtonGenericActionResource.getInstance(),
                TouchButtonStartResource.getInstance(),
                TouchButtonUpResource.getInstance(),
                TouchButtonDownResource.getInstance(),
                TouchButtonTurnLeftResource.getInstance(),
                TouchButtonTurnRightResource.getInstance(),
                TouchButtonStrafeLeftResource.getInstance(),
                TouchButtonStrafeRightResource.getInstance()
        };
        
        final int[] RESOURCE_ARRAY = new int[RESOURCE_STRING_ARRAY.length];
        
        if(CommonButtons.getInstance().STANDARD_BUTTON_SIZE == 128)
        {
            RESOURCE_ARRAY[0] = androidResources.raw.touch_button_128_by_128;
            RESOURCE_ARRAY[1] = androidResources.raw.touch_button_generic_action_128_by_128;
            RESOURCE_ARRAY[2] = androidResources.raw.touch_button_start_128_by_128;
            RESOURCE_ARRAY[3] = androidResources.raw.touch_button_up_arrow_128_by_128;
            RESOURCE_ARRAY[4] = androidResources.raw.touch_button_down_arrow_128_by_128;
            RESOURCE_ARRAY[5] = androidResources.raw.touch_button_turn_left_arrow_128_by_128;
            RESOURCE_ARRAY[6] = androidResources.raw.touch_button_turn_right_arrow_128_by_128;
            RESOURCE_ARRAY[7] = androidResources.raw.touch_button_strafe_left_arrow_128_by_128;
            RESOURCE_ARRAY[8] = androidResources.raw.touch_button_strafe_right_arrow_128_by_128;

            if(OperatingSystemFactory.getInstance().getOperatingSystemInstance().isOverScan())
            {
                //final int[] HINT_RESOURCE_ARRAY = new int[RESOURCE_STRING_ARRAY.length];

                resourceUtil.addResource(
                        RESOURCE_STRING_ARRAY[2].HINT,
                        Integer.valueOf(androidResources.raw.ouya_o));
                
                /*
                for (int index = 0; index < RESOURCE_ARRAY.length; index++) {
                    resourceUtil.addResource(
                            RESOURCE_STRING_ARRAY[index].HINT,
                            Integer.valueOf(HINT_RESOURCE_ARRAY[index]));
                }
                */
            }

        }
        else
            //if(CommonButtons.getInstance().STANDARD_BUTTON_SIZE == 64)
        {
            RESOURCE_ARRAY[0] = androidResources.raw.touch_button_64_by_64;
            RESOURCE_ARRAY[1] = androidResources.raw.touch_button_generic_action_64_by_64;
            RESOURCE_ARRAY[2] = androidResources.raw.touch_button_start_64_by_64;
            RESOURCE_ARRAY[3] = androidResources.raw.touch_button_up_arrow_64_by_64;
            RESOURCE_ARRAY[4] = androidResources.raw.touch_button_down_arrow_64_by_64;
            RESOURCE_ARRAY[5] = androidResources.raw.touch_button_turn_left_arrow_64_by_64;
            RESOURCE_ARRAY[6] = androidResources.raw.touch_button_turn_right_arrow_64_by_64;
            RESOURCE_ARRAY[7] = androidResources.raw.touch_button_strafe_left_arrow_64_by_64;
            RESOURCE_ARRAY[8] = androidResources.raw.touch_button_strafe_right_arrow_64_by_64;
        }
        
        for(int index = 0; index < RESOURCE_ARRAY.length; index++)
        {
            resourceUtil.addResource(
                RESOURCE_STRING_ARRAY[index].RESOURCE,
                Integer.valueOf(RESOURCE_ARRAY[index]));            
        }

    }
}
