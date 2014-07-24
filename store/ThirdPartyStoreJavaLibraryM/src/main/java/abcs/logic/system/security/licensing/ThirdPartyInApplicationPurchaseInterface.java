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
package abcs.logic.system.security.licensing;

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
