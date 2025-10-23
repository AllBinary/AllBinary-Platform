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
package org.allbinary.game.displayable.canvas;

import org.allbinary.graphics.form.ItemPaintable;
import org.allbinary.graphics.form.ItemPaintableFactory;
import org.allbinary.graphics.form.PaintableForm;
import org.allbinary.graphics.form.ScrollCurrentSelectionForm;

public class PreGameLockableFormPaintableFactory extends ItemPaintableFactory
{   
    private final int lockedIndex;
    
    public PreGameLockableFormPaintableFactory(int lockedIndex)
    {
        this.lockedIndex = lockedIndex;   
    }
    
    @Override
    public ItemPaintable getInstance(PaintableForm paintableForm) 
    throws Exception
    {
        return new PreGameLockablePaintable(
                (ScrollCurrentSelectionForm) paintableForm, this.lockedIndex);
    }    
}
