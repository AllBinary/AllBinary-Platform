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
package org.allbinary.input;

import java.awt.event.KeyEvent;
import java.util.Hashtable;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class KeySingletonFactory
{
   private static Hashtable keyStringHashtable = new Hashtable();
   //private static Integer[] integerArray = new Integer[0xFF];
   
   private static Integer[] keyArray =
   {
      KeyEvent.VK_0,
      KeyEvent.VK_1,
      KeyEvent.VK_2,
      KeyEvent.VK_3,
      KeyEvent.VK_4,
      KeyEvent.VK_5,
      KeyEvent.VK_6,
      KeyEvent.VK_7,
      KeyEvent.VK_8,
      KeyEvent.VK_9,
      
      KeyEvent.VK_A,
      KeyEvent.VK_B,
      KeyEvent.VK_C,
      KeyEvent.VK_D,
      KeyEvent.VK_E,
      KeyEvent.VK_F,
      KeyEvent.VK_G,
      KeyEvent.VK_H,
      KeyEvent.VK_I,
      KeyEvent.VK_J,
      KeyEvent.VK_K,
      KeyEvent.VK_L,
      KeyEvent.VK_M,
      KeyEvent.VK_N,
      KeyEvent.VK_O,
      KeyEvent.VK_Q,
      KeyEvent.VK_R,
      KeyEvent.VK_S,
      KeyEvent.VK_T,
      KeyEvent.VK_U,
      KeyEvent.VK_V,
      KeyEvent.VK_W,
      KeyEvent.VK_X,
      KeyEvent.VK_Y,
      KeyEvent.VK_Z,
      
      KeyEvent.VK_NUMPAD0,
      KeyEvent.VK_NUMPAD1,
      KeyEvent.VK_NUMPAD2,
      KeyEvent.VK_NUMPAD3,
      KeyEvent.VK_NUMPAD4,
      KeyEvent.VK_NUMPAD5,
      KeyEvent.VK_NUMPAD6,
      KeyEvent.VK_NUMPAD7,
      KeyEvent.VK_NUMPAD8,
      KeyEvent.VK_NUMPAD9,
      KeyEvent.VK_NUM_LOCK,
      
      KeyEvent.VK_F1,
      KeyEvent.VK_F2,
      KeyEvent.VK_F3,
      KeyEvent.VK_F4,
      KeyEvent.VK_F5,
      KeyEvent.VK_F6,
      KeyEvent.VK_F7,
      KeyEvent.VK_F8,
      KeyEvent.VK_F9,
      KeyEvent.VK_F10,
      KeyEvent.VK_F11,
      KeyEvent.VK_F12,
      KeyEvent.VK_F13,
      KeyEvent.VK_F14,
      KeyEvent.VK_F15,
      KeyEvent.VK_F16,
      KeyEvent.VK_F17,
      KeyEvent.VK_F18,
      KeyEvent.VK_F19,
      KeyEvent.VK_F20,
      KeyEvent.VK_F21,
      KeyEvent.VK_F22,
      KeyEvent.VK_F23,
      KeyEvent.VK_F24,
      
      KeyEvent.VK_ACCEPT,
      KeyEvent.VK_ADD,
      KeyEvent.VK_AGAIN,
      KeyEvent.VK_ALL_CANDIDATES,
      KeyEvent.VK_ALPHANUMERIC,
      KeyEvent.VK_ALT,
      KeyEvent.VK_ALT_GRAPH,
      KeyEvent.VK_AMPERSAND,
      KeyEvent.VK_ASTERISK,
      KeyEvent.VK_AT,
      KeyEvent.VK_BACK_QUOTE,
      KeyEvent.VK_BACK_SLASH,
      KeyEvent.VK_BACK_SPACE,
      KeyEvent.VK_BEGIN,
      KeyEvent.VK_BRACELEFT,
      KeyEvent.VK_BRACERIGHT,
      KeyEvent.VK_CANCEL,
      KeyEvent.VK_CAPS_LOCK,
      KeyEvent.VK_CIRCUMFLEX,
      KeyEvent.VK_CLEAR,
      KeyEvent.VK_CLOSE_BRACKET,
      KeyEvent.VK_CODE_INPUT,
      KeyEvent.VK_COLON,
      KeyEvent.VK_COMMA,
      KeyEvent.VK_COMPOSE,
      KeyEvent.VK_CONTEXT_MENU,
      KeyEvent.VK_CONTROL,
      KeyEvent.VK_CONVERT,
      KeyEvent.VK_COPY,
      KeyEvent.VK_CUT,
      KeyEvent.VK_DEAD_ABOVEDOT,
      KeyEvent.VK_DEAD_ABOVERING,
      KeyEvent.VK_DEAD_ACUTE,
      KeyEvent.VK_DEAD_BREVE,
      KeyEvent.VK_DEAD_CARON,
      KeyEvent.VK_DEAD_CEDILLA,
      KeyEvent.VK_DEAD_CIRCUMFLEX,
      KeyEvent.VK_DEAD_DIAERESIS,
      KeyEvent.VK_DEAD_DOUBLEACUTE,
      KeyEvent.VK_DEAD_GRAVE,
      KeyEvent.VK_DEAD_IOTA,
      KeyEvent.VK_DEAD_MACRON,
      KeyEvent.VK_DEAD_OGONEK,
      KeyEvent.VK_DEAD_SEMIVOICED_SOUND,
      KeyEvent.VK_DEAD_TILDE,
      KeyEvent.VK_DEAD_VOICED_SOUND,
      KeyEvent.VK_DECIMAL,
      KeyEvent.VK_DELETE,
      KeyEvent.VK_DIVIDE,
      KeyEvent.VK_DOLLAR,
      KeyEvent.VK_DOWN,
      KeyEvent.VK_END,
      KeyEvent.VK_ENTER,
      KeyEvent.VK_EQUALS,
      KeyEvent.VK_ESCAPE,
      KeyEvent.VK_EURO_SIGN,
      KeyEvent.VK_EXCLAMATION_MARK,
      KeyEvent.VK_FINAL,
      KeyEvent.VK_FIND,
      KeyEvent.VK_FULL_WIDTH,
      KeyEvent.VK_GREATER,
      KeyEvent.VK_HALF_WIDTH,
      KeyEvent.VK_HELP,
      KeyEvent.VK_HIRAGANA,
      KeyEvent.VK_HOME,
      KeyEvent.VK_INPUT_METHOD_ON_OFF,
      KeyEvent.VK_INSERT,
      KeyEvent.VK_INVERTED_EXCLAMATION_MARK,
      KeyEvent.VK_JAPANESE_HIRAGANA,
      KeyEvent.VK_JAPANESE_KATAKANA,
      KeyEvent.VK_JAPANESE_ROMAN,
      KeyEvent.VK_KANA,
      KeyEvent.VK_KANA_LOCK,
      KeyEvent.VK_KANJI,
      KeyEvent.VK_KATAKANA,
      KeyEvent.VK_KP_DOWN,
      KeyEvent.VK_KP_LEFT,
      KeyEvent.VK_KP_RIGHT,
      KeyEvent.VK_KP_UP,
      KeyEvent.VK_LEFT,
      KeyEvent.VK_LEFT_PARENTHESIS,
      KeyEvent.VK_LESS,
      KeyEvent.VK_META,
      KeyEvent.VK_MINUS,
      KeyEvent.VK_MODECHANGE,
      KeyEvent.VK_MULTIPLY,
      KeyEvent.VK_NONCONVERT,
      KeyEvent.VK_NUMBER_SIGN,
      KeyEvent.VK_OPEN_BRACKET,
      KeyEvent.VK_PAGE_DOWN,
      KeyEvent.VK_PAGE_UP,
      KeyEvent.VK_PASTE,
      KeyEvent.VK_PAUSE,
      KeyEvent.VK_PERIOD,
      KeyEvent.VK_PLUS,
      KeyEvent.VK_PREVIOUS_CANDIDATE,
      KeyEvent.VK_PRINTSCREEN,
      KeyEvent.VK_PROPS,
      KeyEvent.VK_QUOTE,
      KeyEvent.VK_QUOTEDBL,
      KeyEvent.VK_RIGHT,
      KeyEvent.VK_RIGHT_PARENTHESIS,
      KeyEvent.VK_SCROLL_LOCK,
      KeyEvent.VK_SEMICOLON,
      KeyEvent.VK_SEPARATER,
      KeyEvent.VK_SEPARATOR,
      KeyEvent.VK_SHIFT,
      KeyEvent.VK_SLASH,
      KeyEvent.VK_SPACE,
      KeyEvent.VK_STOP,
      KeyEvent.VK_SUBTRACT,
      KeyEvent.VK_TAB,
      KeyEvent.VK_UNDEFINED,
      KeyEvent.VK_UNDERSCORE,
      KeyEvent.VK_UNDO,
      KeyEvent.VK_UP,
      KeyEvent.VK_WINDOWS
   };
   
   static
   {
      try
      {
         for(int index = 0; index < keyArray.length; index++)
         {
            String keyAsString = KeyEvent.getKeyText(keyArray[index]);
            keyStringHashtable.put(keyAsString, keyArray[index]);
            /*
            if(keyArray[index] < 0xFF && keyArray[index] > 0x00)
            {
               integerArray[keyArray[index]] = keyArray[index];
            }
            */
         }
      }
      catch(Exception e)
      {
         LogUtil.put(LogFactory.getInstance("Static Exception", "KeySingleFactory", "static", e));
      }
   }
   
   private KeySingletonFactory()
   {
   }
   
   public static Hashtable getHashtable()
   {
      return keyStringHashtable;
   }
   
   public static Integer getInstance(int index)
   {
      /*
      if(keyArray[index] < 0xFF && keyArray[index] > 0x00)
      {
         return integerArray[index];
      }
      else
      {
         
      }
      */
      return Integer.valueOf(index);
   }
   
   public static Integer[] getArray()
   {
      return keyArray;
   }
}
