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

import org.allbinary.game.rand.MyRandomFactory;
import org.allbinary.logic.system.security.licensing.LockedUtil;
import org.allbinary.util.BasicArrayList;

public class Advertisements
{
    private final BasicArrayList bannerList = new BasicArrayList();
    private final BasicArrayList pageList = new BasicArrayList();
    
    public void addPage(AdvertisementProcessorInterface advertisementProcessorInterface)
    {
        if(!this.pageList.contains(advertisementProcessorInterface))
        {
            this.pageList.add(advertisementProcessorInterface);
        }
    }
    
    public void startRandomPage()
    {
        int size = this.pageList.size();

        if(size > 0)
        {
            int random = myRandomFactory.getAbsoluteNextInt(size);
            
            AdvertisementProcessorInterface advertisementProcessorInterface = 
                (AdvertisementProcessorInterface)
                this.pageList.objectArray[random];
            
            advertisementProcessorInterface.start();
        }
    }

    private final MyRandomFactory myRandomFactory = MyRandomFactory.getInstance();
    
    public void processRandomPage()
    {
        if(LockedUtil.getInstance().isLockedFeature())
        {
            int size = this.pageList.size();

            if(size > 0)
            {
                int random = myRandomFactory.getAbsoluteNextInt(size);
                
                AdvertisementProcessorInterface advertisementProcessorInterface = 
                    (AdvertisementProcessorInterface)
                    this.pageList.objectArray[random];
                
                advertisementProcessorInterface.process();
            }
        }
    }
    
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
    
    public void addBanner(AdvertisementProcessorInterface advertisementProcessorInterface)
    {
        if(!this.bannerList.contains(advertisementProcessorInterface))
        {
            this.bannerList.add(advertisementProcessorInterface);
        }
    }
    
    public void startAll()
    {
        for(int index = this.bannerList.size(); --index >= 0;)
        {
            AdvertisementProcessorInterface advertisementProcessorInterface = 
                (AdvertisementProcessorInterface) this.bannerList.objectArray[index];

            advertisementProcessorInterface.start();
        }
    }

    public void stopAllBanner()
    {
        for(int index = this.bannerList.size(); --index >= 0;)
        {
            AdvertisementProcessorInterface advertisementProcessorInterface = 
                (AdvertisementProcessorInterface) this.bannerList.objectArray[index];

            advertisementProcessorInterface.stop();
        }
    }
    
    public AdvertisementProcessorInterface getBanner(int index)
    {
        AdvertisementProcessorInterface advertisementProcessorInterface = 
            (AdvertisementProcessorInterface) this.bannerList.objectArray[index];

        return advertisementProcessorInterface;
    }

    public void stopAll()
    {
        this.stopAllBanner();
        this.stopAllPage();
    }        
}
