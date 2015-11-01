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

import org.allbinary.logic.basic.string.StringUtil;

public class ThirdPartyInApplicationPurchase 
implements ThirdPartyInApplicationPurchaseInterface {

    public boolean isPurchased(LockableFeature lockableFeature)
    {
        return false;
    }

    public void onCreate(Object object, Object stateObject)
    {
        
    }

    public void onDestroy()
    {
        
    }

    public void onStart()
    {
        
    }

    public void onActivityResult(final int requestCode, final int resultCode, final Object dataObject) {
    }

    public void onSaveInstanceState(Object object) {
        
    }
    
    public void onStop()
    {
        
    }

    public void requestPurchase(LockableFeature lockableFeature)
    {
        
    }

    public void requestReceipts()
    {
        
    }
    
    public void add(LockableFeature lockableFeature)
    {
    }
    
    public String getUserName()
    {
        return StringUtil.getInstance().EMPTY_STRING;
    }
    
    public boolean isEnabled()
    {
        return false;
    } 
    
    protected void requestProducts()
    {
            
    }
}