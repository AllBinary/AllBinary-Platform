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
package allbinary.android.input.motion.button;

import org.allbinary.AndroidResources;

import allbinary.data.resource.ResourceUtil;
import allbinary.init.Init;
import allbinary.input.motion.button.TouchButtonBlankResource;
import allbinary.input.motion.button.TouchButtonDownResource;
import allbinary.input.motion.button.TouchButtonGenericActionResource;
import allbinary.input.motion.button.TouchButtonStartResource;
import allbinary.input.motion.button.TouchButtonStrafeLeftResource;
import allbinary.input.motion.button.TouchButtonStrafeRightResource;
import allbinary.input.motion.button.TouchButtonTurnLeftResource;
import allbinary.input.motion.button.TouchButtonTurnRightResource;
import allbinary.input.motion.button.TouchButtonUpResource;

public class TouchButtonAndroidResources extends Init
{
    public void init()
    {
        ResourceUtil resourceUtil = ResourceUtil.getInstance();
        
        resourceUtil.addResource(
                TouchButtonBlankResource.RESOURCE,
                Integer.valueOf(AndroidResources.raw.touch_button_64_by_64));

        resourceUtil.addResource(
                TouchButtonGenericActionResource.RESOURCE,
                Integer.valueOf(AndroidResources.raw.touch_button_generic_action_64_by_64));
        
        resourceUtil.addResource(
                TouchButtonStartResource.RESOURCE,
                Integer.valueOf(AndroidResources.raw.touch_button_start_64_by_64));
        
        resourceUtil.addResource(
                TouchButtonUpResource.RESOURCE,
                Integer.valueOf(AndroidResources.raw.touch_button_up_arrow_64_by_64));

        resourceUtil.addResource(
                TouchButtonDownResource.RESOURCE,
                Integer.valueOf(AndroidResources.raw.touch_button_down_arrow_64_by_64));

        resourceUtil.addResource(
                TouchButtonTurnLeftResource.RESOURCE,
                Integer.valueOf(AndroidResources.raw.touch_button_turn_left_arrow_64_by_64));

        resourceUtil.addResource(
                TouchButtonTurnRightResource.RESOURCE,
                Integer.valueOf(AndroidResources.raw.touch_button_turn_right_arrow_64_by_64));

        resourceUtil.addResource(
                TouchButtonStrafeLeftResource.RESOURCE,
                Integer.valueOf(AndroidResources.raw.touch_button_strafe_left_arrow_64_by_64));

        resourceUtil.addResource(
                TouchButtonStrafeRightResource.RESOURCE,
                Integer.valueOf(AndroidResources.raw.touch_button_strafe_right_arrow_64_by_64));
    }
}
