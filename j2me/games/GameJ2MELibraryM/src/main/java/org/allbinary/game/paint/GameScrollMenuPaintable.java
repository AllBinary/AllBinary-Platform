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
package org.allbinary.game.paint;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.ColorChangeEventHandler;
import org.allbinary.graphics.color.ColorChangeListener;
import org.allbinary.graphics.form.FormPaintable;
import org.allbinary.graphics.form.ScrollSelectionForm;
import org.allbinary.graphics.form.item.StringComponent;
import org.allbinary.graphics.form.item.TextItem;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.logic.util.event.AllBinaryEventObject;

public class GameScrollMenuPaintable 
extends BasicGameDemoPaintable
implements ColorChangeListener
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    public GameScrollMenuPaintable(Paintable mainDemoStatePaintable, 
            Paintable ownershipPaintable, 
            Paintable helpPaintableInterface, 
            BasicColor basicColor)
    {
        super(mainDemoStatePaintable, ownershipPaintable, helpPaintableInterface);
    
        this.setColor(basicColor);
        ColorChangeEventHandler.getInstance().addListener(this);
    }

    public void setState(int state)
    {
        //logUtil.put("Setting Main Demo State Paintable", this, "setState");
        this.setCurrentStatePaintable(this.getMainDemoStatePaintable());
        //logUtil.put("Paintable is now: ").append(this.currentStatePaintable, this, "setState");
    }
    
    public void onEvent(AllBinaryEventObject eventObject)
    {
    }
    
    protected void setColor(BasicColor basicColor)
    {
        MainGameDemoStatePaintable mainGameDemoStatePaintable = 
            (MainGameDemoStatePaintable) this.getMainDemoStatePaintable();
        
        FormPaintable formPaintable = (FormPaintable) 
        mainGameDemoStatePaintable.getMenuPaintableInterface();
        
        ScrollSelectionForm scrollSelectionForm = 
            (ScrollSelectionForm) formPaintable.getForm();
        
        mainGameDemoStatePaintable.getOwnershipPaintableInterface().setBasicColor(basicColor);
        
        scrollSelectionForm.setButtonBasicColor(basicColor);
        
        for(int index = scrollSelectionForm.size() - 1; index >= 0; index--)
        {
            TextItem item = (TextItem) scrollSelectionForm.get(index);
            
            StringComponent stringComponent = item.getLabelStringComponent();
            
            if(stringComponent != null)
            {
                stringComponent.setForegroundBasicColor(basicColor);
            }
        }
    }
}
