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
package org.allbinary.thirdparty.store;

/**
 *
 * @author user
 */
public class ThirdPartyStoreFactory {

    private static final NoThirdPartyStore instance =
        new NoThirdPartyStore();

    /**
     * @return the instance
     */
    public static ThirdPartyStore getInstance()
    {
        return instance;
    }

    private ThirdPartyStoreFactory()
    {

    }
}
