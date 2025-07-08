/*
 * AllBinary Open License Version 1
 * Copyright (c) 2003 AllBinary
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

import java.util.Hashtable;

import org.allbinary.game.layer.RTSLayer;
import org.allbinary.game.layer.RTSPlayerLayerInterface;
import org.allbinary.game.layer.item.LayerInterfaceFactoryImageItem;
import org.allbinary.graphics.form.item.CustomItem;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.direction.DirectionFactory;
import org.allbinary.game.identification.Group;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.form.ScrollSelectionForm;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.layer.LayerInterfaceFactoryInterface;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;

/**
 *
 * @author user
 */
public class RTSFormInput
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final Hashtable hashtable = new Hashtable();
        
    protected final RTSLayer[] newUnconstructedRTSLayerInterfaceArray = new RTSLayer[7];
    protected final Group[] groupInterfaceArray;
    private GeographicMapCellPosition selectedGeographicCellPosition;
    private boolean stickyItemSelected;
    private int selectedStickyItemIndex;
    private CustomItem selectedStickyItem;
    
    public RTSFormInput(final Group[] groupInterfaceArray)
    {
        //logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);

        this.groupInterfaceArray = groupInterfaceArray;

        this.hashtable.put(DirectionFactory.getInstance().NAME, DirectionFactory.getInstance().DOWN);

        //if (groupInterface == null)
            //throw new Exception("No Group Provided");

        if (groupInterfaceArray != null)
        {
            this.hashtable.put(Group.ID, groupInterfaceArray);
        }
        
        //logUtil.put(commonStrings.END, this, commonStrings.CONSTRUCTOR);
    }

    public void setAllBinaryGameLayerManager(final AllBinaryGameLayerManager allBinaryGameLayerManager) throws Exception {
        
    }
        
    public void process(
        final RTSLayer associatedRtsLayer,
        final RTSPlayerLayerInterface rtsPlayerLayerInterface,
        final AllBinaryLayerManager layerManager, final GPoint point)
        throws Exception
    {
        final int index = this.getIndexAt(rtsPlayerLayerInterface, point);

        if (index < 0)
        {
            return;
        }

        final ScrollSelectionForm scrollSelectionForm =
            rtsPlayerLayerInterface.getCurrentScrollSelectionForm();

        //this.getItemAt(rtsPlayerLayerInterface, point);
        final CustomItem item = scrollSelectionForm.get(index);
        if (item == null)
        {
            return;
        }

        this.process(
            associatedRtsLayer,
            rtsPlayerLayerInterface,
            layerManager, item, index);
    }

    public void process(final AllBinaryLayerManager layerManager) throws Exception {
        
        this.setAllBinaryGameLayerManager((AllBinaryGameLayerManager) layerManager);
    }
    
    public void process(
        final RTSLayer associatedRtsLayer,
        final RTSPlayerLayerInterface rtsPlayerLayerInterface,
        final AllBinaryLayerManager layerManager, final CustomItem item, final int index)
        throws Exception
    {
    }

    public boolean processSticky(
        final RTSLayer associatedRtsLayer,
        final RTSPlayerLayerInterface rtsPlayerLayerInterface,
        final AllBinaryLayerManager layerManager, final GPoint point)
        throws Exception
    {
        int index = this.getIndexAt(rtsPlayerLayerInterface, point);

        if (index < 0)
        {
            return false;
        }

        final ScrollSelectionForm scrollSelectionForm =
            rtsPlayerLayerInterface.getCurrentScrollSelectionForm();

        //this.getItemAt(rtsPlayerLayerInterface, point);
        final CustomItem item = scrollSelectionForm.get(index);
        if (item == null)
        {
            return false;
        }

        this.processSticky(
            associatedRtsLayer,
            rtsPlayerLayerInterface,
            layerManager, item, index);
        
        return true;
    }

    public void processSticky(
        RTSLayer associatedRtsLayer,
        RTSPlayerLayerInterface rtsPlayerLayerInterface,
        AllBinaryLayerManager layerManager, CustomItem item, int index)
        throws Exception
    {
    }

    protected CustomItem getItemAt(
        final RTSPlayerLayerInterface rtsPlayerLayerInterface,
        final GPoint point)
        throws Exception
    {
        logUtil.put(new StringMaker().append("Point: ").append(StringUtil.getInstance().toString(point)).toString(), this, "getItemAt");

        final ScrollSelectionForm scrollSelectionForm =
            rtsPlayerLayerInterface.getCurrentScrollSelectionForm();

        return scrollSelectionForm.getSelectedItem(point);
    }

    protected int getIndexAt(
        final RTSPlayerLayerInterface rtsPlayerLayerInterface,
        final GPoint point)
        throws Exception
    {
        logUtil.put(new StringMaker().append("Point: ").append(StringUtil.getInstance().toString(point)).toString(), this, "getItemAt");

        final ScrollSelectionForm scrollSelectionForm =
            rtsPlayerLayerInterface.getCurrentScrollSelectionForm();

        return scrollSelectionForm.getSelectedIndex(point);
    }

    protected RTSLayer getInstance(
            final AllBinaryLayerManager layerManager, final CustomItem aItem, final GeographicMapCellPosition geographicMapCellPosition)
        throws Exception
    {
        final LayerInterfaceFactoryImageItem item =
            (LayerInterfaceFactoryImageItem) aItem;

        final LayerInterfaceFactoryInterface layerInterfaceFactoryInterface =
            item.getLayerInterfaceFactoryInterface();

        final GPoint cellPoint = geographicMapCellPosition.getPoint();

        //Find first empty spot
        //GeographicMapCellPositionFactory geographicMapCellPositionFactory =
        //  geographicMapInterface.getGeographicMapCellPositionFactory();

        //this.geographicMapInterface.getCellPositionAt(cellPoint.getX(), cellPoint.getY())

        if (layerInterfaceFactoryInterface != null)
        {
            hashtable.put(AllBinaryGameLayerManager.ID, layerManager);
            return (RTSLayer) layerInterfaceFactoryInterface.getInstance(
                getHashtable(), cellPoint.getX(), cellPoint.getY(), cellPoint.getZ());
            //lastLayerInterfaceFactoryInterface = layerInterfaceFactoryInterface;
        }
        return null;
    }

    /**
     * @return the selectedGeographicCellPosition
     */
    public GeographicMapCellPosition getSelectedGeographicCellPosition()
    {
        return selectedGeographicCellPosition;
    }

    /**
     * @param selectedGeographicCellPosition
     *            the selectedGeographicCellPosition to set
     */
    public void setSelectedGeographicCellPosition(
        GeographicMapCellPosition selectedGeographicCellPosition)
    {
        logUtil.put(
            new StringMaker().append("Selected GeographicMapCellPosition: ")
                    .append(selectedGeographicCellPosition.toString()).toString(),
            this, "setSelectedGeographicCellPosition");

        this.selectedGeographicCellPosition = selectedGeographicCellPosition;
    }

    /**
     * @return the stickyItemSelected
     */
    public boolean isStickyItemSelected()
    {
        return stickyItemSelected;
    }

    /**
     * @param stickyItemSelected the stickyItemSelected to set
     */
    public void setStickyItemSelected(boolean stickyItemSelected)
    {
        this.stickyItemSelected = stickyItemSelected;
    }

    /**
     * @return the selectedStickyItem
     */
    public CustomItem getSelectedStickyItem()
    {
        return selectedStickyItem;
    }

    /**
     * @param selectedStickyItem the selectedStickyItem to set
     */
    public void setSelectedStickyItem(CustomItem selectedStickyItem)
    {
        this.selectedStickyItem = selectedStickyItem;
    }

    /**
     * @return the selectedStickyItemIndex
     */
    public int getSelectedStickyItemIndex()
    {
        return selectedStickyItemIndex;
    }

    /**
     * @param selectedStickyItemIndex the selectedStickyItemIndex to set
     */
    public void setSelectedStickyItemIndex(int selectedStickyItemIndex)
    {
        this.selectedStickyItemIndex = selectedStickyItemIndex;
    }

    public Hashtable getHashtable()
    {
        return hashtable;
    }
}
