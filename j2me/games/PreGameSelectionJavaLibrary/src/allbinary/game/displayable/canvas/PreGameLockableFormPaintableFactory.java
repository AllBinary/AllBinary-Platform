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
package allbinary.game.displayable.canvas;

import allbinary.graphics.form.ItemPaintable;
import allbinary.graphics.form.ItemPaintableFactory;
import allbinary.graphics.form.PaintableForm;
import allbinary.graphics.form.ScrollCurrentSelectionForm;

public class PreGameLockableFormPaintableFactory extends ItemPaintableFactory
{   
    private final int lockedIndex;
    
    public PreGameLockableFormPaintableFactory(int lockedIndex)
    {
        this.lockedIndex = lockedIndex;   
    }
    
    public ItemPaintable getInstance(PaintableForm paintableForm) 
    throws Exception
    {
        return new PreGameLockablePaintable(
                (ScrollCurrentSelectionForm) paintableForm, this.lockedIndex);
    }    
}
