/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allbinary.logic.system.security.licensing;

import org.allbinary.logic.string.StringUtil;

/**
 *
 * @author user
 */
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
