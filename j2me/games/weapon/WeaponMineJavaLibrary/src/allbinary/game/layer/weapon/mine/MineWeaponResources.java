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
package allbinary.game.layer.weapon.mine;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class MineWeaponResources {

    private static final MineWeaponResources SINGLETON = new MineWeaponResources();
    
    public String RESOURCE = "/mine_20_by_20.png";
    public String DROP_ICON_RESOURCE = "/mine_drop_icon_20_by_20.png";
    public String DROP_RESOURCE = "/mine_drop_20_by_20.png";

    private MineWeaponResources()
    {
    }

    public static MineWeaponResources getInstance()
    {
        return SINGLETON;
    }
}
