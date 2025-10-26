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
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.form.ScrollSelectionForm;
import org.allbinary.graphics.form.item.ItemArraySingletonFactoryInterface;

public class AdvancedRTSLayerFormFactory extends RTSLayerFormFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final ScrollSelectionForm scrollSelectionForm;
    
    protected AdvancedRTSLayerFormFactory(
            final String label,
            final ItemArraySingletonFactoryInterface itemArrayFactoryInterface)
    {
        ScrollSelectionForm scrollSelectionForm = ScrollSelectionForm.NULL_SCROLL_SELECTION_FORM;
        try
        {
            final CustomItem[] items = itemArrayFactoryInterface.getItems();

            scrollSelectionForm = 
                RTSScrollSelectionFormFactory.getInstance(label, items);
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e);
        }
        this.scrollSelectionForm = scrollSelectionForm;
    }
    
    /* (non-Javadoc)
     * @see org.allbinary.game.input.RTSLayerFormFactoryInterface#getInstance(org.allbinary.game.layer.RTSLayer)
     */
    @Override
    public ScrollSelectionForm getInstance(final RTSLayer rtsLayer)
    {
        return this.scrollSelectionForm;
    }
}
