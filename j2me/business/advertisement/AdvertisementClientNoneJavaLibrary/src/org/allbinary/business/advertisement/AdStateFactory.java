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

public class AdStateFactory
{
    private static final AdStateFactory instance = new AdStateFactory();

    public static AdStateFactory getInstance()
    {
        return instance;
    }
    
    public void addPage(AdvertisementProcessorInterface advertisementProcessorInterface)
    {
    }
    
    public void startRandomPage()
    {
    }

    public void processRandomPage()
    {
    }
    
    public void stopAllPage()
    {
    }
    
    public void addBanner(AdvertisementProcessorInterface advertisementProcessorInterface)
    {
    }
    
    public void startAll()
    {
    }

    public void stopAllBanner()
    {
    }
    
    public void stopAll()
    {
    }
    
    public boolean isOkayToShowPageAd()
    {
        return false;
    }
    
    
    public void setOkayToShowAds(boolean okayToShowAds)
    {
    }

    public boolean isOkayToShowAds()
    {
        return false;
    }
    
}
