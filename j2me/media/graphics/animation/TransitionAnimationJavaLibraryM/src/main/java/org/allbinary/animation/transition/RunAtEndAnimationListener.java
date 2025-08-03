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
package org.allbinary.animation.transition;

public class RunAtEndAnimationListener implements AnimationListenerInterface
{
    protected Runnable runnable;
    
    protected RunAtEndAnimationListener(Runnable runnable) {
        this.runnable = runnable;
    }
    
    @Override
    public void onAnimationStart() {
    }

    @Override
    public void onAnimationEnd() {
    }

    @Override
    public void onAnimationRepeat() {
    }

}