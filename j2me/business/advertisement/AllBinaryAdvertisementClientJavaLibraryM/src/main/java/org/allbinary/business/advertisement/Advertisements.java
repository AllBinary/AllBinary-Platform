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
package org.allbinary.business.advertisement;

import jsinterop.annotations.JsType;

import org.allbinary.game.rand.MyRandomFactory;
import org.allbinary.logic.system.security.licensing.LockedUtil;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import jsinterop.annotations.JsMethod;


@JsType
public class Advertisements
{
    private final BasicArrayList bannerList = new BasicArrayListD();
    private final BasicArrayList pageList = new BasicArrayListD();
    
    @JsMethod
    public void addPage(AdvertisementProcessorInterface advertisementProcessorInterface)
    {
        if(!this.pageList.contains(advertisementProcessorInterface))
        {
            this.pageList.add(advertisementProcessorInterface);
        }
    }
    
    @JsMethod
    public void startRandomPage()
    {
        int size = this.pageList.size();

        if(size > 0)
        {
            int random = this.myRandomFactory.getAbsoluteNextInt(size);
            
            AdvertisementProcessorInterface advertisementProcessorInterface = 
                (AdvertisementProcessorInterface)
                this.pageList.objectArray[random];
            
            advertisementProcessorInterface.start();
        }
    }

    private final MyRandomFactory myRandomFactory = MyRandomFactory.getInstance();
    
    @JsMethod
    public void processRandomPage()
    {
        if(LockedUtil.getInstance().isLocked())
        {
            int size = this.pageList.size();

            if(size > 0)
            {
                int random = this.myRandomFactory.getAbsoluteNextInt(size);
                
                AdvertisementProcessorInterface advertisementProcessorInterface = 
                    (AdvertisementProcessorInterface)
                    this.pageList.objectArray[random];
                
                advertisementProcessorInterface.process();
            }
        }
    }
    
    @JsMethod
    public void stopAllPage()
    {
        for(int index = this.pageList.size(); --index >= 0;)
        {
            AdvertisementProcessorInterface advertisementProcessorInterface = 
                (AdvertisementProcessorInterface)
                this.pageList.objectArray[index];
            
            advertisementProcessorInterface.stop();
        }
    }
    
    @JsMethod
    public void addBanner(AdvertisementProcessorInterface advertisementProcessorInterface)
    {
        if(!this.bannerList.contains(advertisementProcessorInterface))
        {
            this.bannerList.add(advertisementProcessorInterface);
        }
    }
    
    @JsMethod
    public void startAll()
    {
        for(int index = this.bannerList.size(); --index >= 0;)
        {
            AdvertisementProcessorInterface advertisementProcessorInterface = 
                (AdvertisementProcessorInterface) this.bannerList.objectArray[index];

            advertisementProcessorInterface.start();
        }
    }

    @JsMethod
    public void stopAllBanner()
    {
        for(int index = this.bannerList.size(); --index >= 0;)
        {
            AdvertisementProcessorInterface advertisementProcessorInterface = 
                (AdvertisementProcessorInterface) this.bannerList.objectArray[index];

            advertisementProcessorInterface.stop();
        }
    }
    
    @JsMethod
    public AdvertisementProcessorInterface getBanner(int index)
    {
        AdvertisementProcessorInterface advertisementProcessorInterface = 
            (AdvertisementProcessorInterface) this.bannerList.objectArray[index];

        return advertisementProcessorInterface;
    }

    @JsMethod
    public void stopAll()
    {
        this.stopAllBanner();
        this.stopAllPage();
    }        
}
