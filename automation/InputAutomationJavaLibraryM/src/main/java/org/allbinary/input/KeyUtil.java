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

/**
 *
 * @author Admin
 */
public class KeyUtil
{
   
   /** Creates a new instance of KeyUtil */
   public KeyUtil()
   {
   }
   
   public static boolean isNormallyDisplayed(int keyCode)
   {
      //Character.isDigit(nextInteger)
      //Character.isLetter(keyCode);
      switch(keyCode)
      {
         case KeyEvent.VK_SPACE:
         case KeyEvent.VK_COMMA:
         case KeyEvent.VK_MINUS:
         case KeyEvent.VK_PERIOD:
         case KeyEvent.VK_SLASH:
         case KeyEvent.VK_0:
         case KeyEvent.VK_1:
         case KeyEvent.VK_2:
         case KeyEvent.VK_3:
         case KeyEvent.VK_4:
         case KeyEvent.VK_5:
         case KeyEvent.VK_6:
         case KeyEvent.VK_7:
         case KeyEvent.VK_8:
         case KeyEvent.VK_9:
         case KeyEvent.VK_SEMICOLON:
         case KeyEvent.VK_EQUALS:
            
         case KeyEvent.VK_A:
         case KeyEvent.VK_B:
         case KeyEvent.VK_C:
         case KeyEvent.VK_D:
         case KeyEvent.VK_E:
         case KeyEvent.VK_F:
         case KeyEvent.VK_G:
         case KeyEvent.VK_H:
         case KeyEvent.VK_I:
         case KeyEvent.VK_J:
         case KeyEvent.VK_K:
         case KeyEvent.VK_L:
         case KeyEvent.VK_M:
         case KeyEvent.VK_N:
         case KeyEvent.VK_O:
         case KeyEvent.VK_P:
         case KeyEvent.VK_Q:
         case KeyEvent.VK_R:
         case KeyEvent.VK_S:
         case KeyEvent.VK_T:
         case KeyEvent.VK_U:
         case KeyEvent.VK_V:
         case KeyEvent.VK_W:
         case KeyEvent.VK_X:
         case KeyEvent.VK_Y:
         case KeyEvent.VK_Z:
            
         case KeyEvent.VK_OPEN_BRACKET:
         case KeyEvent.VK_BACK_SLASH:
         case KeyEvent.VK_CLOSE_BRACKET:
            
         case KeyEvent.VK_NUMPAD0:
         case KeyEvent.VK_NUMPAD1:
         case KeyEvent.VK_NUMPAD2:
         case KeyEvent.VK_NUMPAD3:
         case KeyEvent.VK_NUMPAD4:
         case KeyEvent.VK_NUMPAD5:
         case KeyEvent.VK_NUMPAD6:
         case KeyEvent.VK_NUMPAD7:
         case KeyEvent.VK_NUMPAD8:
         case KeyEvent.VK_NUMPAD9:
         case KeyEvent.VK_MULTIPLY:
         case KeyEvent.VK_ADD:
         case KeyEvent.VK_SEPARATER:
         case KeyEvent.VK_SUBTRACT:
         case KeyEvent.VK_DECIMAL:
         case KeyEvent.VK_DIVIDE:
            
            return true;
         default:
            return false;
      }
   }
   
   /*
   public static boolean isActionKey(int keyCode)
   {
      switch (keyCode)
      {
         case KeyEvent.VK_HOME:
         case KeyEvent.VK_END:
         case KeyEvent.VK_PAGE_UP:
         case KeyEvent.VK_PAGE_DOWN:
         case KeyEvent.VK_UP:
         case KeyEvent.VK_DOWN:
         case KeyEvent.VK_LEFT:
         case KeyEvent.VK_RIGHT:
         case KeyEvent.VK_BEGIN:
            
         case KeyEvent.VK_KP_LEFT:
         case KeyEvent.VK_KP_UP:
         case KeyEvent.VK_KP_RIGHT:
         case KeyEvent.VK_KP_DOWN:
            
         case KeyEvent.VK_F1:
         case KeyEvent.VK_F2:
         case KeyEvent.VK_F3:
         case KeyEvent.VK_F4:
         case KeyEvent.VK_F5:
         case KeyEvent.VK_F6:
         case KeyEvent.VK_F7:
         case KeyEvent.VK_F8:
         case KeyEvent.VK_F9:
         case KeyEvent.VK_F10:
         case KeyEvent.VK_F11:
         case KeyEvent.VK_F12:
         case KeyEvent.VK_F13:
         case KeyEvent.VK_F14:
         case KeyEvent.VK_F15:
         case KeyEvent.VK_F16:
         case KeyEvent.VK_F17:
         case KeyEvent.VK_F18:
         case KeyEvent.VK_F19:
         case KeyEvent.VK_F20:
         case KeyEvent.VK_F21:
         case KeyEvent.VK_F22:
         case KeyEvent.VK_F23:
         case KeyEvent.VK_F24:
         case KeyEvent.VK_PRINTSCREEN:
         case KeyEvent.VK_SCROLL_LOCK:
         case KeyEvent.VK_CAPS_LOCK:
         case KeyEvent.VK_NUM_LOCK:
         case KeyEvent.VK_PAUSE:
         case KeyEvent.VK_INSERT:
            
         case KeyEvent.VK_FINAL:
         case KeyEvent.VK_CONVERT:
         case KeyEvent.VK_NONCONVERT:
         case KeyEvent.VK_ACCEPT:
         case KeyEvent.VK_MODECHANGE:
         case KeyEvent.VK_KANA:
         case KeyEvent.VK_KANJI:
         case KeyEvent.VK_ALPHANUMERIC:
         case KeyEvent.VK_KATAKANA:
         case KeyEvent.VK_HIRAGANA:
         case KeyEvent.VK_FULL_WIDTH:
         case KeyEvent.VK_HALF_WIDTH:
         case KeyEvent.VK_ROMAN_CHARACTERS:
         case KeyEvent.VK_ALL_CANDIDATES:
         case KeyEvent.VK_PREVIOUS_CANDIDATE:
         case KeyEvent.VK_CODE_INPUT:
         case KeyEvent.VK_JAPANESE_KATAKANA:
         case KeyEvent.VK_JAPANESE_HIRAGANA:
         case KeyEvent.VK_JAPANESE_ROMAN:
         case KeyEvent.VK_KANA_LOCK:
         case KeyEvent.VK_INPUT_METHOD_ON_OFF:
            
         case KeyEvent.VK_AGAIN:
         case KeyEvent.VK_UNDO:
         case KeyEvent.VK_COPY:
         case KeyEvent.VK_PASTE:
         case KeyEvent.VK_CUT:
         case KeyEvent.VK_FIND:
         case KeyEvent.VK_PROPS:
         case KeyEvent.VK_STOP:
            
         case KeyEvent.VK_HELP:
         case KeyEvent.VK_WINDOWS:
         case KeyEvent.VK_CONTEXT_MENU:
            return true;
      }
      return false;
   }
   */
}
