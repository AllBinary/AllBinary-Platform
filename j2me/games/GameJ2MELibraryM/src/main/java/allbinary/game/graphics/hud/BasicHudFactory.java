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
package allbinary.game.graphics.hud;

public class BasicHudFactory
{
    private static final BasicHudFactory instance = new BasicHudFactory();
    
    public static BasicHudFactory getInstance()
    {
        return instance;
    }

    private BasicHudFactory()
    {
        
    }
    
    public final String DIRECTION_EXCEPTION =
        "Only Horizontal Direction is Allowed Currently";
    
   //Direction
   public final int VERTICAL = 0;
   public final int HORIZONTAL = 1;
   
   //Location
   public final int BOTTOMLEFT = 0;
   public final int BOTTOMRIGHT = 1;
   public final int TOPLEFT = 2;
   public final int TOPRIGHT = 3;
   
   public final int BOTTOMCENTER = 4;
   public final int TOPCENTER = 5;
   public final int ABSOLUTE = 6;
}
