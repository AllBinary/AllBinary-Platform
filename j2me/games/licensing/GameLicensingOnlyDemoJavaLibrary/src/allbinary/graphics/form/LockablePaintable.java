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
package allbinary.graphics.form;

import javax.microedition.lcdui.Graphics;

import abcs.logic.system.security.licensing.LockedResources;
import abcs.logic.system.security.licensing.LockedUtil;
import allbinary.animation.Animation;
import allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;

public class LockablePaintable
    extends ItemPaintable
{
    private final Animation animation = (Animation)
    FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance().get(
            LockedResources.getInstance().LOCKED_DEMO_GAME_FEATURE_RESOURCE).getInstance();
    
    private final int lockedIndex;
    
    public LockablePaintable(int lockedIndex) 
    throws Exception
    {
        this.lockedIndex = lockedIndex;
    }
    
    public void paint(Graphics graphics, int currentIndex, int x, int y)
    {
        if(LockedUtil.getInstance().isLockedFeature() && 
                currentIndex >= this.getLockedIndex())
        {
            //- this.halfWidth
            this.getAnimation().paint(graphics, x, y);
        }
    }

    protected int getLockedIndex()
    {
        return lockedIndex;
    }

    protected Animation getAnimation()
    {
        return animation;
    }

}
