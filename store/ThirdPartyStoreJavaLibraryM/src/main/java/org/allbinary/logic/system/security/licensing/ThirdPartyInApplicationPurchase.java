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
package org.allbinary.logic.system.security.licensing;

import org.allbinary.logic.string.StringUtil;

public class ThirdPartyInApplicationPurchase 
implements ThirdPartyInApplicationPurchaseInterface {

    @Override
    public boolean isPurchased(LockableFeature lockableFeature)
    {
        return false;
    }

    @Override
    public void onCreate(Object object, Object stateObject)
    {
        
    }

    @Override
    public void onDestroy()
    {
        
    }

    @Override
    public void onStart()
    {
        
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Object dataObject) {
    }

    @Override
    public void onSaveInstanceState(Object object) {
        
    }
    
    @Override
    public void onStop()
    {
        
    }

    @Override
    public void requestPurchase(LockableFeature lockableFeature)
    {
        
    }

    @Override
    public void requestReceipts()
    {
        
    }
    
    @Override
    public void add(LockableFeature lockableFeature)
    {
    }
    
    @Override
    public String getUserName()
    {
        return StringUtil.getInstance().EMPTY_STRING;
    }
    
    @Override
    public boolean isEnabled()
    {
        return false;
    } 
    
    protected void requestProducts()
    {
            
    }
}