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
package org.allbinary.game.input.event;

import org.allbinary.logic.NullUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.event.AllBinaryEventObject;

public class GameKeyEvent extends AllBinaryEventObject
{
   public static final GameKeyEvent NONE = new GameKeyEvent(NullUtil.getInstance().NULL_OBJECT, -1, -1, -1, -1, false);
   
   private final int sourceId;
   
   //input info
   private boolean repeated;
   private int key;
   private int gameActionKey;
   private int originalKey;
   
   public GameKeyEvent(Object object, int sourceId, int originalKey, int gameActionKey, int key, boolean repeated)
   {
      super(object);
   
      this.sourceId = sourceId;

      this.setOriginalKey(originalKey);
      this.key = key;
      this.setGameActionKey(gameActionKey);
      this.setRepeatEvents(repeated);
   }

   public GameKeyEvent(Object object, int sourceId, int key)
   {
      super(object);

      this.sourceId = sourceId;

      this.key = key;      
      this.setRepeatEvents(false);            
   }

   public void init(Object object)
   {
      this.setSource(object);
   }
   
   /*
   public void init(Object object, int key)
   {
      //this.setOriginalKey(NO_KEY);
      //this.setGameActionKey(NO_KEY);
      this.setSource(object);
      this.setKey(key);
   }
   */
      
   private  void setRepeatEvents(boolean repeated)
   {
      this.repeated = repeated;
   }
   
   public boolean hasRepeatEvents()
   {
      return this.repeated;
   }

   public int getKey()
   {
      return key;
   }
      
   public String toString()
   {
      StringMaker stringBuffer = new StringMaker();

      /*
      stringBuffer.append("GameKeyEvent: ");
      stringBuffer.append("\nOriginal Key: ");
      stringBuffer.append(this.getOriginalKey());
      stringBuffer.append("\nGame Action Key: ");
      stringBuffer.append(this.getGameActionKey());
      stringBuffer.append("\nMapped Key: ");
      */
      stringBuffer.append("\nKey: ");
      stringBuffer.append(this.getKey());
      
      /*
      stringBuffer.append("\nMapping: ");

      GameKey originalGameKey = GameKey.getInstance(this.getOriginalKey());
      if(originalGameKey != null)
      {
         stringBuffer.append("\nMapping:");
         stringBuffer.append(originalGameKey.getName());
      }

      stringBuffer.append(" to ");

      GameKey gameKey = GameKey.getInstance(this.getKey());
      if(gameKey != null)
      {
         stringBuffer.append(gameKey.getName());
      }

      stringBuffer.append("\nRepeated: ");
      stringBuffer.append(this.hasRepeatEvents());
      */

      //stringBuffer.append("Fix Me");
      
      return stringBuffer.toString();
   }

   public int getOriginalKey()
   {
      return originalKey;
   }

   public void setOriginalKey(int originalKey)
   {
      this.originalKey = originalKey;
   }

   public int getGameActionKey()
   {
      return gameActionKey;
   }

   public void setGameActionKey(int gameActionKey)
   {
      this.gameActionKey = gameActionKey;
   }

   public int getSourceId()
   {
       return sourceId;
   }
}
