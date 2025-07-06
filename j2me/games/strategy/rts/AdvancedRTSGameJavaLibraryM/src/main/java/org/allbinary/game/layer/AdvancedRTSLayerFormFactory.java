/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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
package org.allbinary.game.layer;

import org.allbinary.game.input.RTSLayerFormFactory;
import org.allbinary.graphics.form.item.CustomItem;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.form.ScrollSelectionForm;
import org.allbinary.graphics.form.item.ItemArraySingletonFactoryInterface;

public class AdvancedRTSLayerFormFactory extends RTSLayerFormFactory
{
    private ScrollSelectionForm scrollSelectionForm;
    
    protected AdvancedRTSLayerFormFactory(
            String label,
            ItemArraySingletonFactoryInterface itemArrayFactoryInterface)
    {
        try
        {
            CustomItem[] items = itemArrayFactoryInterface.getItems();

            this.scrollSelectionForm = 
                RTSScrollSelectionFormFactory.getInstance(label, items);
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e));
        }
    }
    
    /* (non-Javadoc)
     * @see org.allbinary.game.input.RTSLayerFormFactoryInterface#getInstance(org.allbinary.game.layer.RTSLayer)
     */
    public ScrollSelectionForm getInstance(RTSLayer rtsLayer)
    {
        return this.scrollSelectionForm;
    }
}
