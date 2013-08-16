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
package allbinary.debug;

public class DebugFactory {

    //private static DebugInterface SINGLETON = new AndroidDebug();
    
    public static DebugInterface getInstance()
    {
      //TWB - Release Change
        //Make sure that it is on No Debug For Release
        //return SINGLETON;
        return NoDebug.getInstance();
    }    
}
