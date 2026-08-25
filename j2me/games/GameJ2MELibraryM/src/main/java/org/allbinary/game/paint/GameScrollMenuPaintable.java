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

import jsinterop.annotations.JsType;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.ColorChangeEventHandler;
import org.allbinary.graphics.color.ColorChangeListener;
import org.allbinary.graphics.form.FormPaintable;
import org.allbinary.graphics.form.ScrollSelectionForm;
import org.allbinary.graphics.form.item.ABStringComponent;
import org.allbinary.graphics.form.item.ABTextItem;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class GameScrollMenuPaintable 
extends BasicGameDemoPaintable
implements ColorChangeListener
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    @JsConstructor
    public GameScrollMenuPaintable(final Paintable mainDemoStatePaintable, 
            final Paintable ownershipPaintable, 
            final Paintable helpPaintableInterface, 
            final BasicColor basicColor)
    {
        super(mainDemoStatePaintable, ownershipPaintable, helpPaintableInterface);
    
        this.setColor(basicColor);
        ColorChangeEventHandler.getInstance().addListenerInterface(this);
    }

    @Override
    @JsMethod
    public void setState(final int state)
    {
        //this.logUtil.putF("Setting Main Demo State Paintable", this, "setState");
        this.setCurrentStatePaintable(this.getMainDemoStatePaintable());
        //this.logUtil.putF("Paintable is now: ").append(this.currentStatePaintable, this, "setState");
    }
    
    @Override
    @JsMethod
    public void onEvent(final AllBinaryEventObject eventObject)
    {
    }
    
    @JsMethod
    protected void setColor(final BasicColor basicColor)
    {
        final MainGameDemoStatePaintable mainGameDemoStatePaintable = 
            (MainGameDemoStatePaintable) this.getMainDemoStatePaintable();
        
        final FormPaintable formPaintable = (FormPaintable) 
        mainGameDemoStatePaintable.getMenuPaintableInterface();
        
        final ScrollSelectionForm scrollSelectionForm = 
            (ScrollSelectionForm) formPaintable.getForm();
        
        mainGameDemoStatePaintable.getOwnershipPaintableInterface().setBasicColorP(basicColor);
        
        scrollSelectionForm.setButtonBasicColor(basicColor);
        
        ABTextItem item;
        ABStringComponent stringComponent;
        for(int index = scrollSelectionForm.size() - 1; index >= 0; index--)
        {
            item = (ABTextItem) scrollSelectionForm.get(index);
            
            stringComponent = item.getLabelStringComponent();
            
            if(stringComponent != null)
            {
                stringComponent.setForegroundBasicColor(basicColor);
            }
        }
    }
}
