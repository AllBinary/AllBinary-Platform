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
package abcs.logic.basic.path;

//Used to correct user input for paths
public class AbPathData
{
    private static final AbPathData instance = new AbPathData();

    public static AbPathData getInstance()
    {
        return instance;
    }
    
   public final String EXTENSION_SEP = ".";
   public final char SEPARATORCHAR = '/';
   public final String SEPARATOR = "/";
   
   private AbPathData()
   {
   }

}
