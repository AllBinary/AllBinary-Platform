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
package org.allbinary.game.input.form;

import org.allbinary.game.layer.RTSLayer;
import org.allbinary.game.layer.RTSPlayerLayerInterface;
import org.allbinary.graphics.form.item.CustomItem;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.identification.Group;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.form.ScrollSelectionForm;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCompositeInterface;
import org.allbinary.string.CommonLabels;

/**
 *
 * @author user
 */
public class CompositeRTSFormInput extends RTSFormInput
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final RTSFormInput[] rtsFormInputArray;
    private final int[] itemIndex;
    private final boolean isPrimaryWaypointCreator;

    public CompositeRTSFormInput(
        final Group[] groupInterface, 
            final boolean isPrimaryWaypointCreator)
    {
        this(groupInterface, isPrimaryWaypointCreator, new int[]
            {
                0
            });
    }

    public CompositeRTSFormInput(
        final Group[] groupInterface,
        final boolean isPrimaryWaypointCreator, 
        final int[] itemIndex)
    {
        super(groupInterface);

        this.itemIndex = itemIndex;
        
        this.isPrimaryWaypointCreator = isPrimaryWaypointCreator;
        
        this.rtsFormInputArray = new RTSFormInput[2];
        
        this.rtsFormInputArray[0] =
            new WaypointRTSFormInput(this.groupInterfaceArray, isPrimaryWaypointCreator);

        this.rtsFormInputArray[1] = new UnitRTSFormInput(this.groupInterfaceArray);        
    }

    public void setAllBinaryGameLayerManager(final AllBinaryGameLayerManager allBinaryGameLayerManager) throws Exception {

        super.setAllBinaryGameLayerManager(allBinaryGameLayerManager);

        final GeographicMapCompositeInterface geographicMapCompositeInterface
            = (GeographicMapCompositeInterface) allBinaryGameLayerManager;
        final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
        
        final int size = this.rtsFormInputArray.length;
        for(int index = 0; index < size; index++) {
            this.rtsFormInputArray[index].setAllBinaryGameLayerManager(allBinaryGameLayerManager);
        }
        
    }
    
    public void process(
        RTSLayer associatedRtsLayer,
        RTSPlayerLayerInterface rtsPlayerLayerInterface,
        AllBinaryLayerManager layerManager, GPoint point)
        throws Exception
    {   
        ScrollSelectionForm scrollSelectionForm =
            rtsPlayerLayerInterface.getCurrentScrollSelectionForm();

        int index = scrollSelectionForm.getSelectedIndex(point);

        if (this.isStickyItemSelected() && associatedRtsLayer == null)
        {
            //logUtil.put(index +"<="+ this.itemIndex[0], this, commonStrings.CONSTRUCTOR);

            //Make sure your not drag and dropping units yet
            if(this.getSelectedStickyItemIndex() <= this.itemIndex[0])
            {
            this.rtsFormInputArray[0].process(
                associatedRtsLayer,
                rtsPlayerLayerInterface,
                layerManager, point);
            }
        }
        else if (index > this.itemIndex[0])
        {
            this.rtsFormInputArray[1].process(
                associatedRtsLayer,
                rtsPlayerLayerInterface,
                layerManager, point);
        }
    }

    public void process(
        RTSLayer associatedRtsLayer,
        RTSPlayerLayerInterface rtsPlayerLayerInterface,
        AllBinaryLayerManager layerManager, CustomItem item, int index)
        throws Exception
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        logUtil.put(
             //commonLabels.ITEM_LABEL + item.getLabel() +
             CommonLabels.getInstance().INDEX_LABEL + index + " > " + this.itemIndex[0],
             this, commonStrings.PROCESS);

        if (this.isStickyItemSelected() &&
            associatedRtsLayer == null)
        {
            //Make sure your not drag and dropping units yet
            if(this.getSelectedStickyItemIndex() <= this.itemIndex[0])
            {
            this.rtsFormInputArray[0].process(
                associatedRtsLayer,
                rtsPlayerLayerInterface,
                layerManager, item, index);
            }
        }
        else if (index > this.itemIndex[0])
        {
            this.rtsFormInputArray[1].process(
                associatedRtsLayer,
                rtsPlayerLayerInterface,
                layerManager, item, index);
        }

    }

    public boolean processSticky(
        RTSLayer associatedRtsLayer,
        RTSPlayerLayerInterface rtsPlayerLayerInterface,
        AllBinaryLayerManager layerManager, GPoint point)
        throws Exception
    {
        return this.rtsFormInputArray[0].processSticky(
            associatedRtsLayer,
            rtsPlayerLayerInterface,
            layerManager, point);
    }

    public void processSticky(
        RTSLayer associatedRtsLayer,
        RTSPlayerLayerInterface rtsPlayerLayerInterface,
        AllBinaryLayerManager layerManager, CustomItem item, int index)
        throws Exception
    {
        this.rtsFormInputArray[0].processSticky(
            associatedRtsLayer,
            rtsPlayerLayerInterface,
            layerManager, item, index);
    }

    public int getSelectedStickyItemIndex()
    {
        return this.rtsFormInputArray[0].getSelectedStickyItemIndex();
    }

    /**
     * @return the stickyItemSelected
     */
    public boolean isStickyItemSelected()
    {
        return this.rtsFormInputArray[0].isStickyItemSelected();
    }

    /**
     * @param stickyItemSelected the stickyItemSelected to set
     */
    public void setStickyItemSelected(boolean stickyItemSelected)
    {
        this.rtsFormInputArray[0].setStickyItemSelected(stickyItemSelected);
    }

    /**
     * @return the selectedStickyItem
     */
    public CustomItem getSelectedStickyItem()
    {
        return this.rtsFormInputArray[0].getSelectedStickyItem();
    }

    /**
     * @param selectedStickyItem the selectedStickyItem to set
     */
    public void setSelectedStickyItem(CustomItem selectedStickyItem)
    {
        this.rtsFormInputArray[0].setSelectedStickyItem(selectedStickyItem);
    }
}
