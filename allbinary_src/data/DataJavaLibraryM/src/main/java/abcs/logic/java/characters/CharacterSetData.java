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
package abcs.logic.java.characters;

public class CharacterSetData
{
   private static final CharacterSetData instance = new CharacterSetData();

   public static CharacterSetData getInstance() {
		return instance;
	}
   
   private CharacterSetData()
   {
   }

   public final String US_ASCII = "US-ASCII";
   public final String ISO_8859_1 = "ISO-8859-1";
   public final String UTF_8 = "UTF-8";
   public final String UTF_16 = "UTF-16";
   public final String UTF_16BE = "UTF-16BE";
   public final String UTF_16LE = "UTF-16LE";
}
