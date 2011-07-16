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
package allbinary.game.paint;

import allbinary.graphics.color.BasicColor;
import allbinary.graphics.color.ColorChangeEventHandler;
import allbinary.graphics.color.ColorChangeListener;
import allbinary.graphics.form.FormPaintable;
import allbinary.graphics.form.ScrollSelectionForm;
import allbinary.graphics.form.item.TextItem;
import allbinary.graphics.paint.Paintable;
import allbinary.logic.basic.util.event.AllBinaryEventObject;

public class GameScrollMenuPaintable 
extends BasicGameDemoPaintable
implements ColorChangeListener
{
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
        //LogUtil.put(LogFactory.getInstance("Setting Main Demo State Paintable", this, "setState"));
        this.setCurrentStatePaintable(this.getMainDemoStatePaintable());
        //LogUtil.put(LogFactory.getInstance("Paintable is now: " + this.currentStatePaintable, this, "setState"));
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
        
        OwnershipPaintable ownershipPaintable = (OwnershipPaintable) 
        mainGameDemoStatePaintable.getOwnershipPaintableInterface();
        
        ownershipPaintable.setBasicColor(basicColor);
        
        scrollSelectionForm.setButtonBasicColor(basicColor);
        
        for(int index = scrollSelectionForm.size() - 1; index >= 0; index--)
        {
            TextItem item = (TextItem) scrollSelectionForm.get(index);
            item.getLabelStringComponent().setForegroundBasicColor(basicColor);
        }
    }
}
