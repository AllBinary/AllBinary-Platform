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
package allbinary.input.automation.module.generic;

public class GenericConfigData
{
   public static String RELATIVE_PATH = "./";
   public static String CONFIG_PATH = "modules/configs/";
   public static String MODULE_RELATIVE_PATH = RELATIVE_PATH + CONFIG_PATH + "allbinary/input/automation/module/";
   public static String GAME_MODULE_RELATIVE_PATH = MODULE_RELATIVE_PATH + "game/";
   public static String MMO_GAME_MODULE_RELATIVE_PATH = GAME_MODULE_RELATIVE_PATH + "mmog/";
   public static String FREE_MMO_GAME_MODULE_RELATIVE_PATH = "free/";
    
    private GenericConfigData()
    {
    }
    
}
