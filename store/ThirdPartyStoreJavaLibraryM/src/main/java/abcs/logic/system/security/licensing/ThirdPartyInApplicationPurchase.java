/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package abcs.logic.system.security.licensing;

import abcs.logic.basic.string.StringUtil;

/**
 *
 * @author user
 */
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
}
