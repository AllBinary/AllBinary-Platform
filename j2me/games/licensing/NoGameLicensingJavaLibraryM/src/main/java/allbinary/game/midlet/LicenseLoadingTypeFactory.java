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
package allbinary.game.midlet;

public class LicenseLoadingTypeFactory
{
    private static final LicenseLoadingTypeFactory instance = new LicenseLoadingTypeFactory();
    
    public static LicenseLoadingTypeFactory getIntance()
    {
        return instance;
    }
    
    public final LicenseLoadingType INITIAL_LOADING = new LicenseLoadingType("Initial Loading");
    public final LicenseLoadingType GAME_START = new LicenseLoadingType("Game Start");
    public final LicenseLoadingType LOGIN = new LicenseLoadingType("Login");
    public final LicenseLoadingType OTHER = new LicenseLoadingType("Other/Usually The First Non Demo Level");
}
