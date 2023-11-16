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

public class InApplicationPurchaseFactory {
 
    private static final InApplicationPurchaseFactory instance = new InApplicationPurchaseFactory();

    /**
     * @return the instance
     */
    public static InApplicationPurchaseFactory getInstance() {
        return instance;
    }

    
    private InApplicationPurchaseFactory()
    {
    }

    public void init(Object object, Object stateObject)
    {
    }
    
    public void onCreate(Object object, Object stateObject)
    {
    }
    
    public void onStart()
    {
    }
    
    public void onResult(final int requestCode, final int resultCode, final Object data)
    {
    }
    
    public void onSaveState(final Object object)
    {
    }

    public void onStop()
    {
    }
    
    public void onDestroy() {
    }
    
    public void purchase(LockableFeature lockableFeature)
    {
    }
    
    public boolean isPurchased(LockableFeature lockableFeature)
    {
        return false;
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
}
