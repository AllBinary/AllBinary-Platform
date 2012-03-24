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
package allbinary.game.layer.hud.basic;

public class HudResources
{
    private static final HudResources instance = new HudResources();
    
    public static HudResources getInstance()
    {
        return instance;
    }
    
    private HudResources()
    {
        
    }
    
    public final String RESOURCE_LIFE = "/hud_life_10_by_10.png";
    public final String RESOURCE_HEALTH = "/hud_health_10_by_10.png";

}
