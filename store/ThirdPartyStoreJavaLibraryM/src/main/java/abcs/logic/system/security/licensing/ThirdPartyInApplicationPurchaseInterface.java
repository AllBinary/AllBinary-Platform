/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package abcs.logic.system.security.licensing;

/**
 *
 * @author user
 */
public interface ThirdPartyInApplicationPurchaseInterface {

    boolean isPurchased(LockableFeature lockableFeature);

    void onCreate(Object object, Object stateObject);

    void onDestroy();

    void onStart();

    void onActivityResult(final int requestCode, final int resultCode, final Object dataObject);

    void onSaveInstanceState(Object object);
            
    void onStop();

    void requestPurchase(LockableFeature lockableFeature);

    void requestReceipts();
    
    void add(LockableFeature lockableFeature);
    
    String getUserName();
    
    boolean isEnabled();
}
