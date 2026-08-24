/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allbinary.logic.system.security.licensing;

import jsinterop.annotations.JsType;

import org.allbinary.logic.string.StringUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;

/**
 *
 * @author user
 */

@JsType
public class InApplicationPurchaseFactory {
 
    private static final InApplicationPurchaseFactory instance = new InApplicationPurchaseFactory();

    /**
     * @return the instance
     */
    @JsMethod
    public static InApplicationPurchaseFactory getInstance() {
        return InApplicationPurchaseFactory.instance;
    }
    
    @JsConstructor
    private InApplicationPurchaseFactory()
    {
    }

    @JsMethod
    public void init(Object object, Object stateObject)
    {
    }
    
    @JsMethod
    public void onCreate(Object object, Object stateObject)
    {
    }
    
    @JsMethod
    public void onStart()
    {
    }
    
    @JsMethod
    public void onResult(final int requestCode, final int resultCode, final Object data)
    {
    }
    
    @JsMethod
    public void onSaveState(final Object object)
    {
    }
    
    @JsMethod
    public void onStop()
    {
    }
    
    @JsMethod
    public void onDestroy() {
    }    

    
    @JsMethod
    public void purchase(LockableFeature lockableFeature)
    {
    }
    
    @JsMethod
    public boolean isPurchased(LockableFeature lockableFeature)
    {
        return true;
    }
    
    @JsMethod
    public void add(LockableFeature lockableFeature)
    {
    }
    
    @JsMethod
    public String getUserName()
    {
        return StringUtil.getInstance().EMPTY_STRING;
    }
        
    @JsMethod
    public boolean isEnabled()
    {
        return false;
    }    
}
