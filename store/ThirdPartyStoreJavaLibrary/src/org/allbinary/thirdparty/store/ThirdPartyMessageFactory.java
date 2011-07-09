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

public class ThirdPartyMessageFactory
{
    private static final ThirdPartyMessageFactory instance = 
        new ThirdPartyMessageFactory();

    public final String AUTHORIZED = "Authorized";
    public final String NOT_AUTHORIZED = "Not Authorized";

    public final String LINKAGE_ERROR = "Linkage Error";
    
    public static ThirdPartyMessageFactory getInstance()
    {
        return instance;
    }
}
