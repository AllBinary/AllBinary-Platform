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
package org.allbinary.game.layer.unit;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;

import org.allbinary.game.layer.RTSGameStrings;
import org.allbinary.game.layer.item.LayerInterfaceFactoryImageItem;
import org.allbinary.game.layer.item.RTSLayerTextAnimation;
import org.allbinary.game.layer.resources.BasicGameResources;
import org.allbinary.game.layer.waypoint.FlagGameResources;
import org.allbinary.graphics.form.item.CustomItem;
import org.allbinary.image.GameFeatureImageCacheFactory;

import org.allbinary.string.CommonSeps;
import org.allbinary.animation.Animation;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.form.item.CustomItemUtil;
import org.allbinary.graphics.form.item.ItemArraySingletonFactoryInterface;
import org.allbinary.image.ImageCache;
import org.allbinary.layer.LayerInterfaceFactoryInterface;

/**
 *
 * @author user
 */
public class UnitItemArrayFactory
implements ItemArraySingletonFactoryInterface
{
    private final CommonSeps commonSeps = CommonSeps.getInstance();
    
    private BasicColor basicColor = BasicColorFactory.getInstance().WHITE;

    private static final CustomItem[] ITEMS = CustomItemUtil.getInstance().CUSTOM_ITEM_ARRAY;

    private final BasicGameResources[] resources;

    protected UnitItemArrayFactory(BasicGameResources[] resources)
    {
        this.resources = resources;
    }

    protected CustomItem createFlagItem(FlagGameResources flagResources,
        LayerInterfaceFactoryInterface layerInterfaceFactoryInterface)
        throws Exception
    {
        String name = flagResources.NAME;
        int index = name.indexOf(commonSeps.SPACE);
        if (index >= 0)
        {
            name = name.substring(index);
        }
        return this.createFlagItem(flagResources, name, layerInterfaceFactoryInterface);
    }

    protected CustomItem createFlagItem(FlagGameResources flagResources, String name,
        LayerInterfaceFactoryInterface layerInterfaceFactoryInterface) throws Exception
    {
        ImageCache IMAGE_CACHE = GameFeatureImageCacheFactory.getInstance();
        
        Image image = IMAGE_CACHE.get(flagResources.RESOURCE_ICON);
        
        CustomItem item = new LayerInterfaceFactoryImageItem(
                name, image,
            ImageItem.LAYOUT_DEFAULT, flagResources.NAME, basicColor,
            new Animation[] {new RTSLayerTextAnimation(RTSGameStrings.getInstance().DRAGGABLE, image)},
            layerInterfaceFactoryInterface);
        
        return item;
    }

    /*
    private CustomItem createItem(UnitGameResources unitResources,
            CostLayerInterfaceFactoryInterface layerInterfaceFactoryInterface) throws Exception
    {
            ImageCache IMAGE_CACHE = GameFeatureImageCacheFactory.getInstance();

            String name = unitResources.NAME;
            int index = name.indexOf(commonSeps.SPACE);
            if (index >= 0)
            {
                name = name.substring(index);
            }

            Image image = IMAGE_CACHE.get(unitResources.RESOURCE_ICON);
            
            RTSLayerCostAnimation rtsLayerCostAnimation = 
                new RTSLayerCostAnimation(image, layerInterfaceFactoryInterface);

            LayerInterfaceFactoryImageItem layerInterfaceFactoryImageItem = 
                new LayerInterfaceFactoryImageItem(
                    name, image,
                    ImageItem.LAYOUT_DEFAULT, unitResources.NAME, basicColor,
                    new Animation[] {rtsLayerCostAnimation},
                    layerInterfaceFactoryInterface);

            //Buildings may need this at some point as well
            TechEventHandler.getInstance().addListener(
                    rtsLayerCostAnimation);
            
            return layerInterfaceFactoryImageItem;
    }
    */

    @Override
    public CustomItem[] getItems() throws Exception
    {
        return ITEMS;
    }

    /**
     * @return the resources
     */
    public BasicGameResources[] getResources()
    {
        return resources;
    }

    public int indexOf(BasicGameResources basicGameResources)
    {
        for(int index = this.getResources().length - 1; index >= 0; index--)
        {
            if(this.resources[index] == basicGameResources)
            {
                return index;
            }
        }

        return -1;
    }

}
